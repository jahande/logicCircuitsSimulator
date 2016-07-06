package ir.sharif.ce.javaClass.logicCircuit.gates;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Output;

import java.awt.Graphics;

public class NandGate extends Gate {

	public NandGate(Input[] inputs, Output output) {
		super(inputs, output);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void compute() {

		for (Input i : this.inputs) {
			if (!i.getValue()) {
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
		int xOffset = getDims().getX() + (getDims().getWidth() - widthShape)
				/ 2;
		int yOffset = getDims().getY() + (getDims().getHeight() - heightShape)
				/ 2;
		g.setColor(getGateColor());
		g.drawLine(xOffset, getDims().getY(), xOffset, getDims().getHeight()
				+ getDims().getY());
		g.fillRect(xOffset, yOffset, widthShape / 2, heightShape);
		g.fillArc(xOffset, yOffset, widthShape, heightShape, 270, 180);

		int littleWidth = widthShape / 10;
		g.drawOval(xOffset + widthShape, getDims().getY()
				+ getDims().getHeight() / 2 - littleWidth / 2, littleWidth,
				littleWidth);
		/**
		 * drawing Wires
		 */
		int inputYStep = getDims().getHeight() / getInputs().length;
		int inputYOffset = getDims().getY()
				+ (int) (getDims().getHeight() * .5 / getInputs().length);
		for (int i = 0; i < getInputs().length; i++) {
			getInputs()[i].setRegions(getDims().getX(), i * inputYStep
					+ inputYOffset, (getDims().getWidth() - widthShape) / 2,
					inputYStep / 2);
			getInputs()[i].draw(g);
		}
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
