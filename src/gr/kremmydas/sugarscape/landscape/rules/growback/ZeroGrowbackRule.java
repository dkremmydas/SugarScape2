package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.landscape.Landscape;
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
	public GridValueLayer growback(Landscape landscape) {
		return landscape.getSugarGridProperties().getCurrentQuantity();
	}
	

}
