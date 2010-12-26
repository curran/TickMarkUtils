import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
	private static double log10(double x){
		return Math.log(x)/Math.log(10);
	}
	public static void main(String[] args) {
//		
//		System.out.println("log10(100) = "+log10(100));
//		System.out.println("log10(1000) = "+log10(1000));
//		System.out.println("Math.log10(100) = "+Math.log10(100));
//		System.out.println("Math.log10(1000) = "+Math.log10(1000));
//		System.exit(0);
//		
		
		
		
		
		
		
		
		final ProperAxis axis = new ProperAxis();

		final JSlider minSlider = new JSlider(-100, 99, (int) axis.getMin());
		minSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				axis.setMin(minSlider.getValue());
				axis.repaint();
			}
		});

		final JSlider maxSlider = new JSlider(100, 10000, (int) axis.getMax());
		maxSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				axis.setMax(maxSlider.getValue());
				axis.repaint();
			}
		});

		final JSlider nSlider = new JSlider(1, 50, (int) axis.getN());
		nSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				axis.setN(nSlider.getValue());
				axis.repaint();
			}
		});

		JFrame frame = new JFrame();
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.add(new JLabel("Min:"));
		frame.add(minSlider);
		frame.add(new JLabel("Max:"));
		frame.add(maxSlider);
		frame.add(new JLabel("Number of Tick Marks:"));
		frame.add(nSlider);

		frame.add(axis);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 600);
		frame.setVisible(true);
	}
}
