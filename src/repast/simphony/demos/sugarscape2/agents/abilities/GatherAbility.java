package repast.simphony.demos.sugarscape2.agents.abilities;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;

public interface GatherAbility {
	
	/**
	 * 
	 * @param a {@link SugarAgent_ch2}
	 * @return the number of sugar it will gather from the point he is located
	 */
	public int gather(SugarAgent_ch2 a);

}
