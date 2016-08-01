package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.agents.AgentChapter2;
import repast.simphony.space.grid.GridPoint;

public interface MoveAbility {

	/**
	 * Decision of the Gridpoint to move
	 * @return the {@link GridPoint}  to move to
	 */
	public GridPoint move(AgentChapter2 owner);

}
