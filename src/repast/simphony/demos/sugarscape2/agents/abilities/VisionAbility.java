package repast.simphony.demos.sugarscape2.agents.abilities;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

public interface VisionAbility {
	

	/**
	 * 
	 * @param a
	 * @return a {@link HashSet} of {@link GridPoint}s that the agent is able to see
	 */
	public Set<GridPoint> see(SugarAgent_ch2 a);
	

}
