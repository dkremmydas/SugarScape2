package repast.simphony.demos.sugarscape2.agents.rules.trade;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public interface TradeAbility {
	
	Set<TradeTransaction> doTrade(SugarAgent_ch4 a1, SugarAgent_ch4 a2);

}
