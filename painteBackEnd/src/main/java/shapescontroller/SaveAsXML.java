package shapescontroller;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SaveAsXML {

    @GetMapping({"/save as xml"})
    public void XMLWriter(String path,String fileName, Object[][] shapes, Object[] steps) {

        String xmlFilePath = path+"\\"+fileName+".xml";

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //root element
            Element root = document.createElement("shapes");
            document.appendChild(root);
            //Shapes
            for (Object[] objects : shapes) {
                //shape element
                Element shape = document.createElement("shape");
                root.appendChild(shape);

                //set an attribute to staff element
                Attr attr = document.createAttribute("id");
                attr.setValue(String.valueOf(objects[0]));
                shape.setAttributeNode(attr);

                //x element
                Element x = document.createElement("x");
                x.appendChild(document.createTextNode(String.valueOf(objects[1])));
                shape.appendChild(x);

                //y element
                Element y = document.createElement("y");
                y.appendChild(document.createTextNode(String.valueOf(objects[2])));
                shape.appendChild(y);

                //x1 element
                Element x1 = document.createElement("x1");
                x1.appendChild(document.createTextNode(String.valueOf(objects[3])));
                shape.appendChild(x1);

                //y1 element
                Element y1 = document.createElement("y1");
                y1.appendChild(document.createTextNode(String.valueOf(objects[4])));
                shape.appendChild(y1);

                //color element
                Element color = document.createElement("color");
                color.appendChild(document.createTextNode(String.valueOf(objects[5])));
                shape.appendChild(color);

                //thickness element
                Element LineThickness = document.createElement("LineThickness");
                LineThickness.appendChild(document.createTextNode(String.valueOf(objects[6])));
                shape.appendChild(LineThickness);

                //filled element
                Element filled = document.createElement("filled");
                filled.appendChild(document.createTextNode(String.valueOf(objects[7])));
                shape.appendChild(filled);

                //shapeType element
                Element shapeType = document.createElement("shapeType");
                shapeType.appendChild(document.createTextNode(String.valueOf(objects[8])));
                shape.appendChild(shapeType);
            }


            for (Object o : steps) {
                //Steps
                Element Steps = document.createElement("Steps");
                root.appendChild(Steps);
                //step element
                Attr attr = document.createAttribute("step");
                attr.setValue(String.valueOf(o));
                Steps.setAttributeNode(attr);
            }

            //create the xml file
            //transform thr DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));

            transformer.transform(domSource, streamResult);
        } catch (
                ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
}
}