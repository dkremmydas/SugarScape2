package repast.simphony.demos.sugarscape2.agents.builders.loaders;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p50;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.PollutionAbility;
import repast.simphony.engine.environment.RunEnvironment;

public class SugarscapeAgentsLoaderChapter2_p50 extends
		SugarscapeAgentsLoaderChapter2_p30 {
	
	String pollutionRuleString;

	public SugarscapeAgentsLoaderChapter2_p50() {
		super();
		pollutionRuleString = RunEnvironment.getInstance().getParameters().getString("pollutionRule"); 
	}

	@Override
	public void addAgents(SimulationContext sc) {
		super.addAgents(sc);
		
		//now for each agent in the landscape, add the Pollution rule
		Iterable<SugarAgent> ai = SimulationContext.getInstance().getAgentLayer(SugarAgent.class);
		
			try {
				for(SugarAgent a : ai) {
					SugarAgent_ch2p50 ap50 = (SugarAgent_ch2p50) a;
					PollutionAbility pa;
					pa = (PollutionAbility)Class.forName(pollutionRuleString).newInstance();
					ap50.setPollutionRule(pa);
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				e.printStackTrace();
				String err = "Could not initialize PollutionRule class. Possibly "
						+ "wrong definition of class in parameters.xml";
				RunEnvironment.getInstance().endRun();
				throw new RuntimeException(err);
			}
			
		
	}
	
	

}
