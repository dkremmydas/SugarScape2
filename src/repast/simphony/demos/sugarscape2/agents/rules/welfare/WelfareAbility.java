package repast.simphony.demos.sugarscape2.agents.rules.welfare;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public interface WelfareAbility {
	
	double estimateWelfare(SugarAgent_ch4 a, int sugarWealth, int spiceWealth);

}
