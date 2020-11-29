package shapescontroller;


class SNode{
	Object data;
	SNode next;
}
class LinkedList{
	SNode head;
	SNode tail;
	int size=0;
}
public class StackMethods  {
	LinkedList s=new LinkedList();
	public Object pop() {
		if(s.size==1) {
			SNode h=s.head;
			s.head=s.tail=null;
			s.size--;
			return h.data;
		}
		else if(s.size==2) {
			SNode tmp=s.head.next;
			s.head.next=null;
			s.tail=s.head;
			s.size--;
			return tmp.data;
		}
		else if(s.size>2) {
		SNode h=s.head;
		SNode tmp=new SNode();
		while(h.next.next!=null) {
			h=h.next;
		}
		tmp.data=h.next.data;
		tmp.next=null;
		h.next=null;
		s.tail=h;
		s.size--;
		return tmp.data;
		}
		else{
			throw new RuntimeException("The Stack is empty (pop)");
		}
		
	}
	
	
	public Object peek() {
		if(s.size>0) {
		return s.tail.data;
		}
		else {
			throw new RuntimeException("The Stack is empty (peek)");
		}
	}
	
	
	public void push(Object element) {
		//Add last
		SNode tmp=new SNode();
		tmp.data=element;
		tmp.next=null;
		if(s.size==0) {
			//add at head
			s.head=tmp;
			s.tail=s.head;
			s.size++;
		}
		else {
			SNode h=s.head;
			while(h.next!=null) {
				h=h.next;
			}
			h.next=tmp;
			s.tail=tmp;
			s.size++;
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static StackMethods getNumberOfOcuurencesOfElementID(StackMethods st,int id) {
		Shape x= (Shape)st.peek();
		if(st.isEmpty()||st==null) {
			return null;
		}else {
			StackMethods tmp=st;
			StackMethods occ=new StackMethods();
			while(tmp.isEmpty()==false) {
				if(id==((Shape)(tmp.peek())).id) {
				occ.push(tmp.pop());
				}else {
					tmp.pop();
				}
			}
			return occ;
		}
	}
	///////////////////////////////////////////////////////////////////////////////////
	public static StackMethods ReverseStack(StackMethods st) {
		StackMethods tmp =new StackMethods();
		while(st.isEmpty()==false) {
			tmp.push(st.pop());
		}
		st=tmp;return tmp;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	public static StackMethods getNumberOfOcuurencesOfLastElementID(StackMethods st) {
		Shape x= (Shape)st.peek();
		if(st.isEmpty()||st==null) {
			return null;
		}else {
			StackMethods tmp=st;
			StackMethods occ=new StackMethods();
			while(tmp.isEmpty()==false) {
				if(x.id==((Shape)(tmp.peek())).id) {
				occ.push(tmp.pop());
				}else {
					tmp.pop();
				}
			}
			return occ;
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	public StackMethods GetShapeById(StackMethods shapes,int id,Shape ModifiedShape) {
		StackMethods sh1=shapes;
		while(shapes.isEmpty()==false) {
			if(((Shape)shapes.peek()).id==id) {
				//we got shape with this id 
				sh1.push(ModifiedShape);
			}else {
			sh1.push(shapes.pop());
			}
		}
		//the elements are revered in the stack
		//so return them again in shapes stack
		while(sh1.isEmpty()==false) {
			shapes.push(sh1.pop());
		}
		return shapes;
	}
	////////////////////////////////////////////////////////////////////////////////////
	
	public StackMethods DelShapeById(StackMethods shapes,int id) {
		StackMethods sh1=shapes;
		while(shapes.isEmpty()==false) {
			if(((Shape)shapes.peek()).id==id) {
				//we got shape with this id 
				//we willnot add it in this stack
			}else {
			sh1.push(shapes.pop());
			}
		}
		//the elements are revered in the stack
		//so return them again in shapes stack
		while(sh1.isEmpty()==false) {
			shapes.push(sh1.pop());
		}
		return shapes;
	}
	/////////////////////////////////////////////////////////////////
	public boolean isEmpty() {
		if(s.size==0||s.head==null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//////////////////////////////////////////////////////////
	public int size() {
		return s.size;
	}
	
	////////////////////////////////////////////////////////
}


