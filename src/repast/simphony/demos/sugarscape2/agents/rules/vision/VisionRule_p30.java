package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

/**
 * The default vision rule (implied in the movement rule)
 * @author Dimitris Kremmydas
 *
 */
public class VisionRule_p30 implements VisionAbility {
	

	public VisionRule_p30() {
		super();
	}

	@Override
	public Set<GridPoint> getVisionedPoints(SugarAgent owner) {
		SugarAgent_ch2p30 o = (SugarAgent_ch2p30) owner;
		
		DefaultGrid<SugarAgent> dg = o.getMyLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		//System.out.println("Owner Location: " + gp);
		
		return NeighbourhoodFunctions.getVonNeumanPoints(gp, dg, o.getVisionLevel());
		
		
	}

}
