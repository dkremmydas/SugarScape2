package repast.simphony.demos.sugarscape2.agents.rules.inheritance;

import java.util.HashMap;
import java.util.Map;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public class NoInheritance implements InheritanceAbility {

	@Override
	public Map<SugarAgent_ch3, Integer> will(SugarAgent_ch3 a) {
		return new HashMap<SugarAgent_ch3, Integer>();
	}

}
