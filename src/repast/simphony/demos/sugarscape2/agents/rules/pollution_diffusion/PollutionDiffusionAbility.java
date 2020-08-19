package repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.valueLayer.GridValueLayer;

public interface PollutionDiffusionAbility {

	/**
	 * Returns the new pollution {@link GridValueLayer}, after the diffusion has taken place
	 * 
	 * @param s the SugarSpace agent
	 * @return
	 */
	public GridValueLayer diffuse(SugarSpace_ch2 s);
	
	
	/**
	 * In this method, the Repast Simphony environmental variables should be used
	 * in order to give values to the object 
	 */
	public void configureFromEnvironment();
	
}