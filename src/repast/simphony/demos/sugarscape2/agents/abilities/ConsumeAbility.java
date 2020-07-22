package repast.simphony.demos.sugarscape2.agents.abilities;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;

public interface ConsumeAbility {
	
	/**
	 * 
	 * @param a {@link SugarAgent_ch2}
	 * @return the number of sugar the agent metabolizes
	 */
	public int metabolize(SugarAgent_ch2 a);

}
