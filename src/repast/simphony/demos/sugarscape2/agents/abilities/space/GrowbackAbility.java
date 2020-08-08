package repast.simphony.demos.sugarscape2.agents.abilities.space;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.valueLayer.GridValueLayer;

public interface GrowbackAbility {

	/**
	 * Returns the new amount of Sugar (e.g. old amount + growback rate) 
	 * 
	 * @param s the SugarSpace agent
	 * @param rate the rate of growback. If ==-1, then growback to capacity
	 * @return
	 */
	public GridValueLayer growback(SugarSpace_ch2 s);
	
}
