package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;

import java.util.Set;

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
		GridValueLayer gvl = SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCurrentQuantity();
		Set<GridPoint> gps = owner.getVisionRule().getVisionedPoints(owner);
		GridPoint toMove = gps.iterator().next();
		double toMoveV = gvl.get(toMove.getX(),toMove.getY());
		for(GridPoint gp : gps) {
			double gpV=gvl.get(gp.getX(),gp.getY());
			if(gpV>toMoveV) {
				toMoveV=gpV;toMove=gp;
			}
		}
		return toMove;
	}

}
