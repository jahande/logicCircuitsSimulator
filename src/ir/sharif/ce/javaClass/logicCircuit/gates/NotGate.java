package ir.sharif.ce.javaClass.logicCircuit.gates;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Output;

import java.awt.Graphics;

public class NotGate extends Gate {

	public NotGate(Input inputs, Output output) {
		super(new Input[] { inputs }, output);
	}

	@Override
	public void compute() {

		this.setOutputValue(!(this.getInputs()[0]).getValue());
	}

	public void draw(Graphics g) {
		/**
		 * drawing Shape
		 */
		int widthShape = 2 * getDims().getWidth() / 4;
		int heightShape = getDims().getHeight();
		int xOffset = getDims().getX() + (getDims().getWidth() - widthShape)
				/ 2;
		int yOffset = getDims().getY();
		g.setColor(getGateColor());
		g.fillPolygon(new int[] { xOffset, xOffset + widthShape, xOffset },
				new int[] { yOffset, yOffset + heightShape / 2,
						yOffset + heightShape }, 3);

		int littleWidth = widthShape / 10;
		g.drawOval(xOffset + widthShape, getDims().getY()
				+ getDims().getHeight() / 2 - littleWidth / 2, littleWidth,
				littleWidth);
		/**
		 * drawing Wires
		 */
		int inputYOffset = getDims().getY() + getDims().getHeight() / 2;
		getInputs()[0].setRegions(getDims().getX(), inputYOffset, (getDims()
				.getWidth() - widthShape) / 2,
				(getDims().getWidth() - widthShape) / 2);
		getInputs()[0].draw(g);
		/**
		 * out put
		 */
		getOutput()
				.setRegions(
						xOffset + widthShape + littleWidth,
						getDims().getY() + getDims().getHeight() / 2,
						(int) (getDims().getWidth() - widthShape - 2 * littleWidth) / 2,
						heightShape / 4);
		getOutput().draw(g);
	}
}
