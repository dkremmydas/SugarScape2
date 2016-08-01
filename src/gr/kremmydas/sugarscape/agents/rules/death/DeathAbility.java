package gr.kremmydas.sugarscape.agents.rules.death;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2;

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
	public boolean die(AgentChapter2 owner);
	
}
