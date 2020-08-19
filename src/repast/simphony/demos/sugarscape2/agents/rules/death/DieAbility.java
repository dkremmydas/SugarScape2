package repast.simphony.demos.sugarscape2.agents.rules.death;

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
	
	
	/**
	 * In this method, the Repast Simphony environmental variables should be used
	 * in order to give values to the object 
	 */
	public void configureFromEnvironment();

}
