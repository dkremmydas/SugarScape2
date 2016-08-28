package repast.simphony.demos.sugarscape2.landscape.rules.growback;

import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.valueLayer.GridValueLayer;

public interface GrowbackAbility {

	/**
	 * Returns the new amount of Sugar (e.g. old amount + growback)
	 * @return
	 */
	public GridValueLayer growback(LandscapeChapter2_p30 l);
	
}
