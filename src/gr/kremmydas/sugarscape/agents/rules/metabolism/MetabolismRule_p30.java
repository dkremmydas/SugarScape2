package gr.kremmydas.sugarscape.agents.rules.metabolism;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;

public class MetabolismRule_p30 implements MetabolismAbility {

	public MetabolismRule_p30() {
		super();
	}

	/**
	 * Metabolize sugar according to agent's metabolism needs
	 */
	@Override
	public Integer metabolize(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		return (int) o.getMetabolism();
	}



}
