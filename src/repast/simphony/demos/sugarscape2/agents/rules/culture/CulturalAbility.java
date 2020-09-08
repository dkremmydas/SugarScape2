package repast.simphony.demos.sugarscape2.agents.rules.culture;

import java.util.Map;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public interface CulturalAbility {
	
	Map<SugarAgent_ch3, Integer> culturalTransmission(SugarAgent_ch3 a);
	
	int cultureGroup(SugarAgent_ch3 a);

}
