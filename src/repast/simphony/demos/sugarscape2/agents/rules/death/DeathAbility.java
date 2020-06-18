package repast.simphony.demos.sugarscape2.agents.rules.death;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;

/**
 * A rule deciding on the death of agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface DeathAbility {

	/**
	 * Should the agent die ?
	 * @param {@link SugarAgent} owner
	 * @return true if agent should die, false if not
	 */
	public boolean die(SugarAgent owner);
	
}
