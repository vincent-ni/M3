package edu.gatech.m3.hierarchyHelper;

import edu.gatech.m3.utilities.Logger;
import edu.gatech.m3.utilities.MonkeyTraceLogger;

public class ViewElementExeciserFactory {

	/***
	 * 
	 * @param type
	 * @param logFile
	 * @return
	 */
	public static ViewElementExeciser getViewElementExeciser(String type,
			String logFile) {
		ViewElementExeciser retVal = null;
		try {
			if (type.equals(MonkeyViewElementExeciser.monkeyElementExeciser)) {
				retVal = new MonkeyViewElementExeciser(new MonkeyTraceLogger(
						logFile));
			}
		} catch (Exception e) {
			Logger.logException(e);
		}
		return retVal;
	}

}
