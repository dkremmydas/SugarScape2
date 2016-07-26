package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.agents.Agent;
import repast.simphony.space.grid.GridPoint;

public interface MoveAbility {

	/**
	 * Decision of the Gridpoint to move
	 * @return the {@link GridPoint}  to move to
	 */
	abstract GridPoint move(Agent owner);

}
