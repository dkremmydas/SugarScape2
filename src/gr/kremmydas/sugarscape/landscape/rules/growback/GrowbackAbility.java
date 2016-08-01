package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2;
import repast.simphony.valueLayer.GridValueLayer;

public interface GrowbackAbility {

	/**
	 * Returns the new amount of Sugar
	 * @return
	 */
	public GridValueLayer growback(LandscapeChapter2 l);
	
}
