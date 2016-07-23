package gr.kremmydas.sugarscape.rules.vision;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class DefaultVisionRule extends AbstractVisionRule {
	
	int visionRadius = 1;

	public DefaultVisionRule(Agent owner) {
		super(owner);
	}

	@Override
	public Set<GridPoint> getVisionedPoints() {
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(this.owner);
		
		@SuppressWarnings("rawtypes")
		//http://stackoverflow.com/questions/37113194/retrieve-moore-neighborhood-value-stored-in-a-list
		GridCellNgh<GridCell> n = new GridCellNgh<GridCell>(dg, gp, GridCell.class, visionRadius,visionRadius);
		
		Set<GridPoint> r = new HashSet<>();
		for(@SuppressWarnings("rawtypes") GridCell gc : n.getNeighborhood(true)) {
			r.add(gc.getPoint());
		}
		
		return r;
		
		
	}

}
