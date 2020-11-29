package shapescontroller;

public class Shape {
	
    float x;
	float y;
	float x1;
	float y1;
	String color;
	String lineThickness;
	int id;
	String shapeType;
	
	public Shape(int id,float x, float y, float x1, float y1, String color, String lineThickness,String shapeType) {
		super();
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		this.color = color;
		this.lineThickness = lineThickness;
		this.id=id;
		this.shapeType=shapeType;
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
	public int getId() {
		return id;
	}
	public String getLineThickness() {
		return lineThickness;
	}
	///////////////////////////////////////////////////////////////////////////
	public void setLineThickness(String lineThickness) {
		this.lineThickness = lineThickness;
		//
		// staksteps.push  new shape (this.x,this.y)
		 // search for shape which inciude the given point from frontend to edit it then in stack of shapes 
		 
	}
	
	///////////////////////////////////////////////////////////////////////
	public boolean isInclude(float a,float b) {
		if(x<a&&x1>a&&y<b&&y1>b) {
			return true;
		}else {
			return false;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	public Shape copy(float x2,float y2,int idNew,float a,float b) {
		Shape copied;
                float distanceX = Math.abs(x2-a);
                float distanceY = Math.abs(y2-b);
                float xx=x+distanceX;
                float xx1=x1+distanceX;
                float yy=y+distanceY;
                float yy1=y1+distanceY;
		if(x>x2) {
			//we should move left
			xx=x-distanceX;
		}
		if(y>y2){//we should move left
			yy=y-distanceY;
		}
		if(x1>x2) {//we should move left
			xx1=x1-distanceX;
		}
		if(y1>y2) {//we should move left
			yy1=y1-distanceY;
		}
		copied=new Shape(idNew,xx, yy, xx1, yy1, color, lineThickness,shapeType);
		return copied;
	}
	

}
