package gr.kremmydas.sugarscape.agents.rules.vision;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.query.space.grid.GridCell;
import repast.simphony.query.space.grid.GridCellNgh;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class DefaultVisionRule implements VisionAbility {
	
	int visionRadius = 1;

	public DefaultVisionRule() {
		super();
	}

	@Override
	public Set<GridPoint> getVisionedPoints(Agent owner) {
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		Set<GridPoint> r = new HashSet<>();
		GridPoint gp = dg.getLocation(owner);
		
		dg.getGridPointTranslator().translate(location, displacement);
		
		@SuppressWarnings("rawtypes")
		//http://stackoverflow.com/questions/37113194/retrieve-moore-neighborhood-value-stored-in-a-list
		GridCellNgh<GridCell> n = new GridCellNgh<GridCell>(dg, gp, GridCell.class, visionRadius,visionRadius);
		
		
		for(@SuppressWarnings("rawtypes") GridCell gc : n.getNeighborhood(true)) {
			r.add(gc.getPoint());
		}
		
		return r;
		
		
	}

}
