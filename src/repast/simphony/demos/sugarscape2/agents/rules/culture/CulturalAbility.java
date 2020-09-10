package repast.simphony.demos.sugarscape2.agents.rules.culture;

import java.util.Map;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public interface CulturalAbility {
	
	/**
	 * Select a tag of other agents to change
	 * 
	 * @param a
	 * @return
	 */
	Map<SugarAgent_ch3, Integer> culturalTransmission(SugarAgent_ch3 a);
	
	/**
	 * Return the cultural group of the Agent
	 * 
	 * @param a
	 * @return
	 */
	String cultureGroup(SugarAgent_ch3 a);

}
