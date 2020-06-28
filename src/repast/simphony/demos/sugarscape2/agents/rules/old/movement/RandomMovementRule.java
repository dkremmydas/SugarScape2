package repast.simphony.demos.sugarscape2.agents.rules.old.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class RandomMovementRule implements MovementAbility {

	public RandomMovementRule() {
		super();
	}

	/**
	 * Move to a random visible point that is not already occupied
	 */
	@Override
	public GridPoint move(SugarAgent owner) {
		SugarAgent_ch2p30 o = (SugarAgent_ch2p30) owner;
		
		List<GridPoint> gps = new ArrayList<GridPoint>(o.getVisionRule().getVisionedPoints(owner));
		
		Collections.shuffle(gps);
		
		DefaultGrid<SugarAgent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		for(GridPoint gp: gps) {
			if(! dg.getObjectsAt(gp.getX(),gp.getY()).iterator().hasNext()) return gp;
		}
		
		//If everything else is occupied by others, stay at the same point
		return dg.getLocation(owner);
	}

}
