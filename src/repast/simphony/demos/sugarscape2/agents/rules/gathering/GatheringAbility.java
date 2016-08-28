package repast.simphony.demos.sugarscape2.agents.rules.gathering;

import repast.simphony.demos.sugarscape2.agents.Agent;

public interface GatheringAbility {

	/**
	 * Decision of how many units of sugar to gather in an existing site
	 * @return Integer units of sugar
	 */
	public Integer gather(Agent owner);
	
}
