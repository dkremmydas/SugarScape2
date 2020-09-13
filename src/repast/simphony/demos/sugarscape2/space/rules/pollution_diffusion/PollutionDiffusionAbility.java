package repast.simphony.demos.sugarscape2.space.rules.pollution_diffusion;

import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.valueLayer.GridValueLayer;

public interface PollutionDiffusionAbility {

	/**
	 * Returns the new pollution {@link GridValueLayer}, after the diffusion has taken place
	 * 
	 * @param s the SugarSpace agent
	 * @return
	 */
	public GridValueLayer diffuse(SugarSpace_ch2 s);
	
	
}
