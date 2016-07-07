package gr.kremmydas.sugarscape.rules.movement;

import gr.kremmydas.sugarscape.Agent;
import repast.simphony.space.grid.GridPoint;

public interface MovementRule {

	public GridPoint move(Agent a);
	
}
