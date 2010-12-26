/**
 * A utility class for computing tick mark intervals to optimize number display.
 * Resulting tick mark intervals will be in the set {1 or 2 or 5}X10^n where n
 * is an integer.
 * 
 * @author curran
 * 
 */
public class TickMarkUtils {
	/**
	 * These are the basis values, multiplied with 10^n, of nice tick marks.
	 */
	private static final int[] niceIntervalBases = { 1, 2, 5 };

	/**
	 * Gets a nice tick mark interval for the given axis specification.
	 * 
	 * @param min
	 *            the minimum value of the axis
	 * @param max
	 *            the maximum value of the axis
	 * @param n
	 *            the approximate number of tick marks desired
	 * @return a nice interval between tick marks.
	 */
	public static double getNiceInterval(double min, double max, double n) {
		double span = max - min;
		double interval = span / n;
		double intervalExponent = Math.floor(Math.log10(interval));
		double intervalBase = interval / Math.pow(10, intervalExponent);

		double bestIntervalBase = niceIntervalBases[0];
		for (int i = 1; i < niceIntervalBases.length; i++)
			if (Math.abs(intervalBase - niceIntervalBases[i]) < Math
					.abs(intervalBase - bestIntervalBase))
				bestIntervalBase = niceIntervalBases[i];

		// System.out.println();
		// System.out.println("interval = "+interval+" = "+intervalBase+" * 10^"+intervalExponent);
		// System.out.println("bestIntervalBase = "+bestIntervalBase);
		double bestInterval = bestIntervalBase * Math.pow(10, intervalExponent);
		// System.out.println("bestInterval = "+bestInterval);
		return bestInterval;
	}

	/**
	 * Gets the value which should be used for the first tick mark for the given
	 * minimum value and interval.
	 * 
	 * @param min
	 * @param interval
	 */
	public static double getFirstTickValue(double min, double interval) {
		double v = Math.ceil(min / interval) * interval;
		if (v == -0)
			v = 0;
		return v;
	}

	/**
	 * Gets the next smallest nice interval. For example, an input of 50 will
	 * result in 10, and an input of 1 will result in .5.
	 * 
	 * @param interval
	 *            a nice interval (returned by getNiceInterval())
	 */
	public static double getNextSmallerInterval(double interval) {
		double intervalExponent = Math.floor(Math.log10(interval));
		double intervalBase = interval / Math.pow(10, intervalExponent);

		double closestNiceBase = niceIntervalBases[0];
		int closestNiceBaseIndex = 0;
		for (int i = 1; i < niceIntervalBases.length; i++)
			if (Math.abs(intervalBase - niceIntervalBases[i]) < Math
					.abs(intervalBase - closestNiceBase))
				closestNiceBase = niceIntervalBases[closestNiceBaseIndex = i];

		if (closestNiceBase == niceIntervalBases[0])
			return niceIntervalBases[niceIntervalBases.length - 1]
					* Math.pow(10, intervalExponent - 1);
		else
			return niceIntervalBases[closestNiceBaseIndex - 1]
					* Math.pow(10, intervalExponent);
	}
}
