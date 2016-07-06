package SamplesMain;

import ir.sharif.ce.javaClass.logicCircuit.*;
import ir.sharif.ce.javaClass.logicCircuit.gates.*;
import ir.sharif.ce.javaClass.logicCircuit.ui.Monitor;
import ir.sharif.ce.javaClass.logicCircuit.ui.ToggleSwitcher;
import ir.sharif.ce.javaClass.logicCircuit.ui.Window;
//import ir.sharif.ce.javaClass.logicCircuit.ui.Window;

public class Main1 {
	public static void main(String[] args) {
		//System.out.println(true);
		Wire i0 = new Wire("i0", false);
		Wire i1 = new Wire("i1", false);
		Wire outAnd = new Wire("oAnd", false);
		Wire outOr = new Wire("oOr", false);
		Gate andGate = new AndGate(new Input[] { i0, i1 }, outAnd);
		Gate orGate = new OrGate(new Input[] { i0, i1 }, outOr);
		andGate.setRegions(200, 200, 0, 0);
		orGate.setRegions(200, 50, 0, 0);
		Module andOrModule = new Module("And Or Module",
				new Input[] { i0, i1 }, new Output[] { outAnd, outOr },
				new Gate[] { andGate, orGate });
		Monitor i0Monitor = new Monitor(i0);
		Monitor i1Monitor = new Monitor(i1);
		Monitor outAndMonitor = new Monitor(outAnd);
		Monitor outOrMonitor = new Monitor(outOr);
		outAndMonitor.setBounds(0, 30, 100, 80);
		outOrMonitor.setBounds(0, 150, 100, 80);
		i0Monitor.setBounds(0, 270, 100, 80);
		i1Monitor.setBounds(0, 390, 100, 80);
		ToggleSwitcher i0Toggle = new ToggleSwitcher(i0);
		ToggleSwitcher i1Toggle = new ToggleSwitcher(i1);
		andOrModule.setRegions(10, 10, 130, 130);
		i0Toggle.setBounds(75, 110, 100, 30);
		i1Toggle.setBounds(75, 150, 100, 30);
		new Window(andOrModule, new Monitor[] { i0Monitor, i1Monitor,
				outAndMonitor, outOrMonitor }, new ToggleSwitcher[] { i0Toggle,
				i1Toggle });
	}
}
