package shapescontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin
public class ShapesFactory {
	
	//@GetMapping("/getInstanceOfShape")
	public static Shape getShape (@RequestParam String shapeType,@RequestParam float x, @RequestParam float y,@RequestParam float x1,@RequestParam float y1,@RequestParam String color,@RequestParam String lineThickness) {
		StackShapes states = StackShapes.getStackShapes();
				
		if(shapeType.equals("line")) {
			Line line=new Line(shapeType,x,y,x1,y1,color,lineThickness);
			states.push(line);
			return line;
			
		}
		else if(shapeType.equals("circle")) {
			Circle circle = new Circle (shapeType,x,y,x1,y1,color,lineThickness);
			states.push(circle);
			return circle;
		}
		else if(shapeType.equals("ellipse")) {
			Ellipse ellipse= new Ellipse(shapeType,x,y,x1,y1,color,lineThickness);
			states.push(ellipse);
			return ellipse;
		}
		else if(shapeType.equals("square")) {
			Square square = new Square(shapeType,x,y,x1,y1,color,lineThickness);
			states.push(square);
			return square;
		}
		else if(shapeType.equals("rectangle")) {
			Rectangle rectangle = new Rectangle(shapeType,x,y,x1,y1,color,lineThickness);
			states.push(rectangle);
			return rectangle;
		}
		else if(shapeType.equals("triangle")) {
			Triangle triangle = new Triangle(shapeType,x,y,x1,y1,color,lineThickness);
			states.push(triangle);
			return triangle;
		}
		else {
			return null;
		}
	}

}
