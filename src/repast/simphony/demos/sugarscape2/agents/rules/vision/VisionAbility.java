package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.Agent;
import repast.simphony.space.grid.GridPoint;

public interface VisionAbility {

	/**
	 * Decision of the she set of points that are currently visible
	 * 
	 * @return
	 */
	public Set<GridPoint> getVisionedPoints(Agent owner);
	
}
