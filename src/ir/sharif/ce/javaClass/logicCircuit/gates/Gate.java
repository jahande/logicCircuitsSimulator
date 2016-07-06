package ir.sharif.ce.javaClass.logicCircuit.gates;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Output;
import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.ui.Drawable;
import ir.sharif.ce.javaClass.logicCircuit.utils.GraphicalCordination;
import java.awt.Color;

public abstract class Gate implements Runnable, Drawable {
	protected Input[] inputs;
	protected Output output;
	protected GraphicalCordination dims = new GraphicalCordination(0,0,0,0);
	protected final Color gateColor = ir.sharif.ce.javaClass.logicCircuit.utils.Utils.getRandomColor();

	/*
	 * public Gate() {
	 *  }
	 */
	
	
	public Gate(Input[] inputs, Output output) {
		this.inputs = inputs;
		this.output = output;
	}

	protected Input[] getInputs() {
		return inputs;
	}

	protected void setInputs(Input[] inputs) {
		this.inputs = inputs;
	}

	protected Output getOutput() {
		return output;
	}

	protected void setOutput(Output output) {
		this.output = output;
	}

	protected void setOutputValue(boolean value) {
		this.output.setValue(value);
	}

	public boolean getOutPut() {
		return ((Wire) this.output).getValue();
	}

	protected long getThisComponentDelay() {
		return GateDefaultDelay.getDelayValue();
	}
	
	protected void performDelay() {
		try{
		Thread.sleep(getThisComponentDelay());
		}catch(InterruptedException e)
		{
			System.out.println("Catched Ecception:");
			e.printStackTrace();
			System.out.println("End Catching");
		}
	}

	protected GraphicalCordination getDims() {
		return dims;
	}

	protected void setDims(GraphicalCordination dims) {
		this.dims = dims;
	}

	public abstract void compute();

	public void run() {
		while (true) {
			performDelay();
			compute();
		}
		
	}

	public void setRegions(int x, int y, int width, int height) {
		this.dims.setCordination(x, y, width, height);
	}

	protected Color getGateColor() {
		return gateColor;
	}
	
}