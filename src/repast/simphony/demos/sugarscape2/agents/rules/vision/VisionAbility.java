package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

public interface VisionAbility {
	

	/**
	 * The {@link GridPoint}s that the agent can see
	 * It shall not contain the position where the agent is
	 * It shall not contain points occupied by other agents
	 * @param a
	 * @return a {@link HashSet} of {@link GridPoint}s that the agent is able to see
	 */
	public Set<GridPoint> seeEmpty(SugarAgent_ch2 a);
	
	
	
	/**
	 * The {@link GridPoint}s that the agent can see
	 * It shall not contain the position where the agent is
	 * @param a
	 * @return a {@link HashSet} of {@link GridPoint}s that the agent is able to see
	 */
	public Set<GridPoint> seeAll(SugarAgent_ch2 a);


}
