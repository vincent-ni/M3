/**
 * 
 */
package edu.gatech.m3.testHarness;

import edu.gatech.m3.testHarness.ScreenBasedGraphAbstraction.GraphBasedSelectionStrategy;
import edu.gatech.m3.testHarness.WidgetFrequencySelection.WidgetFrequencyBasedSelectionStrategy;
import edu.gatech.m3.testHarness.WidgetRandomSelection.WidgetRandomBasedSelectionStrategy;
import edu.gatech.m3.testHarness.WidgetRandomBiasSelection.WidgetRandomBiasBasedSelectionStrategy;
import edu.gatech.m3.utilities.Logger;

/**
 * This is used to get the requested widget selection strategy 
 * @author machiry
 *
 */
public class StrategyFactory {
	
	public static final String widgetGraph = "GraphBased";
	public static final String frequencyBased = "FrequencyBased";
	public static final String randomBased = "RandomBased";
	public static final String randomBiasBased = "RandomBiasBased";
	
	public static WidgetSelectionStrategy getWidgetSelectionStrategy(String strategyName,String workingDir,int covSamplingInterval){
		if(strategyName.equals(widgetGraph)){
			try{
			return new GraphBasedSelectionStrategy(workingDir,covSamplingInterval);
			} catch(Exception e){
				Logger.logException(e);
			}
		}
		if(strategyName.equals(frequencyBased)){
			try{
			return new WidgetFrequencyBasedSelectionStrategy(workingDir,covSamplingInterval);
			} catch(Exception e){
				Logger.logException(e);
			}
		}
		if(strategyName.equals(randomBased)){
			try{
			return new WidgetRandomBasedSelectionStrategy(workingDir,covSamplingInterval);
			} catch(Exception e){
				Logger.logException(e);
			}
		}
                if(strategyName.equals(randomBiasBased)){
			try{
			return new WidgetRandomBiasBasedSelectionStrategy(workingDir,covSamplingInterval);
			} catch(Exception e){
				Logger.logException(e);
			}
		}

		return null;
	}
}
