package gr.kremmydas.sugarscape.agents.rules.death;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;

public class DeathRule_p30 implements DeathAbility {

	@Override
	public boolean die(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		if(o.getSugarProperties().getHolding()>0) return false;
		else return true;
	}

}
