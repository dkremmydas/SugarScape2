package repast.simphony.demos.sugarscape2.agents.rules.old.vision;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.space.grid.GridPoint;

public interface VisionAbility {

	/**
	 * Decision of the she set of points that are currently visible
	 * 
	 * @return
	 */
	public Set<GridPoint> getVisionedPoints(SugarAgent owner);
	
}
