package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import gr.kremmydas.sugarscape.agents.rules.movement.MovementRule_p41;

public class SugarscapeAgentsLoaderChapter2_p41 extends
		SugarscapeAgentsLoaderChapter2_p30 {

	public SugarscapeAgentsLoaderChapter2_p41() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAgents(SimulationContext sc) {
		super.addAgents(sc);
		
		//now for each agent in the landscape, modify the M-rule to p41 and the Die rule
		Iterable<Agent> ai = SimulationContext.getInstance().getAgentLayer(Agent.class);
		for(Agent a : ai) {
			AgentChapter2_p30 ap30 = (AgentChapter2_p30) a;
			ap30.setMovementRule(new MovementRule_p41());
		}
	}
	
	

}
