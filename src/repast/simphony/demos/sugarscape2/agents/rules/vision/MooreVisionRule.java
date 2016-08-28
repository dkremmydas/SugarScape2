package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.Agent;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p30;
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
	public Set<GridPoint> getVisionedPoints(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		DefaultGrid<Agent> dg = o.getMyLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		//System.out.println("Owner Location: " + gp);
		
		return NeighbourhoodFunctions.getMoorePoints(gp, dg, o.getVisionLevel());
		
	}
	

}
