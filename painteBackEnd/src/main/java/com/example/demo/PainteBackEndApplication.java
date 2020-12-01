package com.example.demo;

import org.springframework.boot.SpringApplication;
 //import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import shapescontroller.Circle;
import shapescontroller.Rectangle;
import shapescontroller.Shape;
import shapescontroller.ShapesFactory;
import shapescontroller.Square;
import shapescontroller.StackMethods;
import shapescontroller.Triangle;


@SpringBootApplication
@ComponentScan(basePackageClasses= ShapesFactory.class)
public class PainteBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(PainteBackEndApplication.class, args);
		
		/*Circle c = new Circle("circle",0,0,1,2,"red","10");
		System.out.println(c.id);*/
		
		/*ShapesFactory factory = new ShapesFactory();
		
		System.out.println(c1.id);*/
		
		
		/*ShapesFactory factory = new ShapesFactory();
		Shape c1=(Circle) factory.getShape(1,"circle",3,2,3,-1,"red","10",false);
		Shape c2=(Circle) factory.getShape(2,"circle",3,2,3,-1,"red","10",false);

		/*Shape s1 = factory .getShape(2, "square", 0, 6, 6, 0, "red", "10");
		Shape l1 = factory .getShape(3, "line", 0, 6, 6, 0, "red", "10");
		Shape r1 = factory .getShape(4, "rectangle", 0, 6, 6, 0, "red", "10");
		Shape e1 = factory .getShape(5, "ellipse", 0, 6, 6, 0, "red", "10");
		Shape t1 = factory .getShape(6, "triangle", 0, 6, 6, 0, "red", "10");*/
		/*System.out.println("size of the two stacks from the factory");
		System.out.println(factory.stackshapes.size());
		System.out.println(factory.stepsStack.size());*/
		/*System.out.println("the steps stack after add");
		Object[][] pointsSteps1= convertStackToArray(factory.stepsStack);
		for(int i=0 ;i<2;i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(pointsSteps1[i][j]);
																		
			}
			System.out.println("********");
		}*/
		/*factory.undo();
		System.out.println(factory.stackshapes.size());
		System.out.println(factory.stepsStack.size());*/
		//Object[][] points =factory.move(4, 3, 9, 4);
		/*System.out.println("stack of shapes after move");
		for(int i=0 ;i<factory.stackshapes.size();i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(points[i][j]);
																		
			}
		}
		System.out.println("stack of steps after move");
		System.out.println("stack of steps after  move ="+factory.stepsStack.size());
		Object[][] pointsSteps2 = convertStackToArray(factory.stepsStack);
		for(int i=0 ;i<factory.stepsStack.size();i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(pointsSteps2[i][j]);
																		
			}
			System.out.println("********");
		}*/
		/*Object[][] pointsdel =factory.Delete(10, 2);
		System.out.println("stack of shapes after delete size ="+factory.stackshapes.size());
		for(int i=0 ;i<factory.stackshapes.size();i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(pointsdel[i][j]);
																		
			}
		}
		System.out.println("stack of steps after delete");
		System.out.println("stack of steps after  delete size ="+factory.stepsStack.size());
		Object[][] pointsStepsdel2 = convertStackToArray(factory.stepsStack);
		for(int i=0 ;i<factory.stepsStack.size();i++) {
			for(int j=0 ;j<9;j++) {
				System.out.println(pointsStepsdel2[i][j]);
																		
			}
			System.out.println("********");
		}
		Object[][] pointsRedo=factory.redo();
		System.out.println("size of the two stacks from the factory after the redo");
		System.out.println(factory.stackshapes.size());
		System.out.println(factory.stepsStack.size());
		System.out.println("stack of shapes after redo");
		for(int i=0 ;i<factory.stackshapes.size();i++) {
			for(int j=0 ;j<9;j++) {
				System.out.println(pointsRedo[i][j]);
																		
			}
		}
		System.out.println("stack of steps after redo");
		Object[][] pointsStepsre= convertStackToArray(factory.stepsStack);
		for(int i=0 ;i<factory.stepsStack.size();i++) {
			for(int j=0 ;j<9;j++) {
				System.out.println(pointsStepsre[i][j]);
																		
			}
			System.out.println("********");
		}
		
		/*
		System.out.println("size of the two stacks from the factory after the move");
		System.out.println(factory.stackshapes.size());
		System.out.println(factory.stepsStack.size());*/
		/*Object[][] pointsUndo=factory.undo();
		System.out.println("size of the two stacks from the factory after the undo");
		System.out.println(factory.stackshapes.size());
		System.out.println(factory.stepsStack.size());
		System.out.println("stack of shapes after undo");
		for(int i=0 ;i<1;i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(pointsUndo[i][j]);
																		
			}
		}
		System.out.println("stack of steps after undo");
		Object[][] pointsSteps= convertStackToArray(factory.stepsStack);
		for(int i=0 ;i<2;i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(pointsSteps[i][j]);
																		
			}
			System.out.println("********");
		}
		Object[][] pointsRedo=factory.undo();
		System.out.println("size of the two stacks from the factory after the second undo");
		System.out.println(factory.stackshapes.size());
		System.out.println(factory.stepsStack.size());
		System.out.println("stack of shapes after redo");
		for(int i=0 ;i<1;i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(pointsRedo[i][j]);
																		
			}
		}
		System.out.println("stack of steps after second undo");
		Object[][] pointsStepsre= convertStackToArray(factory.stepsStack);
		for(int i=0 ;i<2;i++) {
			for(int j=0 ;j<8;j++) {
				System.out.println(pointsStepsre[i][j]);
																		
			}
			System.out.println("********");
		}
		
		
	
		/*Circle c1 = new Circle(1,0,0,1,2,"red","10","circle");
		factory.stackshapes.push(c1);
		System.out.println(factory.stackshapes.size());*/
		
		/*Rectangle r = new Rectangle (1,6,6,0,0,"red","10","rectangle");
	   System.out.println(r.isInclude(4,3));*/
		
		/*Circle c1 = new Circle(1,3,2,3,-1,"red","10","circle");
		System.out.println(c1.isInclude(4,3));*/
		/*Triangle r = new Triangle (1, 0 , 0 , 3 , 5,"red","10","rectangle",false);
		   System.out.println(r.isInclude(1,1));
		
		 /*  ShapesFactory factory = new ShapesFactory();
			Shape c1=(Circle) factory.getShape(1,"circle",3,2,3,-1,"red","10");
			Shape c2=(Circle) factory.getShape(2,"circle",4,2,5,-1,"red","10");
			Object[][] pointsUndo=factory.undo();
			System.out.println("size of the two stacks from the factory after the undo");
			System.out.println(factory.stackshapes.size());
			System.out.println(factory.stepsStack.size());
			System.out.println("stack of shapes after undo");
			for(int i=0 ;i<1;i++) {
				for(int j=0 ;j<8;j++) {
					System.out.println(pointsUndo[i][j]);
																			
				}
			}
			System.out.println("stack of steps after undo");
			Object[][] pointsUndo1=convertStackToArray(factory.stepsStack);
			for(int i=0 ;i<2;i++) {
				for(int j=0 ;j<8;j++) {
					System.out.println(pointsUndo1[i][j]);
																			
				}
			}
			Object[][] pointsredo=factory.redo();
			System.out.println("size of the two stacks from the factory after the redo");
			System.out.println(factory.stackshapes.size());
			System.out.println(factory.stepsStack.size());
			System.out.println("stack of shapes after redo");
			for(int i=0 ;i<2;i++) {
				for(int j=0 ;j<8;j++) {
					System.out.println(pointsredo[i][j]);
																			
				}
			}
			System.out.println("stack of steps after redo");
			Object[][] pointsUndo11=convertStackToArray(factory.stepsStack);
			for(int i=0 ;i<2;i++) {
				for(int j=0 ;j<8;j++) {
					System.out.println(pointsUndo11[i][j]);
																			
				}
			}*/
		
}

public static  Object[][] convertStackToArray(StackMethods given) {
	//converts stack of shapes to array of objects to be sent to front end
	Object[][] arr= new Object[given.size()][9];
	//a copy from the stack given to pop from it 
	StackMethods s = new StackMethods();
	//int id,float x, float y, float x1, float y1, String color, String lineThickness
	int i=0;
	while(given.isEmpty()==false) {
		arr[i][0]=((Shape)given.peek()).id;
		arr[i][1]=((Shape)given.peek()).x;
		arr[i][2]=((Shape)given.peek()).y;
		arr[i][3]=((Shape)given.peek()).x1;
		arr[i][4]=((Shape)given.peek()).y1;
		arr[i][5]=((Shape)given.peek()).color;
		arr[i][6]=((Shape)given.peek()).lineThickness;
		arr[i][7]=((Shape)given.peek()).shapeType;
		arr[i][8]=((Shape)given.peek()).filled;
		i++;
		s.push(given.pop());
	}
	while(s.isEmpty()==false) {
		given.push(s.pop());
	}
	return arr;
}
}
