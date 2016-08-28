package repast.simphony.demos.sugarscape2.agents.rules.gathering;

import repast.simphony.demos.sugarscape2.agents.Agent;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p50;

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
