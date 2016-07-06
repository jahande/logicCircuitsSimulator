package ir.sharif.ce.javaClass.logicCircuit;

import java.awt.Color;
import java.awt.Graphics;
import ir.sharif.ce.javaClass.logicCircuit.ui.Drawable;
import ir.sharif.ce.javaClass.logicCircuit.utils.GraphicalCordination;
import ir.sharif.ce.javaClass.logicCircuit.utils.Utils;

public class Wire implements Input, Output, Drawable {

	private GraphicalCordination cordinate = new GraphicalCordination(0, 0, 0,
			0);
	private boolean value;
	final private String wireName;
	final private Color wireColor = Utils.getRandomColor();

	public Wire(String name, boolean value) {
		this.value = value;
		this.wireName = name;

	}

	public String toString() {
		return this.wireName;
	}

	public boolean getValue() {
		return this.value;
	}

	public void draw(Graphics g) {
		g.setColor(this.wireColor);
		g.drawLine(this.cordinate.getX(), cordinate.getY(), cordinate.getX()
				+ this.cordinate.getWidth(), cordinate.getY());
		g.setFont(Utils.getResizedFont(g.getFont(), toString(), cordinate.getWidth(),cordinate.getHeight()));
		g.drawString(toString(), cordinate.getX(), cordinate.getY());
	}

	public void setRegions(int x, int y, int width, int height) {
		this.cordinate.setCordination(x, y, width, height);
	}

	public void setValue(boolean value) {

		this.value = value;

	}

	public void switchValue() {
		this.value=!this.value;
	}

	public Color getWireColor() {
		return wireColor;
	}
}
