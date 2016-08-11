package gr.kremmydas.sugarscape.agents.rules.death;

import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;

public class DefaultDeathRule implements DeathAbility {

	@Override
	public boolean die(AgentChapter2_p30 owner) {
		if(owner.getSugarProperties().getHolding()>0) return false;
		else return true;
	}

}
