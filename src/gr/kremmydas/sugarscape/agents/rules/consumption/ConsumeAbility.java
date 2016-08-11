package gr.kremmydas.sugarscape.agents.rules.consumption;

import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import repast.simphony.space.grid.GridPoint;

public interface ConsumeAbility {
	
	/**
	 * Decision of many units of each {@link GridPoint} to consume
	 * @return Map<GridPoint,Integer> Map of GridPoint->Consumption(int)
	 */
	public Integer consume(AgentChapter2_p30 owner);

}
