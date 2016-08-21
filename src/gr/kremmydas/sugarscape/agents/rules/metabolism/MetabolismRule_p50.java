package gr.kremmydas.sugarscape.agents.rules.metabolism;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p50;

public class MetabolismRule_p50 extends MetabolismRule_p30 {

	public MetabolismRule_p50() {
		super();
	}

	@Override
	public Integer metabolize(Agent owner) {
		AgentChapter2_p50 a = (AgentChapter2_p50) owner;
		int r = super.metabolize(owner);
		
		a.setAmountMetbolized(r);
		
		return r;
	}
	
	

}
