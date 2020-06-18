package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

/**
 * The vision of the agent is the Moore neighborhood 
 * (as opposed to von Neumann neighborhood that is used in the book).
 * 
 * @author Dimitris Kremmydas
 *
 */
public class MooreVisionRule implements VisionAbility {

	public MooreVisionRule() {
		super();
	}

	@Override
	public Set<GridPoint> getVisionedPoints(SugarAgent owner) {
		SugarAgent_ch2p30 o = (SugarAgent_ch2p30) owner;
		
		DefaultGrid<SugarAgent> dg = o.getMyLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		//System.out.println("Owner Location: " + gp);
		
		return NeighbourhoodFunctions.getMoorePoints(gp, dg, o.getVisionLevel());
		
	}
	

}
