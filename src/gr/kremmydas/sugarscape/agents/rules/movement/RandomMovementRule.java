package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class RandomMovementRule implements MoveAbility {

	public RandomMovementRule() {
		super();
	}

	/**
	 * Move to a random visible point that is not already occupied
	 */
	@Override
	public GridPoint move(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		List<GridPoint> gps = new ArrayList<GridPoint>(o.getVisionRule().getVisionedPoints(owner));
		
		Collections.shuffle(gps);
		
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		for(GridPoint gp: gps) {
			if(! dg.getObjectsAt(gp.getX(),gp.getY()).iterator().hasNext()) return gp;
		}
		
		//If everything else is occupied by others, stay at the same point
		return dg.getLocation(owner);
	}

}
