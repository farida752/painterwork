package shapescontroller;

public class XMLReturn {
	
	 private Object[][] shapes;
	    private Object[] steps;

	    public XMLReturn( Object [][]shapes,Object[]steps) {
	        this.shapes = shapes;
	        this.steps = steps;
	    }

		public Object[][] getShapes() {
			return shapes;
		}

		

		public Object[] getSteps() {
			return steps;
		}


}
