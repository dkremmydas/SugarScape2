package repast.simphony.demos.sugarscape2.agents.rules.gather;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

public interface GatherAbility {
	
	/**
	 * 
	 * @param a {@link SugarAgent_ch2}
	 * @param g the {@link GridPoint} from which to gather the sugar
	 * @return the quantity of sugar the agent will gather
	 */
	public int gather(SugarAgent_ch2 a,GridPoint g);

}
