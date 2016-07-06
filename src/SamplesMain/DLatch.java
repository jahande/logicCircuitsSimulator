package SamplesMain;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Module;
import ir.sharif.ce.javaClass.logicCircuit.Output;
import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.gates.AndGate;
import ir.sharif.ce.javaClass.logicCircuit.gates.Gate;
//import ir.sharif.ce.javaClass.logicCircuit.gates.NandGate;
import ir.sharif.ce.javaClass.logicCircuit.gates.NotGate;
import ir.sharif.ce.javaClass.logicCircuit.gates.OrGate;
import ir.sharif.ce.javaClass.logicCircuit.ui.Monitor;
import ir.sharif.ce.javaClass.logicCircuit.ui.ToggleSwitcher;
import ir.sharif.ce.javaClass.logicCircuit.ui.Window;

public class DLatch {
	public static void main(String[] args) {
		Wire d = new Wire("D", false);
		Wire c = new Wire("C", false);
		Wire q = new Wire("Q", false);
		Wire q_ = new Wire("Q_", false);
		Wire betweenAbove = new Wire("betweenAbove", true);
		Wire betweenBottom = new Wire("betweenBottom", true);
		Wire notD = new Wire("notD", true);
		Wire notQ = new Wire("notQ", true);
		Wire notQ_ = new Wire("notQ_", true);

		Gate notDGate = new NotGate(d, notD);
		// Gate notDGate = new NotGate(d,notD);
		Gate notQGate = new NotGate(q, notQ);
		Gate notQ_Gate = new NotGate(q_, notQ_);
		Gate aboveand = new AndGate(new Input[] { c, d }, betweenAbove);
		Gate botomand = new AndGate(new Input[] { c, notD }, betweenBottom);

		Gate orLastAbove = new OrGate(new Input[] { betweenAbove, notQ_ }, q);
		Gate orLastbottom = new OrGate(new Input[] { betweenBottom, notQ }, q_);
		// andGate.setRegions(200, 200, 0, 0);
		// orGate.setRegions(200, 50, 0, 0);
		Module dLatch = new Module("DLatch", new Input[] { d, c },
				new Output[] { q, q_ },
				new Gate[] { notQ_Gate, notQGate, notDGate, aboveand, botomand,
						orLastAbove, orLastbottom });
		Monitor dMonitor = new Monitor(d);
		Monitor cMonitor = new Monitor(c);
		Monitor qMonitor = new Monitor(q);
		Monitor q_Monitor = new Monitor(q_);
		qMonitor.setBounds(0, 30, 100, 80);
		q_Monitor.setBounds(0, 150, 100, 80);
		dMonitor.setBounds(0, 270, 100, 80);
		cMonitor.setBounds(0, 390, 100, 80);
		ToggleSwitcher cToggle = new ToggleSwitcher(c);
		ToggleSwitcher dToggle = new ToggleSwitcher(d);
		dLatch.setRegions(10, 10, 130, 130);
		cToggle.setBounds(75, 110, 100, 30);
		dToggle.setBounds(75, 150, 100, 30);
		new Window(dLatch, new Monitor[] { cMonitor, dMonitor,
				qMonitor, q_Monitor }, new ToggleSwitcher[] { cToggle,
				dToggle });
	}

}
