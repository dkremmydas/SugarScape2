package gr.kremmydas.sugarscape.loaders.agents;

import gr.kremmydas.sugarscape.SimulationContext;

/**
 * 
 * Adds agents to the {@link SimulationContext}
 * 
 * @author Dimitris Kremmydas
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
