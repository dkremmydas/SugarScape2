package gr.kremmydas.sugarscape.rules.consumption;

import gr.kremmydas.sugarscape.Agent;
import gr.kremmydas.sugarscape.rules.AbstractRule;
import repast.simphony.space.grid.GridPoint;

public abstract class AbstractConsumptionRule extends AbstractRule {

	public AbstractConsumptionRule(Agent owner) {
		super(owner);
	}

	/**
	 * Decision of many units of each {@link GridPoint} to consume
	 * @return Map<GridPoint,Integer> Map of GridPoint->Consumption(int)
	 */
	abstract Integer consume();
	
}
