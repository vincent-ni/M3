package edu.gatech.m3.clients;

import edu.gatech.m3.devHandler.ADevice;
import edu.gatech.m3.utilities.Logger;

public class BroadCastStartMonitoring extends MonitoringClient{
	
	public BroadCastStartMonitoring(ADevice targetDevice) throws Exception {
		if (targetDevice != null) {
			this.finalTargetDevice = targetDevice;
			this.filterString = "M3BroadcastStart";
			Logger.logInfo("BroadCastStartMonitoring Initialized for:"+targetDevice.toString());
		}
		else{
			throw new Exception("Unable to initialize BroadCastStartMonitoring");
		}
	}
	
}
