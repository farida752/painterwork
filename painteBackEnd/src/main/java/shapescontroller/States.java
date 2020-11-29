package shapescontroller;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class States  {
//singleton class that holds three stacks that saves the operations and they will be full of data from the factory of shapes
	private   StackMethods stackShaps;
	private   StackMethods redoStack;
	private   StackMethods stepsStack;
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
	}
	@GetMapping("/redo")
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
	Object[][] arr= new Object[stackShaps.size()][7];
	//a copy from the stack given to pop from it 
	StackMethods s = given;
	//int id,float x, float y, float x1, float y1, String color, String lineThickness
	int i=0;
	while(s.isEmpty()==false) {
		arr[i][0]=((Shape)s.peek()).id;
		arr[i][1]=((Shape)s.peek()).x;
		arr[i][2]=((Shape)s.peek()).y;
		arr[i][3]=((Shape)s.peek()).x1;
		arr[i][4]=((Shape)s.peek()).y1;
		arr[i][5]=((Shape)s.peek()).color;
		arr[i][6]=((Shape)s.peek()).lineThickness;
		arr[i][7]=((Shape)s.peek()).shapeType;
		i++;
		s.pop();
	}
	return arr;
}

/////////////////////////////////////////////////////////////////////////////
//function to send the stack of shapes as array of points to the front end 
@GetMapping("/sendStackOfShapesToFrontEnd")
public Object[][] sendStackOfShapesToFrontEnd() {
	return this.convertStackToArray(stackShaps);
}
///////////////////////////////////////////////////////////////////////
//search stack of shapes and return all the shapes include this point 
public  StackMethods getIncluded(float a,float b){
	//loop in stack shapes and search then return included shapes
	StackMethods s=stackShaps;
	StackMethods included=new StackMethods();
	while(s.isEmpty()==false) {
		if((((Shape)s.peek()).x<=a)&&
				(((Shape)s.peek()).x1>=a)&&
				(((Shape)s.peek()).y<=b)&&
				(((Shape)s.peek()).y1>=b)) {
			included.push(s.peek());
		}
		s.pop();
	}
	return included;
}
}
