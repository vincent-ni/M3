package edu.gatech.m3.clients;

import edu.gatech.m3.devHandler.ADevice;
import edu.gatech.m3.utilities.Logger;

public class LoadLibraryMonitoring extends MonitoringClient {

	public LoadLibraryMonitoring(ADevice targetDevice) throws Exception {
		if (targetDevice != null) {
			this.finalTargetDevice = targetDevice;
			this.filterString = "M3_SYS:LoadLibrary";
			Logger.logInfo("LoadLibraryMonitoring Initialized for:"+targetDevice.toString());
		} else{
			throw new Exception("Unable to initialize LoadLibraryMonitoring");
		}
	}

}
