package SamplesMain;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Module;
import ir.sharif.ce.javaClass.logicCircuit.Output;
import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.gates.Gate;
import ir.sharif.ce.javaClass.logicCircuit.gates.NandGate;
import ir.sharif.ce.javaClass.logicCircuit.gates.NotGate;
import ir.sharif.ce.javaClass.logicCircuit.ui.Monitor;
import ir.sharif.ce.javaClass.logicCircuit.ui.ToggleSwitcher;
import ir.sharif.ce.javaClass.logicCircuit.ui.Window;

public class MainD {
	public static void main(String[] args) {
		Wire q = new Wire("q", false), qbar = new Wire("qbar", false), d = new Wire(
				"d", false), clk = new Wire("clk", false), s = new Wire("s",
				false), sbar = new Wire("sbar", false), r = new Wire("r", false), rbar = new Wire(
				"rbar", false), nclk = new Wire("nclk", false);
		NotGate notClkGate = new NotGate(clk, nclk);
		NandGate nandSbarGate = new NandGate(new Input[] { rbar, s }, sbar);
		NandGate nandSGate = new NandGate(new Input[] { sbar, nclk }, s);
		NandGate nandRGate = new NandGate(new Input[] { s, nclk, rbar }, r);
		NandGate nandRbarGate = new NandGate(new Input[] { r, d }, rbar);
		NandGate nandQGate = new NandGate(new Input[] { s, qbar }, q);
		NandGate nandQbarGate = new NandGate(new Input[] { q, r }, qbar);
		notClkGate.setRegions(0, 200, 50, 50);
		nandSbarGate.setRegions(200, 200, 500, 50);
		nandSGate.setRegions(200, 400, 50, 50);
		nandRGate.setRegions(200, 450, 50, 50);
		nandRbarGate.setRegions(200, 350, 50, 50);
		nandQGate.setRegions(350, 150, 50, 50);
		nandQbarGate.setRegions(350, 250, 50, 50);
		Monitor monitor = new Monitor(d);
		monitor.setBounds(600, 50, 100, 100);
		Monitor monitor2 = new Monitor(clk);
		monitor2.setBounds(600, 200, 100, 100);
		Monitor monitor3 = new Monitor(q);
		monitor3.setBounds(600, 350, 100, 100);
		ToggleSwitcher togglerD = new ToggleSwitcher(d);
		togglerD.setBounds(75, 150, 100, 30);
		ToggleSwitcher togglerClk = new ToggleSwitcher(clk);
		togglerClk.setBounds(75, 110, 100, 30);
		Gate[] gates = new Gate[] { notClkGate, nandSbarGate, nandSGate,
				nandRGate, nandRbarGate, nandQGate, nandQbarGate };
		Module module = new Module("D Flip-Flop", new Input[] { d, clk },
				new Output[] { q, qbar }, gates);
		module.setRegions(10, 10, 130, 130);
		Monitor[] monitors = new Monitor[] { monitor, monitor2, monitor3 };
		ToggleSwitcher[] togglers = new ToggleSwitcher[] { togglerD, togglerClk };
		new Window(module, monitors, togglers);
	}
}
