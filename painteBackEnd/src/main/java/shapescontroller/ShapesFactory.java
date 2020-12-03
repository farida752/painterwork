package shapescontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 @RestController
 @CrossOrigin
public class ShapesFactory {
	 
	    States states = States.getInstance();
		public StackMethods stackshapes = states.getStackShaps();
		public StackMethods stepsStack = states.getStepsStack();
		public StackMethods redo = states.getRedoStack();

		
		
@GetMapping("/getInstanceOfShape")
public  Shape getShape (@RequestParam(value = "id") int id,@RequestParam(value = "name") String shapeType,@RequestParam(value = "x1") double x, @RequestParam(value = "y1") double y,@RequestParam(value = "x2") double x1,@RequestParam(value = "y2") double y1,@RequestParam(value = "color") String color,@RequestParam(value = "thick") String lineThickness,@RequestParam(value = "filled") boolean filled) {
				
		if(shapeType.equals("line")) {
			Line line1=new Line(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			Line line2=new Line(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			stackshapes.push(line1);
			stepsStack.push(line2);
			
			/*System.out.println("stack of steps after push");
			Object[][] pointsUndo11=convertStackToArray(stepsStack);
			for(int i=0 ;i<2;i++) {
				for(int j=0 ;j<8;j++) {
					System.out.println(pointsUndo11[i][j]);}}*/
				
			return line1;
			
		}
		else if(shapeType.equals("circle")) {
			Circle circle1 = new Circle (id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			Circle circle2 = new Circle (id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			stackshapes.push(circle1);
			stepsStack.push(circle2);
			return circle1;
		}
		else if(shapeType.equals("ellipse")) {
			Ellipse ellipse1= new Ellipse(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			Ellipse ellipse2= new Ellipse(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			stackshapes.push(ellipse1);
			stepsStack.push(ellipse2);
			return ellipse1;
		}
		else if(shapeType.equals("square")) {
			Square square1 = new Square(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			Square square2 = new Square(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			stackshapes.push(square1);
			stepsStack.push(square2);
			return square1;
		}
		else if(shapeType.equals("rectangle")) {
			Rectangle rectangle1 = new Rectangle(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			Rectangle rectangle2 = new Rectangle(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			stackshapes.push(rectangle1);
			stepsStack.push(rectangle2);
			return rectangle1;
		}
		else if(shapeType.equals("triangle")) {
			Triangle triangle1 = new Triangle(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			Triangle triangle2 = new Triangle(id,x,y,x1,y1,color,lineThickness,shapeType,filled);
			stackshapes.push(triangle1);
			stepsStack.push(triangle2);
			return triangle1;
		}
		else {
			return null;
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/copy")
	public  Object[][] copy(@RequestParam(value = "ox") double x1,@RequestParam(value = "oy") double y1,@RequestParam(value = "nx") double x2,
			@RequestParam(value = "ny") double y2,@RequestParam(value = "id") int idNextOfLastElement){
		StackMethods currentSelectedShapes = new StackMethods();
		currentSelectedShapes = (StackMethods) states.getIncluded(x1, y1);
		//lets make copies in stackOfStepsAndStackOfShapes but after changing x1,y1,id 
		//Every Shape should have unique id
		
		
		while(currentSelectedShapes.isEmpty()==false) {
			Shape sh=((Shape)currentSelectedShapes.peek());
			Shape shCopy=sh.copy(x2, y2,idNextOfLastElement,x1,y1);
			

			idNextOfLastElement++;
			/*stackshapes.push(shCopy);
			stepsStack.push(shCopy1);*/
			currentSelectedShapes.pop();
		}
		
		
		return states.convertStackToArray(stackshapes);
	}
	////////////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/delete")
	public Object[][]Delete(@RequestParam (value="fcx")double x1,@RequestParam (value="fcy") double y1){
		StackMethods s = states.getIncluded(x1, y1);
		StackMethods st=new StackMethods();
		StackMethods tmp=s;
		StackMethods redoAdd =new StackMethods();
		//delete all shapes from stackShapes//add all occurences of stacksteps to redo
		while(s.isEmpty()==false) {
			redoAdd=(StackMethods)st.getNumberOfOcuurencesOfElementID((StackMethods)stepsStack,((Shape)s.peek()).id);
			
			st.DelShapeById(stackshapes, ((Shape)s.peek()).id);
			st.DelShapeById(stepsStack, ((Shape)s.peek()).id);
			//for the delete it means we undoed all shapes formed and their changes so if he
			//clicked redo the shape will appear then redo if you made change it will appear then redo till all changes appear
			//so we will put all occurences in the stack redo
			s.pop();
		}
		while(redoAdd.isEmpty()==false) {
			redo.push(redoAdd.pop());
		}
		return states.convertStackToArray(stackshapes);
	}
	/////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/undo")
	public  Object[][] undo () {
		// if there are no shapes in the canvas
		 if(stepsStack.isEmpty()||stepsStack==null) {
				return null ;
			}
		
			//change shapes stack
			StackMethods se=new StackMethods();
			if(se.getNumberOfOcuurencesOfLastElementID(stepsStack).size()>1){
				//we have modified in it so we modify only in stack shapes we have more than one version in stack of steps
				///get shape with this id then modify it
				/*StackMethods newst =*/ se.getNumberOfOcuurencesOfLastElementID(stepsStack);
						//newst.pop();
				//we poped last occurence only we will leave previous modification
				StackMethods stack=se.getNumberOfOcuurencesOfLastElementID(stepsStack);stack.pop();
				Shape x=(Shape)stack.peek();
				System.out.println(x.getX());
				stackshapes=se.GetShapeById(stackshapes,x.id, x);
			}else {
				// there was only one version in stake of steps so we should delete it from stackShapes
				Shape x=(Shape)se.getNumberOfOcuurencesOfLastElementID(stepsStack).peek();
				stackshapes=se.DelShapeById(stackshapes, x.id);
			}
			//pop from the stack of steps 
			redo.push(stepsStack.pop());
			return states.convertStackToArray(stackshapes);	
	}
	////////////////////////////////////////////////////////////////////////
	@GetMapping("/redo")
	public  Object[][] redo () {
		System.out.println("the redo");
		Object[][] pointsSteps4= convertStackToArray(redo);
		/*for(int i=0 ;i<1;i++) {
			for(int j=0 ;j<9;j++) {
				System.out.println(pointsSteps4[i][j]);
																		
			}
			System.out.println("********");
		}
		if(redo.isEmpty()==true) {
			return null;
		}*/
		StackMethods se=new StackMethods();
		stepsStack.push(redo.pop());
		if(se.getNumberOfOcuurencesOfLastElementID(stepsStack).size()>1) {
			//it was only modified and we added new modification
			//so we can search for it in the shapes state and modify it
			
			StackMethods occ =se.getNumberOfOcuurencesOfLastElementID(stepsStack);
			/*StackMethods occ2=new StackMethods();
			while(occ.isEmpty()==false) {
			occ2.push(occ.pop());
		    }*/
			Shape x=(Shape)occ.peek();
			System.out.println("the shap we get from the redo x field "+x.getX());
			stackshapes=se.GetShapeById(stackshapes, x.id, x);		
		}else {
			//we deleted it and then we want to get it back in stackShapes
			stackshapes.push(stepsStack.peek());
		}
		return states.convertStackToArray(stackshapes);}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	//to edit color and line thickness 
	@GetMapping ("/modify")
	public  Object[][] modify(@RequestParam double x1,@RequestParam double y1
			,String modificationType,String modifiedValue){

		
		StackMethods s=(StackMethods)states.getIncluded(x1,y1);
		StackMethods.copy(states.getIncluded(x1,y1), s);
		StackMethods ss = new StackMethods();//s;
		StackMethods.copy(s, ss);
		StackMethods tmp =new StackMethods();

		 if(modificationType.contentEquals("color")) {
			while(ss.isEmpty()==false) {
				//Shape x=((Shape)ss.peek());
				Shape x=new Shape(((Shape)(ss.peek())).getId(),((Shape)(ss.peek())).getX(), ((Shape)(ss.peek())).getY(), ((Shape)(ss.peek())).getX1(),
((Shape)(ss.peek())).getY1(), modifiedValue, ((Shape)(ss.peek())).getLineThickness(), ((Shape)(ss.peek())).shapeType, true);
				//x.setColor(modifiedValue);
				//x.setFilled(true);
				StackMethods.GetShapeById(ss, x.getId(), x);
				tmp.push(ss.pop());
			}
			//tmp stack contains now all the changes lets modify steps stack,shapes stack
			
			while(tmp.isEmpty()==false) {
				stepsStack.push(tmp.peek());
				//search for element tmp in stack shapes and modify it
				StackMethods.GetShapeById(stackshapes, ((Shape)tmp.peek()).id,(Shape)tmp.peek());
				tmp.pop();
			}
			return states.convertStackToArray(stackshapes);
		}
//////////////////////////////////////////////////////////////////////////////////////
		else if(modificationType.contentEquals("line thickness")) {
			while(ss.isEmpty()==false) {
				((Shape)ss.peek()).setLineThickness(modifiedValue);
				tmp.push(ss.pop());
			}
			//tmp stack contains now all the changes lets modify steps stack,shapes stack
			
			while(tmp.isEmpty()==false) {
				stepsStack.push(tmp.peek());
				//search for element tmp in stack shapes and modify it
				StackMethods.GetShapeById(stackshapes, ((Shape)tmp.peek()).id,(Shape)tmp.peek());
				tmp.pop();
			}
			return states.convertStackToArray(stackshapes);
		}
		else {return null;}
			
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//to resize only from the bottom right and send the new x1 and y1 
	//to resize only from the bottom right and send the new x1 and y1 
		@GetMapping ("/resize")
		public  Object[][] resize(@RequestParam (value ="fcx")double x1,@RequestParam (value ="fcy") double y1
				,@RequestParam (value ="sx") double newX1,@RequestParam (value ="sy") double newY1){
			
			StackMethods s=(StackMethods)states.getIncluded(x1,y1);
			StackMethods ss = new StackMethods();StackMethods.copy(s, ss);
			StackMethods tmp =new StackMethods();
			
				while(ss.isEmpty()==false) {
					((Shape)ss.peek()).setX1(newX1);
					//((Shape)ss.peek()).setY1(newY1);
					tmp.push(ss.pop());
				}
				//tmp stack contains now all the changes lets modify steps stack,shapes stack
				
				while(tmp.isEmpty()==false) {
					stepsStack.push(tmp.peek());
					//search for element tmp in stack shapes and modify it
					stackshapes.GetShapeById(stackshapes, ((Shape)tmp.peek()).id,(Shape)tmp.peek());
					tmp.pop();
				}
				StackMethods.copy(s,ss);
				 //ss = s;
				while(ss.isEmpty()==false) {
					((Shape)ss.peek()).setY1(newY1);
					tmp.push(ss.pop());
				}
				//tmp stack contains now all the changes lets modify steps stack,shapes stack
				
				while(tmp.isEmpty()==false) {
					stepsStack.push(tmp.peek());
					//search for element tmp in stack shapes and modify it
					stackshapes.GetShapeById(stackshapes, ((Shape)tmp.peek()).id,(Shape)tmp.peek());
					tmp.pop();
				}
				
			return states.convertStackToArray(stackshapes);
		}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	//to move the shape 
		@GetMapping ("/move")
		public  Object[][] move(@RequestParam(value = "ox") double x1,@RequestParam(value = "oy") double y1,
				@RequestParam(value = "nx") double newX1,@RequestParam(value = "ny") double newY1){
			/*System.out.println("from the move fun befor any thing");
			Object[][] pointsSteps4= convertStackToArray(stepsStack);
			for(int i=0 ;i<2;i++) {
				for(int j=0 ;j<8;j++) {
					System.out.println(pointsSteps4[i][j]);
																			
				}
				System.out.println("********");
			}
			System.out.println("size of the stack shapes inside the begining of function of move ");
			System.out.println(stackshapes.size());*/
			
			double distanceX= Math.abs(x1-newX1);
			double distanceY= Math.abs(y1-newY1);
			 
			StackMethods s=(StackMethods)states.getIncluded(x1,y1);
			/*System.out.println("selected shapes ");
			System.out.println(s.size());
			
			System.out.println("size of the stack shapes inside the after the include of function of move ");
			System.out.println(stackshapes.size());*/
			
			StackMethods ss = s;
			StackMethods tmp =new StackMethods();
			
				while(ss.isEmpty()==false) {
					Shape currentShape=((Shape)ss.peek());
					double xx=currentShape.x+distanceX;
					double xx1=currentShape.x1+distanceX;
					double yy=currentShape.y+distanceY;
					double yy1=currentShape.y1+distanceY;
				if(x1>newX1) {
					//we should move left
					xx=currentShape.x-distanceX;
					xx1=currentShape.x1-distanceX;
				}
				if(y1>newY1){//we should move left
					yy=currentShape.y-distanceY;
					yy1=currentShape.y1-distanceY;
				}
				/*if(currentShape.x1>newX1) {//we should move left
					xx1=currentShape.x1-distanceX;
					
				}
				if(currentShape.y1>newY1) {//we should move left
					yy1=currentShape.y1-distanceY;
				}*/
				((Shape)ss.peek()).setX(xx);
				((Shape)ss.peek()).setY(yy);
				((Shape)ss.peek()).setX1(xx1);
				((Shape)ss.peek()).setY1(yy1);

					tmp.push(ss.pop());
				}
				//tmp stack contains now all the changes lets modify steps stack,shapes stack
				/*System.out.println("temp size");
				System.out.println(tmp.size());
				System.out.println("from the move fun before modify stack of shapes ,stack of shapes =");
				Object[][] pointsSteps2= convertStackToArray(stackshapes);
				for(int i=0 ;i<2;i++) {
					for(int j=0 ;j<8;j++) {
						System.out.println(pointsSteps2[i][j]);
																				
					}
					System.out.println("********");
				}*/
				while(tmp.isEmpty()==false) {
					stepsStack.push(tmp.peek());
					//search for element tmp in stack shapes and modify it
					//System.out.println("size of the stack shapes inside the function of move ");
					//System.out.println(stackshapes.size());
					stackshapes.GetShapeById(this.stackshapes, ((Shape)tmp.peek()).id,(Shape)tmp.peek());
					tmp.pop();
				}
				/*System.out.println("size of stackshapes");
				System.out.println(stackshapes.size());
				System.out.println("size of copied from stack");
				System.out.println(s.size());
				System.out.println("from the move fun after modify stack of shapes ,stack of steps =");
					Object[][] pointsSteps3= convertStackToArray(stepsStack);
					for(int i=0 ;i<2;i++) {
						for(int j=0 ;j<8;j++) {
							System.out.println(pointsSteps3[i][j]);
																					
						}
						System.out.println("********");
					}*/
			return states.convertStackToArray(stackshapes);
		}
		////////////////////////////////////////////////////////
		//to test 

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


@GetMapping("/load")
public Object[][] getFromXMlFile(@RequestParam String path,@RequestParam String fileName,@RequestParam String fileType) {
	Load l=new Load();
	if(fileType.contentEquals("xml")) {
	Object []arrSteps=(l.XMLReader(path,fileName)).getSteps();
	StackMethods.copy(states.ConvertArr1DToStackSteps(arrSteps),stepsStack);
	Object [][]arrShapes=(l.XMLReader(path,fileName)).getShapes();
	StackMethods.copy(states.ConvertArr2DToStackShapes(arrShapes),stackshapes);
	return arrShapes;
	}
	else if(fileType.contentEquals("json")) {
		Object []arrSteps=(l.JSONReader(path, fileName)).getSteps();
		StackMethods.copy(states.ConvertArr1DToStackSteps(arrSteps),stepsStack);
		Object [][]arrShapes=(l.JSONReader(path, fileName)).getShapes();
		StackMethods.copy(states.ConvertArr2DToStackShapes(arrShapes),stackshapes);
		return arrShapes;
	}
	return null;
}

@GetMapping("/save")
public Object[][] saveXML(@RequestParam String path,@RequestParam String fileName,@RequestParam String fileType){
	if(fileType.contentEquals("xml")) {
	Object shapes[][] =states.convertStackToArray(stackshapes);
	Object steps[]=states.ConvertStackStepsTo1DArray(stepsStack);
	Save s=new Save();
	s.XMLWriter(path, fileName, shapes, steps);
	return shapes;
	}else if(fileType.contentEquals("json")){
		Object shapes[][] =states.convertStackToArray(stackshapes);
		Object steps[]=states.ConvertStackStepsTo1DArray(stepsStack);
		Save s=new Save();
		s.JSONWriter(path, fileName, shapes, steps);
		return shapes;
	}
	return states.convertStackToArray(stackshapes);
}
}
