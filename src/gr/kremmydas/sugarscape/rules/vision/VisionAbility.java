package gr.kremmydas.sugarscape.rules.vision;

import java.util.Set;

import repast.simphony.space.grid.GridPoint;

public interface VisionAbility {

	/**
	 * Decision of the she set of points that are currently visible
	 * 
	 * @return
	 */
	abstract public Set<GridPoint> getVisionedPoints();
	
}
