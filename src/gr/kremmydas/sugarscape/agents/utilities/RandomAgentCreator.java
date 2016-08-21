package gr.kremmydas.sugarscape.agents.utilities;

import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import gr.kremmydas.sugarscape.agents.rules.death.DeathAbility;
import gr.kremmydas.sugarscape.agents.rules.gathering.GatheringAbility;
import gr.kremmydas.sugarscape.agents.rules.metabolism.MetabolismAbility;
import gr.kremmydas.sugarscape.agents.rules.movement.MovementAbility;
import gr.kremmydas.sugarscape.agents.rules.vision.VisionAbility;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class RandomAgentCreator<T extends AgentChapter2_p30> {
	
	int maxVision, maxMetabolism, maxInitial;
	String agentClass, visionRuleString, movementRuleString, gatheringRuleString, metabolismRuleString, deathRuleString;

	public RandomAgentCreator() {
		maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
		maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
		agentClass =  RunEnvironment.getInstance().getParameters().getString("agentClass");
		visionRuleString = RunEnvironment.getInstance().getParameters().getString("visionRule");
		movementRuleString = RunEnvironment.getInstance().getParameters().getString("movementRule");
		gatheringRuleString = RunEnvironment.getInstance().getParameters().getString("gatheringRule");
		metabolismRuleString = RunEnvironment.getInstance().getParameters().getString("metabolismRule");
		deathRuleString = RunEnvironment.getInstance().getParameters().getString("deathRule");
	}
	
	public T getNewAgent(int id, LandscapeChapter2_p30 ls) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException {

		@SuppressWarnings("unchecked")
		T a = (T) Class.forName(agentClass).newInstance();

		a.setId(id);
		a.setMyLandscape((LandscapeChapter2_p30) ls);
		
		a.setMetabolismRule((MetabolismAbility) Class.forName(metabolismRuleString).newInstance());
		a.setGatheringRule((GatheringAbility) Class.forName(gatheringRuleString).newInstance());
		a.setDeathRule((DeathAbility) Class.forName(deathRuleString).newInstance());
		a.setMovementRule((MovementAbility) Class.forName(movementRuleString).newInstance()); 
		a.setVisionRule((VisionAbility) Class.forName(visionRuleString).newInstance());
		
		a.setSugarProperties(new ProductAgentProperties(RandomHelper.nextIntFromTo(1, maxInitial), RandomHelper.nextIntFromTo(1, maxMetabolism)));
		a.setVisionLevel(RandomHelper.nextIntFromTo(1, maxVision));
		
		return a;
	}

}
