package repast.simphony.demos.sugarscape2.agents.loaders;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p30;
import repast.simphony.demos.sugarscape2.agents.utilities.RandomAgentFactory;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
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
		RandomAgentFactory rac = new RandomAgentFactory();
		
		for(int i=0;i<n;i++) {
			
			AgentChapter2_p30 a;
			try {
				a = (AgentChapter2_p30)rac.getAgent("Chapter2_p30",i,(LandscapeChapter2_p30) sc.getLandscape());
				sc.add(a);
				
			} catch (InstantiationException e) {
				System.err.println("There was a problem with instatianating classes. Check class names on parameters.xml");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println("A class (possibly rule) was not found. Check class names on parameters.xml");
				e.printStackTrace();
			}
					
			
		}
	}
	
}
