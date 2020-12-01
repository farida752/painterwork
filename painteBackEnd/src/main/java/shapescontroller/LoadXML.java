package shapescontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class LoadXML {

	@GetMapping({"/loadXML"})
    public XMLReturn XMLReader(String path){
        try{
            File file = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Load the input XML document , parse it and return an instance of the document class
            Document document = builder.parse(file);
            NodeList nodelist = document.getDocumentElement().getChildNodes();
            int shapesNum = 0;
            int stepsNum = 0;

            for (int i = 0;i<nodelist.getLength();i++){
                Node node = nodelist.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (((Element) node).hasAttribute("id")) {
                        shapesNum++;
                    } else if (((Element) node).hasAttribute("step")) {
                        stepsNum++;
                    }
                }
            }
            Object[][] shapes = new Object[shapesNum][9];
            Object[] steps = new String[stepsNum];
            for (int i = 0;i<nodelist.getLength();i++) {
                Node node = nodelist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    if (((Element) node).hasAttribute("id")) {
                        //Get the value of ID attribute
                        int ID = Integer.parseInt(node.getAttributes().getNamedItem("id").getNodeValue());
                        shapes[i][0] = ID;
                        //Get the value of all sub-elements
                        float x = Float.parseFloat(elem.getElementsByTagName("x").item(0).getChildNodes().item(0).getNodeValue());
                        shapes[i][1] = x;
                        float y = Float.parseFloat(elem.getElementsByTagName("y").item(0).getChildNodes().item(0).getNodeValue());
                        shapes[i][2] = y;
                        float x1 = Float.parseFloat(elem.getElementsByTagName("x1").item(0).getChildNodes().item(0).getNodeValue());
                        shapes[i][3] = x1;
                        float y1 = Float.parseFloat(elem.getElementsByTagName("y1").item(0).getChildNodes().item(0).getNodeValue());
                        shapes[i][4] = y1;
                        String color = elem.getElementsByTagName("color").item(0).getChildNodes().item(0).getNodeValue();
                        shapes[i][5] = color;
                        String LineThickness = elem.getElementsByTagName("LineThickness").item(0).getChildNodes().item(0).getNodeValue();
                        shapes[i][6] = LineThickness;
                        boolean filled = Boolean.parseBoolean(elem.getElementsByTagName("filled").item(0).getChildNodes().item(0).getNodeValue());
                        shapes[i][7] = filled;
                        String shapeType = elem.getElementsByTagName("shapeType").item(0).getChildNodes().item(0).getNodeValue();
                        shapes[i][8] = shapeType;

                    } else if (((Element) node).hasAttribute("step")) {
                       Object step = node.getAttributes().getNamedItem("step").getNodeValue();
                        steps[i - shapesNum ] = step;
                    }
                }
            }
            return new XMLReturn(shapes,steps);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
