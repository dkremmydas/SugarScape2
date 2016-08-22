package gr.kremmydas.sugarscape.agents.rules.vision;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import gr.kremmydas.sugarscape.utilities.NeighbourhoodFunctions;

import java.util.Set;

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
	public Set<GridPoint> getVisionedPoints(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		DefaultGrid<Agent> dg = o.getMyLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		//System.out.println("Owner Location: " + gp);
		
		return NeighbourhoodFunctions.getVonNeumanPoints(gp, dg, o.getVisionLevel());
		
		
	}

}
