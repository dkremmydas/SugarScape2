package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p37;
import gr.kremmydas.sugarscape.agents.rules.death.DeathRule_p37;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class SugarscapeAgentsLoaderChapter2_p37 extends
		SugarscapeAgentsLoaderChapter2_p30 {

	public SugarscapeAgentsLoaderChapter2_p37() {}

	@Override
	public void addAgents(SimulationContext sc) {
		
		super.addAgents(sc);
		
		int minDieAge = RunEnvironment.getInstance().getParameters().getInteger("minDieAge");
		int maxDieAge = RunEnvironment.getInstance().getParameters().getInteger("maxDieAge");
		
		//now for each agent in the landscape, modify the M-rule to p41 and the Die rule
		Iterable<Agent> ai = SimulationContext.getInstance().getAgentLayer(Agent.class);
		for(Agent a : ai) {
			AgentChapter2_p37 ap37 = (AgentChapter2_p37) a;
			ap37.setDeathRule(new DeathRule_p37());
			ap37.setMaxAge(RandomHelper.nextIntFromTo(minDieAge, maxDieAge));
		}
	}
	
	
	

}
