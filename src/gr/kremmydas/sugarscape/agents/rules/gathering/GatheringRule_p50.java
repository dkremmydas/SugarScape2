package gr.kremmydas.sugarscape.agents.rules.gathering;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p50;

public class GatheringRule_p50 extends GatheringRule_p30 {

	public GatheringRule_p50() {
		super();
	}

	@Override
	public Integer gather(Agent owner) {
		AgentChapter2_p50 a = (AgentChapter2_p50) owner;
		int r = super.gather(owner);
		a.setAmountGathered(r);
		
		return r;
	}
	
	

}
