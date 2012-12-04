package edu.gatech.m3.clients;

import edu.gatech.m3.devHandler.ADevice;
import edu.gatech.m3.deviceEvent.BroadCastAction;
import edu.gatech.m3.hierarchyHelper.IDeviceAction;
import edu.gatech.m3.hierarchyHelper.ViewElement;
import edu.gatech.m3.utilities.Logger;
import edu.gatech.m3.utilities.Pair;

public class BroadCastRegistrationMonitor extends MonitoringClient {
	
	public BroadCastRegistrationMonitor(ADevice targetDevice) throws Exception {
		if (targetDevice != null) {
			this.finalTargetDevice = targetDevice;
			this.filterString = "M3RegReceivers";
			Logger.logInfo("BroadCastRegistrationMonitor Initialized for:"
					+ targetDevice.toString());
		} else {
			throw new Exception(
					"Unable to initialize BroadCastRegistrationMonitor");
		}
	}
	
	@Override
	public boolean consume(String entry) {
		if (this.toMonitor && entry != null) {
			if (entry.contains(filterString) && entry.contains(Integer.toString(targetAppUid))) {
				synchronized (logEntries) {
					logEntries.add(entry);
				}
				//its ok for the feedback to be null
				if(this.feedBack != null){
					BroadCastReceiverInfo receiveInfo = BroadCastReceiverInfo.getReceiverInfo(entry);
					if(receiveInfo != null){
						for(String actionName:receiveInfo.bcActions){
							BroadCastAction targetAction = BroadCastAction.getBroadCastEvent(receiveInfo.receiver, actionName, receiveInfo.bcCategories);
							if(targetAction != null && (targetAction instanceof IDeviceAction)){
								this.feedBack.addNonUiDeviceAction(new Pair<ViewElement, IDeviceAction>(null, (IDeviceAction)targetAction));
							} else{
								Logger.logError("Unable to parse the receiveInfo to listner");
							}
						}
					} else{
						Logger.logError("Problem occured parsing the register receiver entry:"+entry);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	
}
