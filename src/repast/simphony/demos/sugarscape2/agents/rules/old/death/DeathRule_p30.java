package repast.simphony.demos.sugarscape2.agents.rules.old.death;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;

public class DeathRule_p30 implements DeathAbility {

	@Override
	public boolean die(SugarAgent owner) {
		SugarAgent_ch2p30 o = (SugarAgent_ch2p30) owner;
		if(o.getSugarProperties().getHolding()>0) return false;
		else return true;
	}

}
