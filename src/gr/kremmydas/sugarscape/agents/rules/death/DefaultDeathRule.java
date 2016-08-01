package gr.kremmydas.sugarscape.agents.rules.death;

import gr.kremmydas.sugarscape.agents.AgentChapter2;

public class DefaultDeathRule implements DeathAbility {

	@Override
	public boolean die(AgentChapter2 owner) {
		if(owner.getSugarProperties().getHolding()>0) return false;
		else return true;
	}

}
