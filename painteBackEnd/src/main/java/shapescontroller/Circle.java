package shapescontroller;


public class Circle extends Shape {
	
	double radius ;
	double xMax;
	double xMin;
	double yMax;
	double yMin;
	
	

	public Circle(int id, double x, double y, double x1, double y1, String color, String lineThickness, String shapeType,boolean filled) {
		super(id, x, y, x1, y1, color, lineThickness, shapeType,filled);
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean isInclude(double a ,double b) {
		radius = Math.sqrt( Math.pow((x-x1),2) + Math.pow((y-y1),2));
		 xMax = x+radius;
		 xMin = x-radius;
		 yMax = y+radius;
		 yMin  = y-radius;
		if(xMin<=a&&xMax>=a&&yMin<=b&&yMax>=b) {
			return true;
		}else {
			return false;
		}
	}
	
	
	

}
