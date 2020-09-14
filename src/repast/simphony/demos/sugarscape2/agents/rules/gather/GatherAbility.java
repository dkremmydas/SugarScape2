package repast.simphony.demos.sugarscape2.agents.rules.gather;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

public interface GatherAbility {
	
	/**
	 * 
	 * @param a {@link SugarAgent_ch2}
	 * @param g the {@link GridPoint} from which to gather the sugar
	 * @return the quantity of sugar the agent will gather
	 */
	public CaseInsensitiveMap<String,Integer> gather(SugarAgent_ch2 a,GridPoint g);
	
		
	
	/**
	 * The amount of the resource gathered in the last time
	 * @param a
	 * @return
	 */
	public CaseInsensitiveMap<String,Integer> getAmountGathered(SugarAgent_ch2 a);
	
	
}
