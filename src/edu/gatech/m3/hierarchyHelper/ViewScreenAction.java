/**
 * 
 */
package edu.gatech.m3.hierarchyHelper;

/**
 * @author machiry
 *
 */
public enum ViewScreenAction {
	SMSReceive,
	InCommingCall;
	
	public static ViewScreenAction formString(String actionString){
		assert(actionString != null);
		if(actionString.equals("SMS_RECEIVE")){
			return SMSReceive;
		}
		if(actionString.equals("INCOMMING_CALL")){
			return InCommingCall;
		}
		return null;
	}
}
