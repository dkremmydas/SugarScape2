package repast.simphony.demos.sugarscape2.agents.rules.gather;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;


/**
 * Methods of this class should not change the state of agents
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultGather implements GatherAbility {
	
	int gathered_last_time = 0;
	
	
	/**
	 * The name of the {@link ValueLayer} of the resource
	 */
	private String valueLayerName;
	
	
	
	public DefaultGather(String valueLayerName) {
		this.valueLayerName  = valueLayerName;
	}	
	

	@Override
	public int gather(SugarAgent_ch2 a,GridPoint g) {
		
		GridValueLayer gvl = (GridValueLayer)  SugarSpaceFactory.getSugarspace().getValueLayer(valueLayerName);
		
		gathered_last_time = (int) gvl.get(g.getX(),g.getY());
		
		return gathered_last_time;
		
	}


	
	@Override
	public String getValueLayerName() {
		return valueLayerName;
	}


	@Override
	public int getAmountGathered(SugarAgent_ch2 a) {
		return gathered_last_time;
	}
	
	
	
	
	

	

}
