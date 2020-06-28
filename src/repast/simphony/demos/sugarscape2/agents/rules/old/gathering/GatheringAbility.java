package repast.simphony.demos.sugarscape2.agents.rules.old.gathering;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;

public interface GatheringAbility {

	/**
	 * Decision of how many units of sugar to gather in an existing site
	 * @return Integer units of sugar
	 */
	public Integer gather(SugarAgent owner);
	
}
