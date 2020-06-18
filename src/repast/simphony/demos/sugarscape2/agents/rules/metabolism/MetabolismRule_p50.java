package repast.simphony.demos.sugarscape2.agents.rules.metabolism;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p50;

public class MetabolismRule_p50 extends MetabolismRule_p30 {

	public MetabolismRule_p50() {
		super();
	}

	@Override
	public Integer metabolize(SugarAgent owner) {
		SugarAgent_ch2p50 a = (SugarAgent_ch2p50) owner;
		int r = super.metabolize(owner);
		
		a.setAmountMetbolized(r);
		
		return r;
	}
	
	

}
