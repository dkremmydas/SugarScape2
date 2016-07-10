package gr.kremmydas.sugarscape.rules.movement;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;

import java.util.Set;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public class DefaultMovementRule extends AbstractMovementRule {

	public DefaultMovementRule(Agent owner) {
		super(owner);
	}

	/**
	 * Move to the visible point with the greatest concentration of sugar
	 */
	@Override
	GridPoint move() {
		GridValueLayer gvl = SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCurrentQuantity();
		Set<GridPoint> gps = this.owner.getVisionRule().getVisionedPoints();
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
