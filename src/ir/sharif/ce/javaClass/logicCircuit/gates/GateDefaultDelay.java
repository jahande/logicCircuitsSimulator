package ir.sharif.ce.javaClass.logicCircuit.gates;

import ir.sharif.ce.javaClass.logicCircuit.Constants.SleepTime;

public class GateDefaultDelay {

	private GateDefaultDelay() {
	}
	
	private static int delayValue;

	public static long getDelayValue() {
		return delayValue;
	}

	public static void setDelayValue(int delayValue) {
		GateDefaultDelay.delayValue = delayValue;
	}
	
	static
	{
		delayValue = 1+(int)(SleepTime.getSleepTime()/100.0);
	}

	
}
