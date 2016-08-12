package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p37;
import gr.kremmydas.sugarscape.agents.rules.consumption.DefaultSugarConsumptionRule;
import gr.kremmydas.sugarscape.agents.rules.death.DeathRule_p37;
import gr.kremmydas.sugarscape.agents.rules.movement.MovementRule_p37;
import gr.kremmydas.sugarscape.agents.rules.vision.MooreVisionRule;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;



public class SugarscapeAgentsLoaderChapter2_p37 implements AgentLoader {

	public SugarscapeAgentsLoaderChapter2_p37() {
		
	}

	/**
	 * A replication of the agent creation of the book. 400 agents with random 
	 * genetic characteristics (vision and metabolism) and initial position.
	 */
	@Override
	public void addAgents(SimulationContext sc) {
		RandomHelper.createUniform();
		
		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");
		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
		int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
		int minDieAge = RunEnvironment.getInstance().getParameters().getInteger("minDieAge");
		int maxDieAge = RunEnvironment.getInstance().getParameters().getInteger("maxDieAge");
		for(int i=0;i<n;i++) {
			AgentChapter2_p37 a = new AgentChapter2_p37();
			
			a.setId(i);
			a.setMyLandscape((LandscapeChapter2_p30) sc.getLandscape());
			a.setConsumptionRule(new DefaultSugarConsumptionRule());
			a.setDeathRule(new DeathRule_p37());
			a.setMovementRule(new MovementRule_p37()); //a.setMovementRule(new RandomMovementRule());
			a.setVisionRule(new MooreVisionRule());//a.setVisionRule(new DefaultVisionRule());
			
			a.setSugarProperties(new ProductAgentProperties(RandomHelper.nextIntFromTo(1, maxInitial), RandomHelper.nextIntFromTo(1, maxMetabolism)));
			a.setVisionLevel(RandomHelper.nextIntFromTo(1, maxVision));
			a.setMaxAge(RandomHelper.nextIntFromTo(minDieAge, maxDieAge));
			
			sc.add(a);
		}
	}
	

}
