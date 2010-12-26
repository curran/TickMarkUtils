import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProperAxis extends JPanel {
	private double min = 50, max = 300, n = 10;

	/**
	 * Axis is drawn from (x1,y1) to (x2,y2);
	 */
	private int x1 = 50, y1 = 400, x2 = x1, y2 = 50;

	private int majorTickSize = 12, minorTickSize = 5;

	private double majorInterval, firstMajorTickMarkValue;

	private double minorInterval, firstMinorTickMarkValue;;

	DecimalFormat formatter = new DecimalFormat("#,###,###,###.######");

	public ProperAxis() {
		updateInterval();
	}

	private void updateInterval() {
		majorInterval = TickMarkUtils.getNiceInterval(min, max, n);
		firstMajorTickMarkValue = TickMarkUtils.getFirstTickValue(min,
				majorInterval);
		minorInterval = TickMarkUtils.getNextSmallerInterval(majorInterval);
		minorInterval = TickMarkUtils.getNextSmallerInterval(minorInterval);
		firstMinorTickMarkValue = TickMarkUtils.getFirstTickValue(min,
				minorInterval);
	}

	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.black);
		// g.drawLine(10, 10, 50, 50);
		int x = x1;// hard coded to be vertical
		for (double value = firstMinorTickMarkValue; value < max; value += minorInterval) {
			double norm = (value - min) / (max - min);
			int y = (int) (norm * (y2 - y1) + y1);
			g.drawLine(x - minorTickSize / 2, y, x + minorTickSize / 2, y);
		}
		for (double value = firstMajorTickMarkValue; value < max; value += majorInterval) {
			double norm = (value - min) / (max - min);
			int y = (int) (norm * (y2 - y1) + y1);
			g.drawLine(x - majorTickSize / 2, y, x + majorTickSize / 2, y);
			g.drawString(formatter.format(value), x + 10, y + 5);
		}

		g.drawLine(x1, y1, x2, y2);
		// g.drawString("" + min, x1, y1);
		// g.drawString("" + max, x2, y2);
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
		updateInterval();
		repaint();
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
		updateInterval();
		repaint();
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
		updateInterval();
		repaint();
	}

}
