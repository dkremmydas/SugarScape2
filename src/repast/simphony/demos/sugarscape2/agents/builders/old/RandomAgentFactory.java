package repast.simphony.demos.sugarscape2.agents.builders.old;

import repast.simphony.demos.sugarscape2.agents.AgentResource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p37;
import repast.simphony.demos.sugarscape2.agents.rules.old.death.DeathAbility;
import repast.simphony.demos.sugarscape2.agents.rules.old.gathering.GatheringAbility;
import repast.simphony.demos.sugarscape2.agents.rules.old.metabolism.MetabolismAbility;
import repast.simphony.demos.sugarscape2.agents.rules.old.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.old.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.landscape.old.LandscapeChapter2_p30;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class RandomAgentFactory {


	private RandomAgentFactory() {}

	public static SugarAgent getAgent(String type, Integer id, LandscapeChapter2_p30 ls) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

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
			
			SugarAgent_ch2p30 a_30 = new SugarAgent_ch2p30.Builder(id)
			.onLandscape(ls)
			.atLocationX(RandomHelper.nextIntFromTo(1,ls.getDimensions().getWidth()))
			.atLocationY((RandomHelper.nextIntFromTo(1,ls.getDimensions().getHeight())))
			.withVisionLevel(RandomHelper.nextIntFromTo(1, maxVision))
			.withMetabolismRule((MetabolismAbility) Class.forName(metabolismRuleString).newInstance())
			.withGatheringRule((GatheringAbility) Class.forName(gatheringRuleString).newInstance())
			.withDeathRule((DeathAbility) Class.forName(deathRuleString).newInstance())
			.withMovementRule((MovementAbility) Class.forName(movementRuleString).newInstance())
			.withVisionRule((VisionAbility) Class.forName(visionRuleString).newInstance())
			.withSugarProperties(new AgentResource(
					RandomHelper.nextIntFromTo(1, maxInitial), 
					RandomHelper.nextIntFromTo(1, maxMetabolism)
					)
					)
			.build();
			
			return(a_30);
			
			
		case "Chapter2_p37" :
						
			SugarAgent_ch2p30 a_30_37 = (SugarAgent_ch2p30) RandomAgentFactory.getAgent("Chapter2_p30", id, ls);
			
			int minDieAge = RunEnvironment.getInstance().getParameters().getInteger("minDieAge");
			int maxDieAge = RunEnvironment.getInstance().getParameters().getInteger("maxDieAge");
			int cur_maxAge = RandomHelper.nextIntFromTo(minDieAge, maxDieAge);
			
			SugarAgent_ch2p30 a_37 = new SugarAgent_ch2p37.Builder()
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

