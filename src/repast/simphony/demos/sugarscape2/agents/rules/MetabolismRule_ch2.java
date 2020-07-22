package repast.simphony.demos.sugarscape2.agents.rules;

import java.util.Set;

import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.abilities.ConsumeAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.GatherAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.VisionAbility;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.engine.environment.RunState;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class MetabolismRule_ch2 implements VisionAbility,MovementAbility,GatherAbility,ConsumeAbility {
	
	
	
	@Override
	public Set<GridPoint> see(SugarAgent_ch2 a) {
		
		DefaultContext<SugarAgent_ch2> context =  (DefaultContext<SugarAgent_ch2>) RunState.getInstance().getMasterContext().getSubContext("agents");
		
		DefaultGrid<SugarAgent_ch2> grid = (DefaultGrid<SugarAgent_ch2>) context.getProjection("sugarscape");
		
		return NeighbourhoodFunctions.getVonNeumanPoints(grid.getLocation(a), grid, a.getVisionLevel());
		
	}
	
	
	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> g) {
		
		

	}

	@Override
	public int gather(SugarAgent_ch2 a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int metabolize(SugarAgent_ch2 a) {
		// TODO Auto-generated method stub
		
	}

	

}
