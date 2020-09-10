package repast.simphony.demos.sugarscape2.agents.rules.culture;

import java.util.HashMap;
import java.util.Map;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public class NoCulture implements CulturalAbility {

	@Override
	public Map<SugarAgent_ch3, Integer> culturalTransmission(SugarAgent_ch3 a) {
		return new HashMap<SugarAgent_ch3, Integer>();
	}

	@Override
	public String cultureGroup(SugarAgent_ch3 a) {
		return "<NA>";
	}

}
