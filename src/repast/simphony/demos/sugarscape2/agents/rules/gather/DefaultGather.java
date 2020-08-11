package repast.simphony.demos.sugarscape2.agents.rules.gather;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
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
	
	
	/**
	 * The name of the {@link ValueLayer} of the resource
	 */
	private String valueLayerName;
	
	
	
	public DefaultGather(String valueLayerName) {
		this.valueLayerName  = valueLayerName;
	}	
	

	@Override
	public int gather(SugarAgent_ch2 a,GridPoint g) {
		
		GridValueLayer gvl = (GridValueLayer) a.getContext().getValueLayer(valueLayerName);
		
		return (int) gvl.get(g.getX(),g.getY());
		
	}


	
	@Override
	public String getValueLayerName() {
		return valueLayerName;
	}



	public static DefaultGather fromRunenvParameters(String valueLayerName) {
		
		return new DefaultGather(valueLayerName);
		
	}
	
	
	
	
	

	

}
