package gr.kremmydas.sugarscape.rules;

import gr.kremmydas.sugarscape.Agent;
import repast.simphony.space.grid.GridPoint;

public interface MovementRule {

	public GridPoint move(Agent a);
	
}
