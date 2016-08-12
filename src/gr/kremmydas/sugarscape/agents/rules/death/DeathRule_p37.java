package gr.kremmydas.sugarscape.agents.rules.death;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p37;
import gr.kremmydas.sugarscape.agents.rules.consumption.DefaultSugarConsumptionRule;
import gr.kremmydas.sugarscape.agents.rules.movement.MovementRule_p37;
import gr.kremmydas.sugarscape.agents.rules.vision.MooreVisionRule;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class DeathRule_p37 extends DeathRule_p30 {

	@Override
	public boolean die(Agent owner) {
		AgentChapter2_p37 o = (AgentChapter2_p37) owner;
		boolean r;
		
		if(	o.getCurrentAge() > o.getMaxAge()) {
			r = true;
		}
		else {
			r = super.die(owner);
		}
		
		//if die=true, replace agent
		if(r) {
			SimulationContext sc = SimulationContext.getInstance();
			AgentChapter2_p37 a = new AgentChapter2_p37();
			int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
			int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
			int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
			int minDieAge = RunEnvironment.getInstance().getParameters().getInteger("minDieAge");
			int maxDieAge = RunEnvironment.getInstance().getParameters().getInteger("maxDieAge");
			
			a.setId(sc.getObjects(Agent.class).size() + 1);
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
		
		return r;
		
	}
}
