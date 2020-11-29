package shapescontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 @RestController
 @CrossOrigin
public class ShapesFactory {
	
	@GetMapping("/getInstanceOfShape")
	public static Shape getShape (@RequestParam int id,@RequestParam String shapeType,@RequestParam float x, @RequestParam float y,@RequestParam float x1,@RequestParam float y1,@RequestParam String color,@RequestParam String lineThickness) {
		States states = States.getInstance();
		StackMethods stackshapes = states.getStackShaps();
		StackMethods stepsStack = states.getStepsStack();
		
		
				
		if(shapeType.equals("line")) {
			Line line=new Line(id,x,y,x1,y1,color,lineThickness,shapeType);
			stackshapes.push(line);
			stepsStack.push(line);
			return line;
			
		}
		else if(shapeType.equals("circle")) {
			Circle circle = new Circle (id,x,y,x1,y1,color,lineThickness,shapeType);
			stackshapes.push(circle);
			stepsStack.push(circle);
			return circle;
		}
		else if(shapeType.equals("ellipse")) {
			Ellipse ellipse= new Ellipse(id,x,y,x1,y1,color,lineThickness,shapeType);
			stackshapes.push(ellipse);
			stepsStack.push(ellipse);
			return ellipse;
		}
		else if(shapeType.equals("square")) {
			Square square = new Square(id,x,y,x1,y1,color,lineThickness,shapeType);
			stackshapes.push(square);
			stepsStack.push(square);
			return square;
		}
		else if(shapeType.equals("rectangle")) {
			Rectangle rectangle = new Rectangle(id,x,y,x1,y1,color,lineThickness,shapeType);
			stackshapes.push(rectangle);
			stepsStack.push(rectangle);
			return rectangle;
		}
		else if(shapeType.equals("triangle")) {
			Triangle triangle = new Triangle(id,x,y,x1,y1,color,lineThickness,shapeType);
			stackshapes.push(triangle);
			stepsStack.push(triangle);
			return triangle;
		}
		else {
			return null;
		}
	}

}
