package shapescontroller;

public class Triangle extends Shape {

	String name;

	public Triangle(String name ,float x, float y, float x1, float y1, String color, String lineThickness) {
		super(x, y, x1, y1, color, lineThickness);
		// TODO Auto-generated constructor stub
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
