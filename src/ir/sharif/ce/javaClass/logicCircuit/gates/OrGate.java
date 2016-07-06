package ir.sharif.ce.javaClass.logicCircuit.gates;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Output;

import java.awt.Graphics;

public class OrGate extends Gate {

	public OrGate(Input[] inputs, Output output) {
		super(inputs, output);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void compute() {

		for (Input i : this.inputs) {
			if (i.getValue()) {
				setOutputValue(true);
				return;
			}
		}
		setOutputValue(false);
	}

	public void draw(Graphics g) {
		/**
		 * drawing Shape
		 */
		int widthShape = 2 * getDims().getWidth() / 4;
		int heightShape = getDims().getHeight() * 3 / getInputs().length;
		int xOffset = getDims().getX() + (getDims().getWidth() -widthShape)/2;
		int yOffset = getDims().getY() + (getDims().getHeight() - heightShape)/ 2;
		int behindArcWidth = widthShape/4;
				
		g.setColor(getGateColor());
		g.drawArc(xOffset-behindArcWidth/2, yOffset, behindArcWidth, heightShape, 270, 180);
		g.drawLine(xOffset, yOffset, xOffset+widthShape, yOffset+heightShape/2);
		g.drawLine(xOffset, yOffset+heightShape, xOffset+widthShape, yOffset+heightShape/2);
		g.drawLine(xOffset, getDims().getY(), xOffset , yOffset);
		g.drawLine(xOffset, yOffset+heightShape, xOffset , getDims().getY()+getDims().getHeight());
		
		/**
		 * drawing Wires
		 */
		int inputYStep= getDims().getHeight()/getInputs().length;
		int inputYOffset = getDims().getY()+(int)(getDims().getHeight()*.5/getInputs().length);
		for (int i = 0; i < getInputs().length; i++) {
			getInputs()[i].setRegions(getDims().getX(), i*inputYStep+inputYOffset,( getDims().getWidth()-widthShape)/2, inputYStep/2);
			getInputs()[i].draw(g);
		}
		/**
		 * out put
		 */
		getOutput().setRegions(xOffset+widthShape, getDims().getY()+getDims().getHeight()/2, (getDims().getWidth()-widthShape)/2, heightShape/4);
		getOutput().draw(g);
	}

}
