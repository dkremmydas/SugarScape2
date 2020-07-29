package repast.simphony.demos.sugarscape2.agents.abilities.space;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.valueLayer.GridValueLayer;

public interface GrowbackAbility {

	/**
	 * Returns the new amount of Sugar (e.g. old amount + growback)
	 * @return
	 */
	public GridValueLayer growback(SugarSpace_ch2 s);
	
}
