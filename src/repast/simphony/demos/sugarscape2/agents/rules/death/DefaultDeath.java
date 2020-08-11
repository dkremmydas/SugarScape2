package repast.simphony.demos.sugarscape2.agents.rules.death;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;


/**
 * Agents die only from starvation. Age is irrelevant. 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultDeath implements DieAbility {
	
	
	@Override
	public boolean shallDie(SugarAgent_ch2 a) {
		if(a.getSugarWealth() < 0) {
			return  true;	
		} 	else {
			return false;
		}
	}


	
		
	@Override
	public int getAge(SugarAgent_ch2 a) {
		return 1;
	}

	
	@Override
	public void incrementAge(SugarAgent_ch2 a) {
	}



	public static DieAbility fromRunenvParameters() {
		return new DefaultDeath();
	}
	
	
	
	
	

	

}
