package repast.simphony.demos.sugarscape2.agents.rules.metabolism;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;

public class MetabolismRule_p30 implements MetabolismAbility {

	public MetabolismRule_p30() {
		super();
	}

	/**
	 * Metabolize sugar according to agent's metabolism needs
	 */
	@Override
	public Integer metabolize(SugarAgent owner) {
		SugarAgent_ch2p30 o = (SugarAgent_ch2p30) owner;
		
		return (int) o.getMetabolism();
	}



}
