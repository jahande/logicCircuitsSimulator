package ir.sharif.ce.javaClass.logicCircuit.utils;

public class GraphicalCordination {

	private int x;
	private int y;
	private int width;
	private int height;
	

	public void setCordination(int x,int y, int width, int height) {
		setHeight(height);
		setWidth(width);
		setX(x);
		setY(y);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		if (x < 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if (y < 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		if (width < 0) {
			this.width = 0;
		} else {
			this.width = width;
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		if (height < 0) {
			this.height = 0;
		} else {
			this.height = height;
		}
	}

	public GraphicalCordination(int x, int y, int width, int height) {
		super();
		setCordination(x, y, width, height);
	}

}
