package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

public interface MovementAbility {
	
	/**
	 * 
	 * The agent must moves to a new point
	 * 
	 * @param a The {@link SugarAgent_ch2} object
	 * @param g a set of {@link GridPoint}s from which the agent will select
	 * @return {@link GridPoint}
	 */
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> g);
	
	
	public String getValueLayerName();
	

}
