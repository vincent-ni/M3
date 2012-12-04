package edu.gatech.m3.hierarchyHelper;

import edu.gatech.m3.devHandler.ADevice;
import edu.gatech.m3.utilities.MonkeyTraceLogger;
import edu.gatech.m3.utilities.TraceLogger;

public class MonkeyViewScreenExeciser extends ViewScreenExeciser {

	private TraceLogger monkeyTrace;

	public MonkeyViewScreenExeciser(TraceLogger log) {
		assert (log != null);
		this.monkeyTrace = log;
	}

	@Override
	public boolean execiseScreen(ADevice targetTestDevice,
			ViewScreen targetScreen, ViewScreenAction targetAction) {
		boolean handlingSucessfull = true;
		switch (targetAction) {
		case InCommingCall:
			break;
		case SMSReceive:
			break;
		}
		return handlingSucessfull;
	}

	@Override
	public boolean endTracing() {
		if(this.monkeyTrace != null){
			this.monkeyTrace.addTraceData(MonkeyTraceLogger.commentCategory, "Ending Tracing Data");
			this.monkeyTrace.endTraceLog();
			return true;
		}
		return false;
	}

}
