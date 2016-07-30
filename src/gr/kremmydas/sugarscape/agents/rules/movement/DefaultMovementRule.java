package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public class DefaultMovementRule implements MoveAbility {

	public DefaultMovementRule() {
		super();
	}

	/**
	 * Move to the visible point with the greatest concentration of sugar
	 */
	@Override
	public GridPoint move(Agent owner) {
		List<GridPoint> gps = new ArrayList<GridPoint>(owner.getVisionRule().getVisionedPoints(owner));
		Collections.sort(gps, new Comparator<GridPoint>() {
			@Override
			public int compare(GridPoint arg0, GridPoint arg1) {
				GridValueLayer gvl = SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCurrentQuantity();
				Double q1 = gvl.get(arg0.getX(),arg0.getY());
				double q2 = gvl.get(arg1.getX(),arg1.getY());
				return q1.compareTo(q2);
			}
		});
		
		//Return the GridPoint that is higher in the list and no one else is there
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		for(GridPoint gp: gps) {
			if(! dg.getObjectsAt(gp.getX(),gp.getY()).iterator().hasNext()) return gp;
		}
		
		//If everything else is occupied by others, stay at the same point
		return dg.getLocation(owner);
	}

}
