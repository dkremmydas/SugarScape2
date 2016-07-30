package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.landscape.Landscape;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridFunction;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Grows back to the full capacity. The Ga rule (p. 23)
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultGrowbackRule implements GrowbackAbility {
	
	private int regenerationRate;

	public DefaultGrowbackRule() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public GridValueLayer growback(Landscape landscape) {
		int y =landscape.getDimensions().getHeight();
		int x = landscape.getDimensions().getWidth();
		ValueLayerSetToCapacity f = new ValueLayerSetToCapacity(x,y);
				;
		landscape.getSugarGridProperties().getCurrentQuantity().forEach(f,new GridPoint(0,0), x,y);
		return landscape.getSugarGridProperties().getCapacity();
		//return f.getResults();
	}
	
	
	
	private class ValueLayerSetToCapacity  implements GridFunction  {
		
		private GridValueLayer newValueLayer;
		
		public ValueLayerSetToCapacity(int x, int y) {
			newValueLayer = new GridValueLayer("newValueLayer", true, x,y);
		}

		@Override
		public void apply(double gridValue, int... location) {
			
			newValueLayer.set(
					SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCapacity().get(location[0],location[1]), 
					location
				);
			
		}
		
		public GridValueLayer getResults() {
			return this.newValueLayer;
		}
		
	}

}
