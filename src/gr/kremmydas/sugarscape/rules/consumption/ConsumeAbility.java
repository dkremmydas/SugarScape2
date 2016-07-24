package gr.kremmydas.sugarscape.rules.consumption;

import repast.simphony.space.grid.GridPoint;

public interface ConsumeAbility {
	
	/**
	 * Decision of many units of each {@link GridPoint} to consume
	 * @return Map<GridPoint,Integer> Map of GridPoint->Consumption(int)
	 */
	abstract Integer consume();

}
