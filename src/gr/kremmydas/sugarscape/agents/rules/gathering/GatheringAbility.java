package gr.kremmydas.sugarscape.agents.rules.gathering;

import gr.kremmydas.sugarscape.agents.Agent;

public interface GatheringAbility {

	/**
	 * Decision of how many units of sugar to gather in an existing site
	 * @return Integer units of sugar
	 */
	public Integer gather(Agent owner);
	
}
