package repast.simphony.demos.sugarscape2.agents.rules.old.pollution;

import repast.simphony.demos.sugarscape2.agents.abilities.PollutionAbility;
import repast.simphony.demos.sugarscape2.agents.old.SugarAgent_ch2p50;
import repast.simphony.engine.environment.RunEnvironment;

public class PollutionRule_p50 implements PollutionAbility {

	public PollutionRule_p50() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int pollute(SugarAgent_ch2p50 owner) {
		int r = 0;
		int alpha = RunEnvironment.getInstance().getParameters().getInteger("pollutionAlpha");
		int beta = RunEnvironment.getInstance().getParameters().getInteger("pollutionBeta");
		
		r = alpha*owner.getAmountGathered() + beta*owner.getAmountMetabolized();
		
		
		return r;
	}

}
