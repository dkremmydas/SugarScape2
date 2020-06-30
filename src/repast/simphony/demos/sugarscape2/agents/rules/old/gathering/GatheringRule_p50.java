package repast.simphony.demos.sugarscape2.agents.rules.old.gathering;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.old.SugarAgent_ch2p50;

public class GatheringRule_p50 extends GatheringRule_p30 {

	public GatheringRule_p50() {
		super();
	}

	@Override
	public Integer gather(SugarAgent owner) {
		SugarAgent_ch2p50 a = (SugarAgent_ch2p50) owner;
		int r = super.gather(owner);
		a.setAmountGathered(r);
		
		return r;
	}
	
	

}
