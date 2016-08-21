package gr.kremmydas.sugarscape.agents.loaders;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import gr.kremmydas.sugarscape.agents.utilities.RandomAgentCreator;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
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
		RandomAgentCreator<AgentChapter2_p30> rac = new RandomAgentCreator<>();
		
		for(int i=0;i<n;i++) {
			
			try {			
				AgentChapter2_p30 a = rac.getNewAgent(i,(LandscapeChapter2_p30) sc.getLandscape());
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
