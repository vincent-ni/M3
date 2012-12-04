package edu.gatech.m3.deviceEvent;

import java.util.ArrayList;

import edu.gatech.m3.hierarchyHelper.IDeviceAction;

public class MediaUnmounted extends BroadCastAction implements IDeviceAction {
	
	public MediaUnmounted(String completeComponentName,ArrayList<String> intFilter) throws Exception{
		if(completeComponentName != null){
			this.targetComponentName = completeComponentName;
			if(intFilter != null && intFilter.size() > 0){
				this.intentCategories.addAll(intFilter);
			}
		} else{
			throw new Exception("Problem occured while creating MediaUnmounted receiver");
		}
	}
	
	@Override
	public BroadCastAction getInstance(String completeComponentName,ArrayList<String> intFilter) throws Exception {
		return new MediaUnmounted(completeComponentName, intFilter);
	}

	@Override
	public String getBroadCastAction(){
		return "android.intent.action.MEDIA_UNMOUNTED";
	}

}