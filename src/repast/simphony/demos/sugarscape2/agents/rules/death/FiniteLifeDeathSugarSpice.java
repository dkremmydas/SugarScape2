package repast.simphony.demos.sugarscape2.agents.rules.death;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;


/**
 * Agents die only from starvation. Age is irrelevant. 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class FiniteLifeDeathSugarSpice extends FiniteLifeDeath  {
	

	public FiniteLifeDeathSugarSpice() {
		super();
	}

	public FiniteLifeDeathSugarSpice(int age_max) {
		super(age_max);
	}

	@Override
	public boolean shallDie(SugarAgent_ch2 a) {


		boolean dieFromStavation = false;

		for(String resource: a.resourceAvailableResources()) {

			if(a.resourceGetHolding(resource)< 0) {
				dieFromStavation = true;
			}
		}


		boolean dieFromAge = ((a.getAge()>=age_max)?true:false);

		return (dieFromStavation|dieFromAge);

	}

	@Override
	public void configureFromEnvironment() {
		super.configureFromEnvironment();
	}

	
	



}
