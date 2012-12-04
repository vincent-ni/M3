package edu.gatech.m3.deviceEvent;

import java.util.ArrayList;

import edu.gatech.m3.hierarchyHelper.IDeviceAction;

public class PackageAdded extends BroadCastAction implements IDeviceAction {
	
	public PackageAdded(String completeComponentName,ArrayList<String> intFilter) throws Exception{
		if(completeComponentName != null){
			this.targetComponentName = completeComponentName;
			if(intFilter != null && intFilter.size() > 0){
				this.intentCategories.addAll(intFilter);
			}
		} else{
			throw new Exception("Problem occured while creating BaterryLow Action receiver");
		}
	}
	
	@Override
	public BroadCastAction getInstance(String completeComponentName,ArrayList<String> intFilter) throws Exception {
		return new PackageAdded(completeComponentName, intFilter);
	}

	@Override
	public String getBroadCastAction(){
		return "android.intent.action.PACKAGE_ADDED";
	}

}