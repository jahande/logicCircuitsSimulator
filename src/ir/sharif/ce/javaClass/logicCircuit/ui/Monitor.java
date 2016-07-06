package ir.sharif.ce.javaClass.logicCircuit.ui;

import ir.sharif.ce.javaClass.logicCircuit.Wire;
import ir.sharif.ce.javaClass.logicCircuit.Constants.SleepTime;
import ir.sharif.ce.javaClass.logicCircuit.utils.GraphicalCordination;
import ir.sharif.ce.javaClass.logicCircuit.utils.Utils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Monitor extends JPanel {
	/**
	 * inner class!!
	 * 
	 * @author Ruholla
	 * 
	 */
	class MonitorPointsRunner implements Runnable {
		Color drawingColor = Color.GREEN;
		final int duration;// milli second
		int lastStartedIndex = 0;
		Points points;

		private Points getPoints() {
			return points;
		}

		public MonitorPointsRunner(int duringTime) {
			super();
			this.duration = duringTime;
			this.points = new Points();
		}

		private void drawGraph(Graphics g) {

			int justNow = (int) (System.currentTimeMillis() - Window.startTime);

			g.setColor(this.drawingColor);
			int x1, x2, y1, y2;
			for (int i = this.lastStartedIndex + 1; i < getPoints().getXS()
					.size(); i++) {

				if (justNow < this.duration + getPoints().getX(i - 1)) {
					
					x1 = (int) ((getPoints().getX(i - 1) + this.duration - justNow)
							* getWidth() / this.duration);
					y1 = getPoints().getY(i - 1);
					x2 = (int) ((getPoints().getX(i) + this.duration - justNow)
							* getWidth() / this.duration);
					y2 = getPoints().getY(i);

					g.drawLine(x1, y1, x2, y2);
				}else{
					lastStartedIndex = i;
				}

			}

			System.out.println("lastStartedIndex= " + lastStartedIndex);
			System.out.println("size= " + getPoints().getXS().size());

		}

		public void run() {
			int yt;
			while (true) {
				if (getMonitoredWire().getValue()) {
					yt = getHeight() / 4 + getHeight() / 6;
				} else {
					yt = getHeight() * 3 / 4 + getHeight() / 6;
				}

				this
						.getPoints()
						.addPoint(
								(int) ((System.currentTimeMillis() - Window.startTime)),
								yt);
				System.out.println("MCPx= "
						+ ((System.currentTimeMillis() - Window.startTime
								+ "MCP" + yt)));
				try {
					Monitor.this.pointsThread.sleep(SleepTime.getSleepTime());
				} catch (InterruptedException e) {
					System.out.println("CATCHED EXCEPTION!!!");
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * end of inner class
	 */
	MonitorPointsRunner MPR = new MonitorPointsRunner(5000);// 5 second
	Thread pointsThread;
	// = new Points();
	// private Monitor.GraphDrawer graph = new GraphDrawer();
	private Wire monitoredWire;
	private JLabel label = new JLabel();
	private int numberOfPaints = 0;
	private GraphicalCordination placeOfMonotor = new GraphicalCordination(0,
			0, 0, 0);

	public Monitor(Wire monitoredWire) {
		super();
		this.monitoredWire = monitoredWire;
		label.setBackground(Color.BLUE);
		// Font f = this.getFont();
		// javax.swing.text.Style s = f.getStyle();
		this.label.setText(monitoredWire.toString() + "(CS):"
				+ this.monitoredWire.getValue());
		// this.label.setBackground(new Color(25, 100, 100));

		this.add(this.label);
		this.setBackground(Color.BLACK);
		this.pointsThread = new Thread(this.MPR);
		this.pointsThread.start();
		// this.wave = new GraphDrawer(10, 10);
	}

	@Override
	public void paint(Graphics g) {
		// this.setBounds(placeOfMonotor.getX(), placeOfMonotor.getY(),
		// placeOfMonotor.getWidth(), placeOfMonotor.getHeight());
		super.paint(g);
		g.setColor(Color.WHITE);
		this.label.setText(monitoredWire.toString() + "(CS): "
				+ this.monitoredWire.getValue());
		// drawGraph(g);
		getMPR().drawGraph(g);
		this.numberOfPaints++;
	}

	// private Dot computeDot() {
	// Dot res = new Dot();
	// if (this.monitoredWire.getValue()) {
	// res.y = (int) (this.wave.height / 4.0);
	// } else {
	// res.y = (int) (this.wave.height * 3 / 4.0);
	// }
	// res.x = (int) (System.currentTimeMillis() - this.startTime);
	// return res;
	// }

	boolean entranc = true;

	@Override
	public void setBounds(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		if (this.entranc) {
			getPlaceOfMonotor().setCordination(x, y, width, height);
			this.entranc = false;
		}
		super
				.setBounds(getPlaceOfMonotor().getX(), getPlaceOfMonotor()
						.getY(), getPlaceOfMonotor().getWidth(),
						getPlaceOfMonotor().getHeight());

		this.label.setBounds(getPlaceOfMonotor().getX(), getPlaceOfMonotor()
				.getY(), getPlaceOfMonotor().getWidth(), getPlaceOfMonotor()
				.getHeight() / 5);

		this.setFont(Utils.getResizedFont(this.label.getFont(), this.label
				.getText(), getPlaceOfMonotor().getWidth(), getPlaceOfMonotor()
				.getHeight() / 5));
	}

	private GraphicalCordination getPlaceOfMonotor() {
		return this.placeOfMonotor;
	}

	private Wire getMonitoredWire() {
		return monitoredWire;
	}

	private MonitorPointsRunner getMPR() {
		return MPR;
	}

}

class IndexIsOutOfRangeInDraw extends RuntimeException {

}

class Points {
	ArrayList<Integer> xS;
	ArrayList<Integer> yS;

	public Points() {
		xS = new ArrayList<Integer>(5000);
		yS = new ArrayList<Integer>(5000);
	}

	public Points(int capacity) {
		xS = new ArrayList<Integer>(capacity);
		yS = new ArrayList<Integer>(capacity);
	}

	public Points(ArrayList<Integer> xs, ArrayList<Integer> ys) {
		super();
		this.xS = xs;
		this.yS = ys;
	}

	public ArrayList<Integer> getXS() {
		return xS;
	}

	public void setXS(ArrayList<Integer> xs) {
		xS = xs;
	}

	public ArrayList<Integer> getYS() {
		return yS;
	}

	public void setYS(ArrayList<Integer> ys) {
		yS = ys;
	}

	public void addX(int x) {
		getXS().add(x);
	}

	public void addY(int y) {
		getYS().add(y);
	}

	public void addPoint(int x, int y) {
		addX(x);
		addY(y);
	}

	public void addPoint(Point p) {
		addX(p.x);
		addY(p.y);
	}

	public int getX(int ind) {
		return getXS().get(ind);
	}

	public int getY(int index) {
		return getYS().get(index);
	}

}
