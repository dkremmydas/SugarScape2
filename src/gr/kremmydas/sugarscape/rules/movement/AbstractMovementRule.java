package gr.kremmydas.sugarscape.rules.movement;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.rules.AbstractRule;
import repast.simphony.space.grid.GridPoint;

public abstract class AbstractMovementRule extends AbstractRule {
	

	public AbstractMovementRule(Agent owner) {
		super(owner);
	}

	/**
	 * Decision of the Gridpoint to move
	 * @return the {@link GridPoint}  to move to
	 */
	abstract GridPoint move();
	
}
