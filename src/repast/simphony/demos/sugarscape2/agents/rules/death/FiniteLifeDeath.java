package repast.simphony.demos.sugarscape2.agents.rules.death;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.engine.environment.RunEnvironment;


/**
 * Agents can die from two causes: (1) from starvation, (2) from exceeding the maximum age 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class FiniteLifeDeath implements DieAbility {
	
	int age = 1;
	
	int age_max;
	
	
	
	
	public FiniteLifeDeath() {}
	
	public FiniteLifeDeath(int age, int age_max) {
		super();
		this.age = age;
		this.age_max = age_max;
	}




	@Override
	public boolean shallDie(SugarAgent_ch2 a) {

		boolean dieFromStavation = (a.getSugarWealth() < 0);

		boolean dieFromAge = ((a.getDieRule().getAge(a)>=age_max)?true:false);
		
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




	@Override
	public void configureFromEnvironment() {
		
		age_max = RunEnvironment.getInstance().getParameters().getInteger("maxAge");
		
	}
	
	
	
	
	

	

}
