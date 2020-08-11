package repast.simphony.demos.sugarscape2.agents.behaviors;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.death.DieAbility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class AgentDiesFromAge implements DieAbility {

	protected int age = 1;
	
	protected int age_max;

	public AgentDiesFromAge(int age_max) {
		this.age_max = age_max;
	}


	@Override
	public boolean shallDie(SugarAgent_ch2 a) {

		boolean dieFromStavation = (a.getSugarWealth() < 0);

		boolean dieFromAge = ((a.getAge()>=age_max)?true:false);
		
		return (dieFromStavation|dieFromAge);

	}



	@Override
	public int getAge(SugarAgent_ch2 a) {
		return age;
	}


	@Override
	public void incrementAge(SugarAgent_ch2 a) {
		this.age++;		
	}





	public static AgentDiesFromAge fromRunenvParameters() {

		int maxAge = RunEnvironment.getInstance().getParameters().getInteger("maxAge");

		return new AgentDiesFromAge(
				RandomHelper.nextIntFromTo(1, maxAge)
				);

	}




}
