package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2;
import repast.simphony.space.grid.GridPoint;

public interface MoveAbility {

	/**
	 * <p>Decision of the agent for the {@link GridPoint} to move.</p>
	 * <p>The method implementation should cater for returning a valid GridPoint 
	 * since no further checks are made.
	 * For example, the Sugarscape model denotes that only one {@link Agent} can
	 * be on a {@link GridPoint}. A correct implementation should cater for this.
	 * See for example {@link DefaultMovementRule} and {@link RandomMovementRule}.</p>
	 * @return the {@link GridPoint}  to move to
	 */
	public GridPoint move(AgentChapter2 owner);

}
