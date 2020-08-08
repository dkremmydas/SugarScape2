package repast.simphony.demos.sugarscape2.agents.behaviors;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class AgentBehavior_ch2_DP extends AgentBehavior_ch2 {

	int age = 1;
	int age_max;

	public AgentBehavior_ch2_DP(String valueLayerName, int levelOfVision, int age_max) {
		super(valueLayerName, levelOfVision);
		this.age_max = age_max;
	}


	@Override
	public boolean shallDie(SugarAgent_ch2 a) {

		boolean dieFromStavation = super.shallDie(a);

		boolean dieFromAge = ((this.age>=age_max)?true:false);

		this.age++;

		return dieFromStavation&&dieFromAge;

	}


	public static AgentBehavior_ch2_DP fromRunenvParameters(String valueLayerName) {

		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision");
		int maxAge = RunEnvironment.getInstance().getParameters().getInteger("maxAge");

		return new AgentBehavior_ch2_DP(
				valueLayerName, 
				RandomHelper.nextIntFromTo(1, maxVision),
				RandomHelper.nextIntFromTo(1, maxAge)
				);

	}









}
