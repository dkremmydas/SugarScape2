package gr.kremmydas.sugarscape.landscape.rules.growback;

import repast.simphony.valueLayer.GridValueLayer;

public interface GrowbackAbility {

	/**
	 * Returns the new amount of Sugar
	 * @return
	 */
	public GridValueLayer growback();
	
}
