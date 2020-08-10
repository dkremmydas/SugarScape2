package repast.simphony.demos.sugarscape2.agents.abilities.agents;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;

public interface DieAbility {
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public boolean shallDie(SugarAgent_ch2 a);
	
	
	/**
	 * 
	 * @param a
	 * @return
	 */
	public int getAge(SugarAgent_ch2 a);
	
	
	/**
	 * 
	 * @param a
	 */
	public void incrementAge(SugarAgent_ch2 a);

}
