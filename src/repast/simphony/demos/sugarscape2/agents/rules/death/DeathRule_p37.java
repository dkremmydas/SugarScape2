package repast.simphony.demos.sugarscape2.agents.rules.death;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p37;
import repast.simphony.demos.sugarscape2.agents.builders.RandomAgentFactory;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;

public class DeathRule_p37 extends DeathRule_p30 {

	@Override
	public boolean die(SugarAgent owner) {
		SugarAgent_ch2p37 o = (SugarAgent_ch2p37) owner;
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
			int id = sc.getObjects(SugarAgent.class).size() + 1;
			
			SugarAgent_ch2p37 a;
			try {
				a = (SugarAgent_ch2p37)RandomAgentFactory.getAgent("Chapter2_p37",id, (LandscapeChapter2_p30) sc.getLandscape());
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
