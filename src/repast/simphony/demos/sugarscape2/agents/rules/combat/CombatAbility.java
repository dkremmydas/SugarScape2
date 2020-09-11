package repast.simphony.demos.sugarscape2.agents.rules.combat;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public interface CombatAbility {
	
	/**
	 * Get a Map os the {@link SugarAgent_ch3} defeated opponents and the welath it will be transferred to the winning agent 
	 * 
	 * @param a
	 * @return
	 */
	public Pair<SugarAgent_ch3,Integer> getVictim(SugarAgent_ch3 a);

}
