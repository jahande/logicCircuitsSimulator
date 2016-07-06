package ir.sharif.ce.javaClass.logicCircuit.ui;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ir.sharif.ce.javaClass.logicCircuit.Wire;

import javax.swing.JButton;

public class ToggleSwitcher extends JButton {

	private final Wire wire;

	/*
	 * private ToggleSwitcher(){ wire = null; }
	 */
	private Wire getWire() {
		return wire;
	}

	public ToggleSwitcher(Wire wire) {
		this.wire = wire;
		this.addActionListener(new ToggleSwitcherActionListener());
		this.setText("Toggle " + wire.toString());
		this.setVisible(true);
		// setBounds(10, 10, 106, 26);
	}

	private class ToggleSwitcherActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ToggleSwitcher.this.getWire().switchValue();
			System.out.println("value of "
					+ ToggleSwitcher.this.getWire().toString() + " ->"
					+ ToggleSwitcher.this.getWire().getValue());
			// button_1_actionPerformed(arg0);
		}
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		int size = (int)(width * 1.8 / this.getText().length());
		Font f = new Font(this.getFont().getFontName(), this.getFont()
				.getStyle(), size);
		this.setFont(f);
		Insets m = this.getMargin();
		m.set(0, 0, 0, 0);
		
		this.setMargin(m);
	

	}

}
