package gr.kremmydas.sugarscape.landscape.rules.growback;

import repast.simphony.space.grid.GridPoint;
import gr.kremmydas.sugarscape.landscape.rules.AbstractGridPointRule;

/**
 * Grows back to the full capacity
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultGrowbackRule extends AbstractGridPointRule implements
		GrowbackAbility {

	public DefaultGrowbackRule(GridPoint owner) {
		super(owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int growback() {
		// TODO Auto-generated method stub
		return 0;
	}

}
