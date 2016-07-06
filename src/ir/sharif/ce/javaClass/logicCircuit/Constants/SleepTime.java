package ir.sharif.ce.javaClass.logicCircuit.Constants;

public class SleepTime {

	private SleepTime() {
	}
	private static long sleepTime = 20;
	public static long getSleepTime() {
		return sleepTime;
	}
	public static void setSleepTime(long sleepTime) {
		SleepTime.sleepTime = sleepTime;
	} 
}
