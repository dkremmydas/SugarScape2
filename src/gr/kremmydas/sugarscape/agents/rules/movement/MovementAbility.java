package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.agents.Agent;
import repast.simphony.space.grid.GridPoint;

/**
 * The M (movement) rule ability
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface MovementAbility {

	/**
	 * <p>The {@link GridPoint} that the agent decides to move to.</p>
	 * <p>The method implementation should cater for returning a valid GridPoint 
	 * since no further checks are made.<br />
	 * For example, the Sugarscape model denotes that only one {@link Agent} can
	 * be on a {@link GridPoint}. A correct implementation should cater for this.<br />
	 * See for example {@link MovementRule_p30} and {@link RandomMovementRule}.</p>
	 * @return the {@link GridPoint}  to move to
	 */
	public GridPoint move(Agent owner);

}
