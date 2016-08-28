package repast.simphony.demos.sugarscape2.agents.rules.death;

import repast.simphony.demos.sugarscape2.agents.Agent;

/**
 * A rule deciding on the death of agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface DeathAbility {

	/**
	 * Should the agent die ?
	 * @param {@link Agent} owner
	 * @return true if agent should die, false if not
	 */
	public boolean die(Agent owner);
	
}
