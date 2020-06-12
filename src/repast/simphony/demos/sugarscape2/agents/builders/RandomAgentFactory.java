package repast.simphony.demos.sugarscape2.agents.builders;

import repast.simphony.demos.sugarscape2.agents.Agent;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p30;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p37;
import repast.simphony.demos.sugarscape2.agents.rules.death.DeathAbility;
import repast.simphony.demos.sugarscape2.agents.rules.gathering.GatheringAbility;
import repast.simphony.demos.sugarscape2.agents.rules.metabolism.MetabolismAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.products.ProductAgentProperties;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class RandomAgentFactory {


	private RandomAgentFactory() {}

	public static Agent getAgent(String type, Integer id, LandscapeChapter2_p30 ls) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		int maxVision, maxMetabolism, maxInitial;
		String agentClass, visionRuleString, movementRuleString, gatheringRuleString, metabolismRuleString, deathRuleString;

		
		switch (type) {
		case "Chapter2_p30" :
			maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
			maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
			maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
			agentClass =  RunEnvironment.getInstance().getParameters().getString("agentClass");
			visionRuleString = RunEnvironment.getInstance().getParameters().getString("visionRule");
			movementRuleString = RunEnvironment.getInstance().getParameters().getString("movementRule");
			gatheringRuleString = RunEnvironment.getInstance().getParameters().getString("gatheringRule");
			metabolismRuleString = RunEnvironment.getInstance().getParameters().getString("metabolismRule");
			deathRuleString = RunEnvironment.getInstance().getParameters().getString("deathRule");
			
			AgentChapter2_p30 a_30 = new AgentChapter2_p30.Builder(id)
			.onLandscape(ls)
			.atLocationX(RandomHelper.nextIntFromTo(1,ls.getDimensions().getWidth()))
			.atLocationY((RandomHelper.nextIntFromTo(1,ls.getDimensions().getHeight())))
			.withVisionLevel(RandomHelper.nextIntFromTo(1, maxVision))
			.withMetabolismRule((MetabolismAbility) Class.forName(metabolismRuleString).newInstance())
			.withGatheringRule((GatheringAbility) Class.forName(gatheringRuleString).newInstance())
			.withDeathRule((DeathAbility) Class.forName(deathRuleString).newInstance())
			.withMovementRule((MovementAbility) Class.forName(movementRuleString).newInstance())
			.withVisionRule((VisionAbility) Class.forName(visionRuleString).newInstance())
			.withSugarProperties(new ProductAgentProperties(
					RandomHelper.nextIntFromTo(1, maxInitial), 
					RandomHelper.nextIntFromTo(1, maxMetabolism)
					)
					)
			.build();
			
			return(a_30);
			
			
		case "Chapter2_p37" :
			maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
			maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
			maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
			agentClass =  RunEnvironment.getInstance().getParameters().getString("agentClass");
			visionRuleString = RunEnvironment.getInstance().getParameters().getString("visionRule");
			movementRuleString = RunEnvironment.getInstance().getParameters().getString("movementRule");
			gatheringRuleString = RunEnvironment.getInstance().getParameters().getString("gatheringRule");
			metabolismRuleString = RunEnvironment.getInstance().getParameters().getString("metabolismRule");
			deathRuleString = RunEnvironment.getInstance().getParameters().getString("deathRule");
			
			AgentChapter2_p30 a_30_37 = new AgentChapter2_p30.Builder(id)
			.onLandscape(ls)
			.atLocationX(RandomHelper.nextIntFromTo(1,ls.getDimensions().getWidth()))
			.atLocationY((RandomHelper.nextIntFromTo(1,ls.getDimensions().getHeight())))
			.withVisionLevel(RandomHelper.nextIntFromTo(1, maxVision))
			.withMetabolismRule((MetabolismAbility) Class.forName(metabolismRuleString).newInstance())
			.withGatheringRule((GatheringAbility) Class.forName(gatheringRuleString).newInstance())
			.withDeathRule((DeathAbility) Class.forName(deathRuleString).newInstance())
			.withMovementRule((MovementAbility) Class.forName(movementRuleString).newInstance())
			.withVisionRule((VisionAbility) Class.forName(visionRuleString).newInstance())
			.withSugarProperties(new ProductAgentProperties(
					RandomHelper.nextIntFromTo(1, maxInitial), 
					RandomHelper.nextIntFromTo(1, maxMetabolism)
					)
					)
			.build();
			
			int minDieAge = RunEnvironment.getInstance().getParameters().getInteger("minDieAge");
			int maxDieAge = RunEnvironment.getInstance().getParameters().getInteger("maxDieAge");
			int cur_maxAge = RandomHelper.nextIntFromTo(minDieAge, maxDieAge);
			
			AgentChapter2_p30 a_37 = new AgentChapter2_p37.Builder()
					.agentCh30(a_30_37)
					.maxAge(cur_maxAge)
					.build();
			
			return(a_37);
			
			

		default:
			break;
		}

		return null;
	}



}


