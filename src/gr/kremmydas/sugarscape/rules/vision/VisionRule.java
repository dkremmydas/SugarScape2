package gr.kremmydas.sugarscape.rules.vision;

import java.util.Set;

import repast.simphony.space.grid.GridPoint;

public interface VisionRule {

	public Set<GridPoint> getVisionedPoints(int x, int y);
	
}
