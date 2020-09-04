package repast.simphony.demos.sugarscape2.agents.rules.gather;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public interface GatherAbility {
	
	/**
	 * 
	 * @param a {@link SugarAgent_ch2}
	 * @param g the {@link GridPoint} from which to gather the sugar
	 * @return the quantity of sugar the agent will gather
	 */
	public int gather(SugarAgent_ch2 a,GridPoint g);
	
	/**
	 * The name of the {@link GridValueLayer} that the rue is connected to
	 * @return
	 */
	public String getValueLayerName() ;
	
	
	/**
	 * The amount of the resource gathered in the last time
	 * @param a
	 * @return
	 */
	public int getAmountGathered(SugarAgent_ch2 a);
	
	
}
