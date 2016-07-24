package gr.kremmydas.sugarscape.landscape.rules;

import repast.simphony.space.grid.GridPoint;

/**
 * A rule. The owner is the gridpoint that holds the rule and should always be defined.
 * 
 * @author Dimitris Kremmydas
 *
 */
public abstract class AbstractGridPointRule {
	
	protected GridPoint owner;	
	

	public AbstractGridPointRule(GridPoint owner) {
		super();
		this.owner = owner;
	}

}
