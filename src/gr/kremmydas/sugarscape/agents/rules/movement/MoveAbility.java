package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import repast.simphony.space.grid.GridPoint;

/**
 * The M (movement) rule ability
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface MoveAbility {

	/**
	 * <p>The {@link GridPoint} that the agent decides to move to.</p>
	 * <p>The method implementation should cater for returning a valid GridPoint 
	 * since no further checks are made.<br />
	 * For example, the Sugarscape model denotes that only one {@link Agent} can
	 * be on a {@link GridPoint}. A correct implementation should cater for this.<br />
	 * See for example {@link DefaultMovementRule} and {@link RandomMovementRule}.</p>
	 * @return the {@link GridPoint}  to move to
	 */
	public GridPoint move(AgentChapter2_p30 owner);

}
