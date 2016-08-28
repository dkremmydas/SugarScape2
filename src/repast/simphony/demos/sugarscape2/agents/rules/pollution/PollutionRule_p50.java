package repast.simphony.demos.sugarscape2.agents.rules.pollution;

import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p50;
import repast.simphony.engine.environment.RunEnvironment;

public class PollutionRule_p50 implements PollutionAbility {

	public PollutionRule_p50() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int pollute(AgentChapter2_p50 owner) {
		int r = 0;
		int alpha = RunEnvironment.getInstance().getParameters().getInteger("pollutionAlpha");
		int beta = RunEnvironment.getInstance().getParameters().getInteger("pollutionBeta");
		
		r = alpha*owner.getAmountGathered() + beta*owner.getAmountMetabolized();
		
		
		return r;
	}

}
