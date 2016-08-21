package gr.kremmydas.sugarscape.agents.rules.death;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p37;
import gr.kremmydas.sugarscape.agents.utilities.RandomAgentCreator;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
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
		
		//if die=true, replace agent with a new random one
		if(r) {
			RandomAgentCreator<AgentChapter2_p37> rac = new RandomAgentCreator<>();		
			SimulationContext sc = SimulationContext.getInstance();
			int id = sc.getObjects(Agent.class).size() + 1;
			
			AgentChapter2_p37 a;
			try {
				a = rac.getNewAgent(id, (LandscapeChapter2_p30) sc.getLandscape());
				int minDieAge = RunEnvironment.getInstance().getParameters().getInteger("minDieAge");
				int maxDieAge = RunEnvironment.getInstance().getParameters().getInteger("maxDieAge");
				a.setMaxAge(RandomHelper.nextIntFromTo(minDieAge, maxDieAge));
				sc.add(a);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		else {
			o.setCurrentAge(o.getCurrentAge()+1);
		}
		
		return r;
		
	}
}
