package ir.sharif.ce.javaClass.logicCircuit.ui.lastMonitor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.Constants.SleepTime;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Monitor extends JPanel {

	public class GraphDrawer {
		
		Dot corner = new Dot();
		Color graphColor = new Color(0,10,250);
		int width, height;
		int durationTime = 5;// the monitor shows states of last 5 seconds of
		// signal
		ArrayList<Dot> dots = new ArrayList<Dot>(1000);
		int lastValidDotIndexInDotsField = 0;

		// {
		// this.addDot(new Dot());
		// this.addDot(new Dot());
		// }

		public GraphDrawer(int heightOfsetrelativeToLabel, int widthOffset) {
			this.fieldsComputer(heightOfsetrelativeToLabel, widthOffset);
		}

		public GraphDrawer(Dot corner, int with, int height) {
			super();
			if (!corner.isPosetive()) {
				this.corner.setTo(0, 0);

			} else {
				this.corner = corner;
			}
			if (with < 0) {
				this.width = 0;
			} else {
				this.width = with;
			}
			if (height < 0) {
				this.height = 0;
			} else {
				this.height = height;
			}
		}

		// public GraphDrawer(int cornerX,int cornerY, int with, int height) {
		// super();
		//			
		// }
		private void fieldsComputer(int heightOfsetrelativeToLabel,
				int widthOffset) {
			this.corner.x = Monitor.this.getX() + widthOffset;
			this.corner.y = Monitor.this.getY() + heightOfsetrelativeToLabel
					+ Monitor.this.label.getHeight();
			this.height = Monitor.this.getHeight()
					- Monitor.this.label.getHeight() - 2
					* heightOfsetrelativeToLabel;
			this.width = Monitor.this.getWidth() - 2 * widthOffset;
		}

		private int getX(int index) {
			return dots.get(index).x;
		}

		private int getY(int index) {
			return dots.get(index).y;
		}

		public void drawGraph(Graphics g) {
			int temp, temp2;
			for (int i = 0; i < this.dots.size() - 1; i++) {
				//for test!!!!!!!
				//g.drawPolyline(xPoints, yPoints, nPoints)
				temp = this.getXForDraw(i) ;
				temp2 = this.getXForDraw(1 + i) ;
				//!!!!!!!!!!!!!!!
				if (temp > 0 && temp2 > 0) {
					g.drawLine(temp + corner.x, this.getY(i) + corner.y, temp2
							+ corner.x, this.getY(i) + corner.y);
				}else{
					this.lastValidDotIndexInDotsField = i;
				}
			}
		}

		public void drawGraph(Graphics g, int startIndex, int endIndex) {
			if (startIndex < 0 || endIndex > dots.size() - 1) {
				throw new IndexIsOutOfRangeInDraw();
			}
			int temp, temp2;
			for (int i = startIndex; i < endIndex - 1; i++) {
				temp = this.getXForDraw(i);
				temp2 = this.getXForDraw(1 + i);
				g.setColor(this.graphColor);
				g.drawLine(temp + this.corner.x, this.getY(i) + corner.y, temp2
						+ this.corner.x, this.getY(i + 1) + corner.y);
				g.setColor(this.graphColor);
			}
		}

		public int getXForDraw(int i) {
			return this.width
					+ (int) ((this.getX(i) + Monitor.this.startTime - System
							.currentTimeMillis())
							* (double) (this.width) / (1000.0001 * this.durationTime));
		}

		public void addDot(Dot d) {
			Dot t = new Dot(d);
			if (d.x > this.width) {
				t.x = this.width;
			} else if (d.x < 0) {
				t.x = 0;
			}
			if (d.y > this.height) {
				t.y = this.height;
			} else if (t.y < 0) {
				t.y = 0;
			}
			dots.add(t);
		}

	}

	// private Monitor.GraphDrawer graph = new GraphDrawer();
	private Wire monitoredWire;
	private JLabel label = new JLabel();
	private int numberOfPaints = 0;
	private GraphDrawer wave;
	private final long startTime = System.currentTimeMillis();

	public Monitor(Wire monitoredWire) {
		super();
		this.monitoredWire = monitoredWire;
		this.label.setText(monitoredWire.toString() + "(CS):"
				+ this.monitoredWire.getValue());
		this.label.setBackground(new Color(200, 100, 100));

		this.add(this.label);
		this.setBackground(new Color(200, 200, 200));
		this.wave = new GraphDrawer(10, 10);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// this.4
		//System.out.println("hehe");
		int temp;
		this.wave.addDot(this.computeDot());
		this.wave.drawGraph(g);
		// if(this.numberOfPaints>)
		// this.wave.drawGraph(g, this.numberOfPaints-, endIndex);
		this.label.setText(monitoredWire.toString() + ':'
				+ this.monitoredWire.getValue());
		this.numberOfPaints++;

	}

	private Dot computeDot() {
		Dot res = new Dot();
		if (this.monitoredWire.getValue()) {
			res.y = (int) (this.wave.height / 4.0);
		} else {
			res.y = (int) (this.wave.height * 3 / 4.0);
		}
		res.x = (int) (System.currentTimeMillis() - this.startTime);
		return res;
	}
	int[] Rec = new int[4];
	boolean entranc = true;
	@Override
	public void setBounds(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		if(this.entranc)
		{
			Rec[0]=x;
			Rec[1]=y;
			Rec[2]=width;
			Rec[3]=height;
			this.entranc= false;
		}
		super.setBounds(Rec[0], Rec[1], Rec[2], Rec[3]);

		this.label.setBounds(Rec[0], Rec[1], Rec[2], (int)((double)Rec[3] / 5.0));
		int size = (int) (Rec[2] * 1.8 / this.label.getText().length());
		Font f = new Font(this.label.getFont().getFontName(), this.label
				.getFont().getStyle(), size);
		this.setFont(f);
	}
}

class Dot {
	public String toString() {
		return "x= " + this.x + "\ty="+this.y;
		
	}
	public Dot() {
		x = y = 0;
	}

	public boolean isPosetive() {
		return !(x < 0 || y < 0);
	}

	public void setTo(int a, int b) {
		x = a;
		y = b;
	}

	public Dot(int a, int b) {
		x = a;
		y = b;
	}

	public Dot(Dot d) {
		this.x = d.x;
		this.y = d.y;
	}

	public int x, y;
}

class IndexIsOutOfRangeInDraw extends RuntimeException {

}
