package ir.sharif.ce.javaClass.logicCircuit.ui.Constructors;

import ir.sharif.ce.javaClass.logicCircuit.Module;
import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.ui.Monitor;
import ir.sharif.ce.javaClass.logicCircuit.ui.ToggleSwitcher;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ClonedFIirstFrame extends JFrame {

	private final ArrayList<JButton> button = new ArrayList<JButton>(10);

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void ClonedFIirstFrameMain() {
		try {
			ClonedFIirstFrame frame = new ClonedFIirstFrame(null,null,null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		
		}

	}

	public void addToggler() {

	}

	public ClonedFIirstFrame(Module module, Monitor[] monitors,
			ToggleSwitcher[] toggleSwitchers) {
		super();
		try {
			jbInit();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		Wire wTrue1 = new Wire("t1hvhv",true);
		ToggleSwitcher t1 = new ToggleSwitcher(wTrue1);
		
		getContentPane().setLayout(null);

		button.add(t1);
		getContentPane().add(button.get(0));
		//button.get(0).setText("ToggleSwitcher "+);
		button.get(0).setBounds(10, 10, 200, 26);
	}

	private class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			button_actionPerformed(arg0);
		}
	}

	protected void button_actionPerformed(ActionEvent arg0) {
		System.out.println("hehe");
	}

}
