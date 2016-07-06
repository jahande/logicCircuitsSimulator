package SamplesMain;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Module;
import ir.sharif.ce.javaClass.logicCircuit.Output;
import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.ui.Monitor;
import ir.sharif.ce.javaClass.logicCircuit.ui.ToggleSwitcher;
import ir.sharif.ce.javaClass.logicCircuit.ui.Window;

public class TestMonitor {
	public static void main(String[] args) {
		Wire w =new Wire("W_Wire",false);
		Wire out = new Wire("out",false);
		ToggleSwitcher sw = new ToggleSwitcher(w);
		sw.setBounds(100, 400, 200, 30);
		Monitor m = new Monitor(w);
		m.setBounds(300, 100, 200, 200);
		Window w1  = new Window(null,new Monitor[]{m},new ToggleSwitcher[]{sw});
		//Module module  = new Module("NotModule",new Input[]{w},new Output[]{out},);
	}
}
