package ir.sharif.ce.javaClass.logicCircuit.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ir.sharif.ce.javaClass.logicCircuit.Module;
import ir.sharif.ce.javaClass.logicCircuit.Constants.SleepTime;
import ir.sharif.ce.javaClass.logicCircuit.gates.Gate;
import ir.sharif.ce.javaClass.logicCircuit.gates.GateDefaultDelay;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Window extends JFrame {

	final static long startTime = System.currentTimeMillis();

	private final JPanel gatesPanel;
	private final JLabel enterSleep = new JLabel();
	private final JTextField textField = new JTextField();
	private final ToggleSwitcher[] toggleSwitchers;
	private final Monitor[] monitors;
	private final Module wMadule;
	private final JButton changeButton = new JButton();
	boolean drawedConstantShapes = false;

	public Window(Module module, Monitor[] monitors,
			ToggleSwitcher[] toggleSwitchers) {
		super();
		this.setBounds(100, 100, 800, 600);

		this.gatesPanel = new GatesPanel(module.getGates(), this, null, true);

		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("In The Name Of God");
		this.toggleSwitchers = toggleSwitchers;
		this.monitors = monitors;
		this.wMadule = module;

		try {
			windowInit();
			while (true) {
				Thread.sleep(SleepTime.getSleepTime());
				this.repaint(SleepTime.getSleepTime());
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void addTogglerToContentPain() {

		for (ToggleSwitcher i : this.toggleSwitchers) {
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

		this.add(this.gatesPanel);
		getContentPane().setLayout(null);
		this.setBackground(Color.WHITE);
		this.addTogglerToContentPain();
		try {
			this.addMonitors();
		} catch (ModuleNotPassedCorrectly m) {
			System.out.println(m.getMessage());
			m.printStackTrace();
			System.out.println(m.getMessage());
		}

		this.changeButton.setText("Change");
		this.enterSleep.setText("Enter sleep time");
		this.enterSleep.setBounds(getWidth() / 20, getHeight() * 5 / 8,
				getWidth() / 8, getHeight() / 15);
		this.textField.setBounds(getWidth() / 20, getHeight() * 3 / 4,
				getWidth() / 8, getHeight() / 15);
		this.textField.setText("200");
		this.changeButton.setBounds(getWidth() / 20, getHeight() * 7 / 8,
				getWidth() / 8, getHeight() / 15);
		this.add(this.changeButton);
		this.add(this.enterSleep);
		this.add(this.textField);
		this.changeButton.addActionListener(new ChangeButton());
		this.setVisible(true);
	}

	private Module getWMadule() {
		return wMadule;
	}

	boolean entrance = true;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		drawConstants(g);
		if (this.entrance) {
			this.entrance = false;
			try {
				/**
				 * main and important part
				 */
				for (Monitor i : this.monitors) {
					i.repaint();
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
	}

	private JPanel getGatesPanel() {
		return gatesPanel;
	}

	private void drawConstants(Graphics g) {
		// this.wMadule.setRegions(100, 400, 300, 400);
		this.wMadule.draw(g);
		getGatesPanel().repaint();

	}

	private class ChangeButton implements ActionListener {
		// @Override
		public void actionPerformed(ActionEvent e) {
			SleepTime.setSleepTime(Integer.parseInt((Window.this.textField
					.getText())));
			GateDefaultDelay.setDelayValue((int)(1 + SleepTime.getSleepTime() / 100));
			System.out.println(SleepTime.getSleepTime());
		}
	}

}

class ModuleNotPassedCorrectly extends RuntimeException {
	public ModuleNotPassedCorrectly(String s) {
		super(s);
	}
}

class GatesPanel extends JPanel {
	private final Gate[] gates;
	private final JFrame thisFrame;

	public GatesPanel(Gate[] g, JFrame f, LayoutManager layout,
			boolean isDoubledBufferd) {
		super(layout, isDoubledBufferd);
		this.gates = g;
		this.thisFrame = f;
		this.init();
	}

	public GatesPanel(Gate[] g, JFrame f) {
		this.gates = g;
		this.thisFrame = f;
		this.init();
	}

	private void init() {

		int panelTWidth = this.thisFrame.getWidth() * 2 / 5;
		int panelTHeight = this.thisFrame.getHeight() * 4 / 5;
		int XOffset = (this.thisFrame.getWidth() - panelTWidth) / 2;
		int YOffset = (this.thisFrame.getHeight() - panelTHeight) / 2;
		System.out.println(panelTHeight + "fghjk" + panelTWidth + "XJ"
				+ XOffset + "Y" + YOffset);
		this.setBackground(Color.white);

		this.setBounds(XOffset, YOffset, panelTWidth, panelTHeight);

		this.setVisible(true);
		Border b = BorderFactory.createLineBorder(Color.BLACK, 3);
		b= BorderFactory.createTitledBorder(b, "Gates");
		this.setBorder(b);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Gate i : this.gates) {
			i.draw(g);

		}

	}
}