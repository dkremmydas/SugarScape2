package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2;
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
	public GridValueLayer growback(LandscapeChapter2 landscape) {
		return landscape.getSugarGridProperties().getCurrentQuantity();
	}
	

}
