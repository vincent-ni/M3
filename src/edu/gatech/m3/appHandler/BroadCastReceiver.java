package edu.gatech.m3.appHandler;

import java.util.ArrayList;

public class BroadCastReceiver {
	public String receiverComponentName = null;
	public ArrayList<IntentFilter> intentFilters = new ArrayList<IntentFilter>();

	@Override
	public String toString() {
		String retVal = null;
		if (receiverComponentName != null) {
			retVal = "Receiver:" + receiverComponentName;
		}
		if (retVal == null) {
			retVal = "No Receiver";
		}
		retVal +="\n";
		for (IntentFilter f : intentFilters) {
			retVal += "\t\tIntentFilter:" + f.toString()+"\n";
		}
		return retVal;
	}
}
