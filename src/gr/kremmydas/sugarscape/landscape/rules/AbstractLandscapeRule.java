package gr.kremmydas.sugarscape.landscape.rules;

import gr.kremmydas.sugarscape.landscape.Landscape;

/**
 * A rule. The owner is the gridpoint that holds the rule and should always be defined.
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class AbstractLandscapeRule {
	
	protected Landscape owner;	
	

	public AbstractLandscapeRule(Landscape owner) {
		super();
		this.owner = owner;
	}

}
