package edu.gatech.m3.clients;

import edu.gatech.m3.devHandler.ADevice;
import edu.gatech.m3.utilities.Logger;

public class SMSMonitoring extends MonitoringClient {
	
	public SMSMonitoring(ADevice targetDevice) throws Exception {
		if (targetDevice != null) {
			this.finalTargetDevice = targetDevice;
			this.filterString = "M3_SMS";
			Logger.logInfo("SMSMonitoring Initialized for:"+targetDevice.toString());
		} else{
			throw new Exception("Unable to initialize SMSMonitoring");
		}
	}
	
}
