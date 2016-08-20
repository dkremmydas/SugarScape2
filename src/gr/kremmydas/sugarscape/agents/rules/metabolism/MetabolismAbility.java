package gr.kremmydas.sugarscape.agents.rules.metabolism;

import gr.kremmydas.sugarscape.agents.Agent;

public interface MetabolismAbility {
	
	/**
	 * Decision of many units of Sugar to consume
	 * @return Integer units of sugar to metabolize
	 */
	public Integer metabolize(Agent owner);

}
