package shapescontroller;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


public class States  {
//singleton class that holds three stacks that saves the operations and they will be full of data from the factory of shapes
	private   StackMethods stackShaps = new StackMethods();
	private   StackMethods redoStack = new StackMethods();
	private   StackMethods stepsStack= new StackMethods();
    private static  States statesInstance;
	
    //the singleton consept
	private  States() {
		
	}
	

	public static States getInstance() {
		if(statesInstance==null) {
			synchronized (States.class) {
				if(statesInstance==null) {
					statesInstance=new States();
				}
			}
		}
		return statesInstance;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/*@GetMapping("/undo")
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
				se.getNumberOfOcuurencesOfLastElementID(stepsStack).pop();
				//we poped last occurence only we will leave previous modification
				Shape x=(Shape)se.getNumberOfOcuurencesOfLastElementID(stepsStack).peek();
				stackShaps=se.GetShapeById(stackShaps, x.id, x);
			}else {
				// there was only one version in stake of steps so we should delete it from stackShapes
				Shape x=(Shape)se.getNumberOfOcuurencesOfLastElementID(stepsStack).peek();
				stackShaps=se.DelShapeById(stackShaps, x.id);
			}
			//pop from the stack of steps 
			redoStack.push(stepsStack.pop());
			return this.convertStackToArray(stepsStack);	
	}*/
	/*@GetMapping("/redo")
	public  Object[][] redo () {
		if(redoStack.isEmpty()==true) {
			return null;
		}
		StackMethods se=new StackMethods();
		stepsStack.push(redoStack.pop());
		if(se.getNumberOfOcuurencesOfLastElementID(stepsStack).size()>1) {
			//it was only modified and we added new modification
			//so we can search for it in the shapes state and modify it
			Shape x=(Shape)se.getNumberOfOcuurencesOfLastElementID(stepsStack).peek();
			stackShaps=se.GetShapeById(stackShaps, x.id, x);		
		}else {
			//we deleted it and then we want to get it back in stackShapes
			stackShaps.push(stepsStack.peek());
		}
		return this.convertStackToArray(stepsStack);}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//functions to try connection
	@GetMapping("/array")
	public int[] array () {
		return new int [] {1,2,3};
	}
	@GetMapping("/data")
	public String data () {
		return "hello from the spring side ";
	}
	
	// function takes a ,b and return all the included shapes from the stack.
	//function to convert stack to array
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////
 //getters for the three stacks to be called in the factory 
public StackMethods getStackShaps() {
	return stackShaps;
}

public StackMethods getRedoStack() {
	return redoStack;
}

public StackMethods getStepsStack() {
	return stepsStack;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//given a point it will return the shape that include this point from the stack of shapes
/*public  Shape getIncluded(float x1,float y1){
	StackMethods s= stackShaps;
	
	while(s.isEmpty()==false) {
		if ( ((Shape) s.peek()).isInclude(x1,y1)) {
			return (Shape) s.peek();
		}
		s.pop();
	}
	return null;
}*/
//////////////////////////////////////////////////////////////////////////////////////////////////
// function to convert stake to Array 

public  Object[][] convertStackToArray(StackMethods given) {
	//converts stack of shapes to array of objects to be sent to front end
	Object[][] arr= new Object[stackShaps.size()][9];
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

/////////////////////////////////////////////////////////////////////////////
//function to send the stack of shapes as array of points to the front end 
//@GetMapping("/sendStackOfShapesToFrontEnd")
public Object[][] sendStackOfShapesToFrontEnd() {
	return this.convertStackToArray(stackShaps);
}
///////////////////////////////////////////////////////////////////////
//search stack of shapes and return all the shapes include this point 
public  StackMethods getIncluded(double a,double b){
	//loop in stack shapes and search then return included shapes
	StackMethods s=new StackMethods();
	System.out.println("the stack shapes from the included in states");
	Object[][] pointsSteps43= convertStackToArray(stackShaps);
	for(int i=0 ;i<stackShaps.size();i++) {
		for(int j=0 ;j<9;j++) {
			System.out.println(pointsSteps43[i][j]);
		}														
		}
		System.out.println("********");
	StackMethods included=new StackMethods();
	while(stackShaps.isEmpty()==false) {
		if(((Shape)stackShaps.peek()).isInclude(a,b)) {
			included.push(stackShaps.peek());
		}
		s.push(stackShaps.pop());
	}
	while(s.isEmpty()==false) {
		stackShaps.push(s.pop());
	}
	return included;
}

////////////////////////////////////////////////////////
public Object[]ConvertStackStepsTo1DArray(StackMethods stepsStackkk) {
	Object[]arr=new Object[stepsStackkk.size()];
	StackMethods tmp=new StackMethods();
	StackMethods.copy(stepsStackkk, tmp);int i=0;//the array is the same order of stack from last to first
	while(i<arr.length) {
		arr[i]=tmp.pop();
		i++;
	}
	return arr;
}

public StackMethods ConvertArr1DToStackSteps(Object []arr) {
	StackMethods tmp=new StackMethods();
	int i=arr.length-1;//the array is the same order of stack from last to first
	while(i>=0) {
		tmp.push((Shape)arr[i]);
		i--;
	}
	StackMethods.copy(tmp, stepsStack);
	return tmp;
}



public StackMethods ConvertArr2DToStackShapes(Object[][]arr) {///////////needs modification
	int i=arr.length-1;
	StackMethods tmp=new StackMethods();
	int id;double x,y,x1,y1;String color;String lineThickness;boolean filled;String shapeType;
	while(i>=0) {
		Shape shape;
		id=Integer.valueOf(arr[i][0].toString());
		x=Double.valueOf(arr[i][1].toString());
		y=Double.valueOf(arr[i][2].toString());
		x1=Double.valueOf(arr[i][3].toString());
		y1=Double.valueOf(arr[i][4].toString());
		color=arr[i][5].toString();
		lineThickness=arr[i][6].toString();
		shapeType=arr[i][7].toString();
		filled=Boolean.valueOf(arr[i][8].toString());
		shape=new Shape(id, x, y, x1, y1, color, lineThickness, shapeType, filled);
		tmp.push(shape);
		i--;
	}
	StackMethods.copy(tmp, stackShaps);
	return tmp;
}
}
