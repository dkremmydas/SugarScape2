package repast.simphony.demos.sugarscape2.agents.rules.death;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.Agent;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p37;
import repast.simphony.demos.sugarscape2.agents.builders.RandomAgentFactory;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;

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
			SimulationContext sc = SimulationContext.getInstance();
			int id = sc.getObjects(Agent.class).size() + 1;
			
			AgentChapter2_p37 a;
			try {
				a = (AgentChapter2_p37)RandomAgentFactory.getAgent("Chapter2_p37",id, (LandscapeChapter2_p30) sc.getLandscape());
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
