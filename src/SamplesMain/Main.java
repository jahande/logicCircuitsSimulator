package SamplesMain;

import ir.sharif.ce.javaClass.logicCircuit.Input;
import ir.sharif.ce.javaClass.logicCircuit.Module;
import ir.sharif.ce.javaClass.logicCircuit.Output;
import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.gates.*;
import ir.sharif.ce.javaClass.logicCircuit.ui.Monitor;
import ir.sharif.ce.javaClass.logicCircuit.ui.ToggleSwitcher;
import ir.sharif.ce.javaClass.logicCircuit.ui.Window;
import ir.sharif.ce.javaClass.logicCircuit.ui.Constructors.ClonedFIirstFrame;


public class Main {

	static Module module; 
	static Monitor[] monitors;
	static ToggleSwitcher[] toggleSwitchers;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ToggleSwitcher g= new ToggleSwitcher(null);
		// g.pa
		// testNot();
		// testThread();
		// testAndGate();
		//ClonedFIirstFrame.ClonedFIirstFrameMain();
		togglersTest();
		publicTest();
	}

	public static void publicTest() {
		try {
			Window frame = new Window(module, monitors, toggleSwitchers);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void togglersTest() {
		Wire i0 = new Wire("i0", false);
		Wire i1 = new Wire("i1", false);
		Wire outAnd = new Wire("oAnd", false);
		Wire outOr = new Wire("oOr", false);
		Gate andGate = new AndGate(new Input[] { i0, i1 }, outAnd);
		Gate orGate = new OrGate(new Input[] { i0, i1 }, outOr);
		andGate.setRegions(200, 200, 0, 0);
		orGate.setRegions(200, 50, 0, 0);

		ToggleSwitcher i0Toggle = new ToggleSwitcher(i0);
		ToggleSwitcher i1Toggle = new ToggleSwitcher(i1);

		i0Toggle.setBounds(75, 110, 100, 30);
		i1Toggle.setBounds(75, 150, 100, 30);
		Main.module = null;
		Main.toggleSwitchers = new ToggleSwitcher[]{i1Toggle,i0Toggle};
		//Window t = new Window(null, null, new ToggleSwitcher[] { i0Toggle, i1Toggle });
	}

	public static void testThread() {
		Wire wFalse1 = new Wire("w1", true);
		Wire wTrue1 = new Wire("t1", true);
		Wire wFalse2 = new Wire("w1", true);

		Input[] test = { wFalse1, wTrue1, wFalse2 };

		Wire outwire = new Wire("e1", false);

		Gate g = new AndGate(test, outwire);
	}

	public static void testXor() {
		Wire wFalse1 = new Wire("w1", true);
		Wire wTrue1 = new Wire("t1", true);
		Wire wFalse2 = new Wire("w1", true);

		Input[] test = { wFalse1, wTrue1, wFalse2 };

		Wire outwire = new Wire("e1", false);
		// Output outTest = new Output(outTest);

		Gate g = new AndGate(test, outwire);
		g.compute();
		System.out.println(g.getOutPut());
	}

	public static void testNot() {
		Wire wFalse1 = new Wire("w1", false), test = new Wire("w2", false);

		Gate g = new NotGate(wFalse1, test);
		g.compute();
		System.out.println(g.getOutPut());
	}

	public static void testAndGate() {
		Wire wFalse1 = new Wire("w1", true);
		Wire wTrue1 = new Wire("t1", true);
		Wire wFalse2 = new Wire("w1", true);

		Input[] test = { wFalse1, wTrue1, wFalse2 };

		Wire outwire = new Wire("e1", true);
		// Output outTest = new Output(outTest);

		Gate g = new AndGate(test, outwire);
		g.compute();
		System.out.println(g.getOutPut());
	}

}
