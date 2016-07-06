package ir.sharif.ce.javaClass.logicCircuit.ui;

import ir.sharif.ce.javaClass.logicCircuit.Module;
import ir.sharif.ce.javaClass.logicCircuit.Constants.SleepTime;
import ir.sharif.ce.javaClass.logicCircuit.gates.Gate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WindowEXT extends JFrame {
	// private final ArrayList<JButton> button = new ArrayList<JButton>(5);
	private JLabel enterSleep = new JLabel();
	private JTextField textField = new JTextField();
	private JButton[] toggleSwitchers;
	private Monitor[] monitors;
	private Module wMadule;
	private JButton changeButton = new JButton();
	boolean drawedConstantShapes = false;

	public WindowEXT(Module module, Monitor[] monitors,
			ToggleSwitcher[] toggleSwitchers) {
		// super();
		// this.setBounds(100, 100, 800, 600);
		// this.setResizable(false);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setTitle("In The Name Of God");
		// this.toggleSwitchers = toggleSwitchers;
		// this.monitors = monitors;
		// this.wMadule = module;

		try {
			windowInit();
			// while (true) {
			// Thread.sleep(SleepTime.getSleepTime());
			// this.repaint(SleepTime.getSleepTime());
			// }
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void addTogglerToContentPain() {
		for (JButton i : this.toggleSwitchers) {
			getContentPane().add(i);
		}
	}

	public void addMonitors() {
		try {
			for (Monitor i : this.monitors) {
				getContentPane().add(i);
			}
		} catch (NullPointerException n) {
			throw new ModuleNotPassedCorrectly(
					"the passed monitors param is null");
		}
	}

	private void windowInit() throws Exception {
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setBounds(100, 100, 200, 200);
		this.setVisible(true);

		// getContentPane().setLayout(null);
		// this.setBackground(Color.WHITE);
		// this.addTogglerToContentPain();
		// try {
		// this.addMonitors();
		// } catch (ModuleNotPassedCorrectly m) {
		// System.out.println(m.getMessage());
		// m.printStackTrace();
		// System.out.println(m.getMessage());
		// }
		//
		// this.changeButton.setText("Change");
		// this.enterSleep.setText("Enter sleep time");
		// this.enterSleep.setBounds(400, 50, 100, 30);
		// this.textField.setBounds(400, 125, 100, 30);
		// this.changeButton.setBounds(400, 200, 100, 30);
		// this.add(this.changeButton);
		// this.add(this.enterSleep);
		// this.add(this.textField);
		// this.changeButton.addActionListener(new ChangeButton());
		// this.setVisible(true);
	}

	private Module getWMadule() {
		return wMadule;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.drawedConstantShapes = true;
		drawConstantsPlacesShapes(g);

		try {
			for (Monitor i : this.monitors) {
				i.paint(g);
			}
		} catch (NullPointerException e) {
			try {
				throw new ModuleNotPassedCorrectly(
						"ERROR >> CATCHING EXCEPTION!!!");
			} catch (ModuleNotPassedCorrectly m) {
				System.out.println(m.getMessage());
				e.printStackTrace();
				System.out.println(m.getMessage());
			}

		}

	}

	private void drawConstantsPlacesShapes(Graphics g) {
		// this.wMadule.setRegions(100, 400, 300, 400);
		this.wMadule.draw(g);
		for (Gate i : this.getWMadule().getGates()) {
			i.draw(g);
		}

	}

	private class ChangeButton implements ActionListener {
		// @Override
		public void actionPerformed(ActionEvent e) {
			SleepTime.setSleepTime(Integer.parseInt((WindowEXT.this.textField
					.getText())));
			System.out.println(SleepTime.getSleepTime());
		}
	}

}
