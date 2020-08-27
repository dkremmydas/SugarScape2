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
	
	int age_max;
	
	
	
	
	public FiniteLifeDeath() {}
	
	public FiniteLifeDeath(int age_max) {
		super();
		this.age_max = age_max;
	}




	@Override
	public boolean shallDie(SugarAgent_ch2 a) {

		boolean dieFromStavation = (a.getResourceHolding("sugar") < 0);

		boolean dieFromAge = ((a.getAge()>=age_max)?true:false);
		
		return (dieFromStavation|dieFromAge);
		
	}


	



	@Override
	public void configureFromEnvironment() {
		
		age_max = RunEnvironment.getInstance().getParameters().getInteger("maxAge");
		
	}
	
	
	
	
	

	

}
