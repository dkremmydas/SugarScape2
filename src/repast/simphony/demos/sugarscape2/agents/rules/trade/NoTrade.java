package repast.simphony.demos.sugarscape2.agents.rules.trade;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public class NoTrade implements TradeAbility {

	@Override
	public SugarAgent_ch4 selectPartner(SugarAgent_ch4 a) {
		return null;
	}

	@Override
	public TradeTransaction doTrade(SugarAgent_ch4 a1, SugarAgent_ch4 a2) {
		return null;
	}

}
