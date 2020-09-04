package repast.simphony.demos.sugarscape2.agents.rules.pollution;

import java.util.Map;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

/**
 * Calculation of the pollution level emitted to one or more {@link GridPoint}s
 * 
 * @author Dimitrios Kremmydas
 *
 */
public interface PollutionAbility {
	
	
	/**
	 * Calculate the level of pollution for one or more {@link GridPoint}s, relate to 
	 * {@link SugarAgent_ch2}
	 * 
	 * @param a
	 * @return
	 */
	public Map<GridPoint, Integer> pollute(SugarAgent_ch2 a);
		

}
