package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import gr.kremmydas.sugarscape.agents.rules.death.DeathAbility;
import gr.kremmydas.sugarscape.agents.rules.gathering.GatheringAbility;
import gr.kremmydas.sugarscape.agents.rules.metabolism.MetabolismAbility;
import gr.kremmydas.sugarscape.agents.rules.movement.MoveAbility;
import gr.kremmydas.sugarscape.agents.rules.vision.VisionAbility;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;



public class SugarscapeAgentsLoaderChapter2_p30 implements AgentLoader {

	public SugarscapeAgentsLoaderChapter2_p30() {
		
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
		String agentClass =  RunEnvironment.getInstance().getParameters().getString("agentClass");
		String visionRuleString = RunEnvironment.getInstance().getParameters().getString("visionRule");
		String movementRuleString = RunEnvironment.getInstance().getParameters().getString("movementRule");
		String gatheringRuleString = RunEnvironment.getInstance().getParameters().getString("gatheringRule");
		String metabolismRuleString = RunEnvironment.getInstance().getParameters().getString("metabolismRule");
		String deathRuleString = RunEnvironment.getInstance().getParameters().getString("deathRule");
		
		for(int i=0;i<n;i++) {
			AgentChapter2_p30 a;
			try {
				a = (AgentChapter2_p30) Class.forName(agentClass).newInstance();
				a.setId(i);
				a.setMyLandscape((LandscapeChapter2_p30) sc.getLandscape());
				
				a.setMetabolismRule((MetabolismAbility) Class.forName(metabolismRuleString).newInstance());
				a.setGatheringRule((GatheringAbility) Class.forName(gatheringRuleString).newInstance());
				a.setDeathRule((DeathAbility) Class.forName(deathRuleString).newInstance());
				a.setMovementRule((MoveAbility) Class.forName(movementRuleString).newInstance()); 
				a.setVisionRule((VisionAbility) Class.forName(visionRuleString).newInstance());
		
				a.setSugarProperties(new ProductAgentProperties(RandomHelper.nextIntFromTo(1, maxInitial), RandomHelper.nextIntFromTo(1, maxMetabolism)));
				a.setVisionLevel(RandomHelper.nextIntFromTo(1, maxVision));
				
				sc.add(a);
				
			} catch (InstantiationException e) {
				System.err.println("There was a problem with instatianating classes. Check class names on parameters.xml");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("A class (possibly rule) was not found. Check class names on parameters.xml");
				e.printStackTrace();
			}
			
			
		}
	}

}
