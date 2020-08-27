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
		if(a.getResourceHolding("sugar") < 0) {
			return  true;	
		} 	else {
			return false;
		}
	}




	@Override
	public void configureFromEnvironment() {}
	
	
	
	
	

	

}
