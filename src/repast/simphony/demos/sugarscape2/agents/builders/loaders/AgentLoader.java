package repast.simphony.demos.sugarscape2.agents.builders.loaders;

import repast.simphony.demos.sugarscape2.SimulationContext;

/**
 * 
 * Adds agents to the {@link SimulationContext}
 * 
 * @author Dimitris Kremmyda s
 *
 */
public interface AgentLoader {
	
	/**
	 * Add any agents in the {@link SimulationContext} here
	 * 
	 * @param sc {@link SimulationContext}
	 */
	public void addAgents(SimulationContext sc);
	

}
