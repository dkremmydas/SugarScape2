package gr.kremmydas.sugarscape.agents.rules.vision;

import gr.kremmydas.sugarscape.agents.Agent;

import java.util.Set;

import repast.simphony.space.grid.GridPoint;

public interface VisionAbility {

	/**
	 * Decision of the she set of points that are currently visible
	 * 
	 * @return
	 */
	public Set<GridPoint> getVisionedPoints(Agent owner);
	
}
