package gr.kremmydas.sugarscape.rules;

import gr.kremmydas.sugarscape.agents.Agent;

/**
 * A rule. The wner should always be defined.
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class AbstractRule {
	
	protected Agent owner;	
	

	public AbstractRule(Agent owner) {
		super();
		this.owner = owner;
	}

}
