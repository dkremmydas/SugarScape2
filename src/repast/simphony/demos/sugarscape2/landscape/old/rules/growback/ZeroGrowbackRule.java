package repast.simphony.demos.sugarscape2.landscape.old.rules.growback;

import repast.simphony.demos.sugarscape2.agents.abilities.space.GrowbackAbility;
import repast.simphony.demos.sugarscape2.landscape.old.LandscapeChapter2_p30;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * No growback is taking place
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ZeroGrowbackRule implements GrowbackAbility {

	public ZeroGrowbackRule() {
		super();
	}

	@Override
	public GridValueLayer growback(LandscapeChapter2_p30 landscape) {
		return landscape.getSugarGridProperties().getCurrentQuantity();
	}
	

}
