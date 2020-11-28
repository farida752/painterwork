package shapescontroller;

public class Shape {
	
	float x;
	float y;
	float x1;
	float y1;
	String color;
	String lineThickness;
	
	public Shape(float x, float y, float x1, float y1, String color, String lineThickness) {
		super();
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		this.color = color;
		this.lineThickness = lineThickness;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX1() {
		return x1;
	}
	public void setX1(float x1) {
		this.x1 = x1;
	}
	public float getY1() {
		return y1;
	}
	public void setY1(float y1) {
		this.y1 = y1;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLineThickness() {
		return lineThickness;
	}
	public void setLineThickness(String lineThickness) {
		this.lineThickness = lineThickness;
		//
		// staksteps.push  new shape (this.x,this.y)
		 // search for shape which inciude the given point from frontend to edit it then in stack of shapes 
		 
	}
	public boolean isInclude(float a,float b) {
		if(x<a&&x1>a&&y<b&&y1>b) {
			return true;
		}else {
			return false;
		}
	}
	

}
