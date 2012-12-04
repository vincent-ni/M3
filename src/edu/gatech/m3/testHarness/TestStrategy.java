/**
 * 
 */
package edu.gatech.m3.testHarness;

import edu.gatech.m3.appHandler.AndroidManifestParser;
import edu.gatech.m3.appHandler.BroadCastReceiver;
import edu.gatech.m3.appHandler.IntentFilter;
import edu.gatech.m3.deviceEvent.BroadCastAction;
import edu.gatech.m3.hierarchyHelper.IDeviceAction;
import edu.gatech.m3.hierarchyHelper.ViewElement;
import edu.gatech.m3.utilities.Logger;
import edu.gatech.m3.utilities.Pair;

/**
 * @author machiry
 * 
 */
public abstract class TestStrategy {

	protected Logger textLogger;

	/***
	 * This is the preparation step of test strategy. Here all the setup
	 * required by the test strategy needs to be performed if this method
	 * returns false then the test strategy will not be executed further
	 * 
	 * @return true/false indicating success or failure of this step.
	 */
	public abstract boolean prepare();

	/***
	 * This is the method that runs the underlying test strategy Calling of this
	 * method by framework depends on the success of the prepare call.
	 * 
	 * @return true/false indicating success of failure of this step
	 */
	public abstract boolean perform();

	/***
	 * This is the method that does the clean up required for the test strategy
	 * this method will be called only if prepare returns true, irrespective of
	 * whether perform returned false or not
	 * 
	 * @return true/false indicating success of failure of this step
	 */
	public abstract boolean cleanup();

	// Property Names for different properties that will be used by the test
	// strategy
	public static final String workDirPropertyName = "workDir";
	public static final String appSrcPropertyName = "appSrc";
	public static final String appStartUpTimeProperty = "app_start_wait";
	public static final String appCoverageDumpTimeProperty = "app_cov_dump_wait";

	protected void addReceiversToSelectionStrategy(
			AndroidManifestParser manifest,
			WidgetSelectionStrategy targetStretegy) {
		if (manifest != null && targetStretegy != null) {
			for (BroadCastReceiver r : manifest.getAllBroadcastReceivers()) {
				String targetComponentName = manifest.getAppPackage() + "/"
						+ r.receiverComponentName;
				
				//This Receiver is what we added to get the coverage statistics, no need to fire this
				if (!r.receiverComponentName
						.contains(".EmmaInstrument.SMSInstrumentedReceiver")) {
					for (IntentFilter f : r.intentFilters) {
						for (String action : f.intentActions) {
							BroadCastAction targetAction = BroadCastAction
									.getBroadCastEvent(targetComponentName,
											action, f.intentCategory);
							if (targetAction == null) {
								this.textLogger.logError(
										"RECEIVER_ADD_PROBLEM",
										"Unable to get broadcast event for:"
												+ action);
							} else {
								this.textLogger.logInfo("RECEIVER_ADD_SUCESS",
										"Sucessfully added receiver for:"
												+ action);
								targetStretegy
										.addNonUiDeviceAction(new Pair<ViewElement, IDeviceAction>(
												null,
												(IDeviceAction) targetAction));
							}

						}
					}
				}
			}
		}
	}

}
