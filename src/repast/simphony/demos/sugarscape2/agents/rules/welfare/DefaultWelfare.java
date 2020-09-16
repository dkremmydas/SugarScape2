package repast.simphony.demos.sugarscape2.agents.rules.welfare;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public class DefaultWelfare implements WelfareAbility {

	@Override
	public double estimateWelfare(SugarAgent_ch4 a,int sugarWealth, int spiceWealth) {
		
		int metabolism_total = a.resourceGetMetabolism("sugar") + a.resourceGetMetabolism("spice");
		
		
		
		return (
				(sugarWealth^(a.resourceGetMetabolism("sugar")/metabolism_total))
				*
				(spiceWealth^(a.resourceGetMetabolism("spice")/metabolism_total))
				);
				
	}

}
