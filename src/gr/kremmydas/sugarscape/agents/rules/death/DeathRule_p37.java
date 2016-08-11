package gr.kremmydas.sugarscape.agents.rules.death;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p37;

public class DeathRule_p37 implements DeathAbility {

	@Override
	public boolean die(Agent owner) {
		AgentChapter2_p37 o = (AgentChapter2_p37) owner;
		
		if(o.getSugarProperties().getHolding()>0 || 
				o.getCurrentAge() > o.getMaxAge()) return false;
		else return true;
	}

}
