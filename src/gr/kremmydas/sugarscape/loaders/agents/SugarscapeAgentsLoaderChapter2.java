package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.AgentChapter2;
import gr.kremmydas.sugarscape.agents.rules.consumption.DefaultSugarConsumptionRule;
import gr.kremmydas.sugarscape.agents.rules.death.DefaultDeathRule;
import gr.kremmydas.sugarscape.agents.rules.movement.DefaultMovementRule;
import gr.kremmydas.sugarscape.agents.rules.vision.DefaultVisionRule;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import repast.simphony.random.RandomHelper;



public class SugarscapeAgentsLoaderChapter2 implements AgentLoader {

	public SugarscapeAgentsLoaderChapter2() {
		
	}

	/**
	 * A replication of the agent creation of the book. 400 agents with random 
	 * genetic characteristics (vision and metabolism) and initial position.
	 */
	@Override
	public void addAgents(SimulationContext sc) {
		RandomHelper.createUniform();
		
		int n=400;
		int maxVision = 3; int maxMetabolism = 3; int maxInitial = 3;
		for(int i=0;i<n;i++) {
			AgentChapter2 a = new AgentChapter2();
			
			a.setId(i);
			a.setMyLandscape((LandscapeChapter2) sc.getLandscape());
			a.setVisionLevel(RandomHelper.nextIntFromTo(0, maxVision));
			a.setConsumptionRule(new DefaultSugarConsumptionRule());
			a.setDeathRule(new DefaultDeathRule());
			a.setMovementRule(new DefaultMovementRule());
			a.setVisionRule(new DefaultVisionRule());
			a.setSugarProperties(new ProductAgentProperties(RandomHelper.nextIntFromTo(0, maxInitial), RandomHelper.nextIntFromTo(1, maxMetabolism)));
			
			sc.add(a);
		}
	}

}
