package gr.kremmydas.sugarscape.agents.rules;

import gr.kremmydas.sugarscape.agents.Agent;

/**
 * A rule. The wner should always be defined.
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class AbstractAgentRule {
	
	protected Agent owner;	
	

	public AbstractAgentRule(Agent owner) {
		super();
		this.owner = owner;
	}

}
