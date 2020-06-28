package repast.simphony.demos.sugarscape2.agents.rules.old.metabolism;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;

public interface MetabolismAbility {
	
	/**
	 * Decision of many units of Sugar to consume
	 * @return Integer units of sugar to metabolize
	 */
	public Integer metabolize(SugarAgent owner);

}
