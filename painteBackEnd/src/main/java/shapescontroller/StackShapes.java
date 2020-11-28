package shapescontroller;

import java.util.Stack;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class StackShapes extends StackMethods {

	private  static StackShapes stackShaps;
	private  static StackShapes redoStack;
	private  static StackShapes stepsStack;

	
	private  StackShapes() {
		
	}
	
	public static StackShapes getStackShapes() {
		if(stackShaps==null) {
			synchronized (StackShapes.class) {
				if(stackShaps==null) {
					stackShaps=new StackShapes();
				}
			}
		}
		return stackShaps;
	}
	@GetMapping("/stackchangedfromfrontend")
	public void setStackSteps(StackShapes newstackSteps) {
		this.stackShaps = newstackSteps;
		
	}
	@GetMapping("/undo")
	public StackShapes undo () {
		redoStack.push(stepsStack.pop());
		return stepsStack;	
	}
	@GetMapping("/redo")
	public StackShapes redo () {
		stepsStack.push(redoStack.pop());
		return stepsStack;	
	}
	@GetMapping("/array")
	public int[] array () {
		return new int [] {1,2,3};
	}
	
	// function takes a ,b and return all the included shapes from the stack.
}

