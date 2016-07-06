package ir.sharif.ce.javaClass.logicCircuit;

import java.awt.Color;
import java.awt.Graphics;

import ir.sharif.ce.javaClass.logicCircuit.utils.GraphicalCordination;
import ir.sharif.ce.javaClass.logicCircuit.utils.Utils;
import static ir.sharif.ce.javaClass.logicCircuit.utils.Utils.getResizedFont;

import ir.sharif.ce.javaClass.logicCircuit.gates.Gate;
import ir.sharif.ce.javaClass.logicCircuit.ui.Drawable;

public class Module implements Drawable {

	private final String moduleName;
	private final Color moduleColor = Utils.getRandomColor();
	private final Input[] inputs;
	private final Output[] outputs;
	private final Gate[] gates;
	private Thread[] threads;
	private GraphicalCordination MC = new GraphicalCordination(0, 0, 0, 0);

	protected void setThreads(Thread[] threads) {
		this.threads = threads;
	}

	protected Thread[] getThreads() {
		return threads;
	}

	public Module(String moduleName, Input[] inputs, Output[] outputs,
			Gate[] gates) {
		super();
		this.moduleName = moduleName;
		this.inputs = inputs;
		this.outputs = outputs;
		this.gates = gates;
		this.startWork();
	}

	/**
	 * start for first time and reset for after that
	 */
	public void startWork() {
		this.setThreads(new Thread[getGates().length]);
		int j = 0;
		for (Gate i : gates) {
			this.setThread(j, new Thread(i));
			this.getThread(j).start();
			j++;
		}
	}

	public Gate[] getGates() {
		return this.gates;
	}

	public String toString() {
		return this.moduleName;
	}

	public void draw(Graphics g) {
		g.setColor(this.moduleColor);
		drawName(g);
		drawRectangle(g);
		drawWires(g);
	}

	private void drawName(Graphics g) {
		/**
		 * drawing Module name
		 */
		g.setFont(getResizedFont(g.getFont(), this.moduleName,
				MC.getWidth() / 3, (MC.getHeight() * 3 / 8)));
		g.drawString(this.moduleName, MC.getX() + ( MC.getWidth() / 3), MC
				.getY()
				+ MC.getHeight() / 2);

	}

	private void drawWires(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.inputs.length; i++) {
			this.inputs[i]
					.setRegions(
							MC.getX(),
							(int) (MC.getY() + MC.getHeight()
									* (0.125 + .75 * (double) i
											/ this.inputs.length + .5 * .75 / this.inputs.length)),
							(int) (MC.getWidth() / 4.0),(int)( MC.getHeight() *0.75* .3
									/ this.inputs.length));
			this.inputs[i].draw(g);
		}
		for (int j = 0; j < this.outputs.length; j++) {
			this.outputs[j]
					.setRegions(
							MC.getX() + (int) (MC.getWidth() * .75),
							(int) (MC.getY() + MC.getHeight()
									* (0.125 + .75 * (double) j
											/ this.outputs.length + .5 * .75 / this.outputs.length)),
							(int) (MC.getWidth() / 4.0), (int)(MC.getHeight()*.75 * .3
									/ this.outputs.length));
			this.outputs[j].draw(g);
		}
	}

	private void drawRectangle(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(MC.getX() + (int) (MC.getWidth() / 4.0), MC.getY()
				+ (int) (MC.getHeight() / 8.0), (int) (MC.getWidth() / 2.0),
				(int) (MC.getHeight() * .75));
	}

	// public void setXModule(int x) {
	// MC.se
	// }
	//
	// public void setYModule(int y) {
	// if (y < 0) {
	// this.cordinates[1] = 0;
	// } else {
	// this.cordinates[1] = y;
	// }
	// }
	//
	// public void setWidthModule(int width) {
	// if (width < 0) {
	// this.cordinates[2] = 0;
	// } else {
	// this.cordinates[2] = width;
	// }
	// }
	//
	// public void setHeightModule(int height) {
	// if (height < 0) {
	// this.cordinates[3] = 0;
	// } else {
	// this.cordinates[3] = height;
	// }
	// }
	//
	// public int getXModule() {
	// return this.cordinates[0];
	// }
	//
	// public int getYModule() {
	// return this.cordinates[1];
	// }
	//
	// public int getWidthModule() {
	// return this.cordinates[2];
	// }
	//
	// public int getHeightModule() {
	// return this.cordinates[3];
	// }

	public void setRegions(int x, int y, int width, int height) {
		this.MC.setCordination(x, y, width, height);
	}

	private void setThread(int ind, Thread thread) {
		this.threads[ind] = thread;
	}

	private Thread getThread(int ind) {
		return this.threads[ind];
	}
}
