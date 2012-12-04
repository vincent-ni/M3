package edu.gatech.m3.hierarchyHelper;

import edu.gatech.m3.devHandler.ADevice;

/***
 * 
 * @author machiry
 *
 */
public abstract class ViewScreenExeciser {
	
	/***
	 * 
	 * @param targetTestDevice
	 * @param targetScreen
	 * @param targetAction
	 * @return
	 */
	public abstract boolean execiseScreen(ADevice targetTestDevice,ViewScreen targetScreen,ViewScreenAction targetAction);
	
	public abstract boolean endTracing();
}
