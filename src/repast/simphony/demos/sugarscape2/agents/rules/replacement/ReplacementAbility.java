package repast.simphony.demos.sugarscape2.agents.rules.replacement;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;

public interface ReplacementAbility {

	/**
	 * Creates a new agent and puts him on the position of the old one 
	 * //TODO is this the definition of 'replacement' ? 
	 * 
	 * @param s the SugarSpace agent
	 * @param rate the rate of growback. If ==-1, then growback to capacity
	 * @return
	 */
	public void replace(SugarSpace_ch2 space, SugarAgent_ch2 old);
	
	
	/**
	 * In this method, the Repast Simphony environmental variables should be used
	 * in order to give values to the object 
	 */
	public void configureFromEnvironment();
	
}
