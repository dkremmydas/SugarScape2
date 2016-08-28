package repast.simphony.demos.sugarscape2.agents.rules.metabolism;

import repast.simphony.demos.sugarscape2.agents.Agent;

public interface MetabolismAbility {
	
	/**
	 * Decision of many units of Sugar to consume
	 * @return Integer units of sugar to metabolize
	 */
	public Integer metabolize(Agent owner);

}
