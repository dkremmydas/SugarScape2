package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import repast.simphony.valueLayer.GridValueLayer;

public interface GrowbackAbility {

	/**
	 * Returns the new amount of Sugar (e.g. old amount + growback)
	 * @return
	 */
	public GridValueLayer growback(LandscapeChapter2_p30 l);
	
}
