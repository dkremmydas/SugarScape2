package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.products.ProductGridProperties;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Grows back with a certain regeneration rate up to the full capacity. 
 * The Ga rule of p. 23, where a an integer
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class GrowbackRule_p30 implements GrowbackAbility {
	

	public GrowbackRule_p30() {
		super();
	}

	@Override
	public GridValueLayer growback(LandscapeChapter2_p30 landscape) {
		ProductGridProperties sgp = landscape.getSugarGridProperties();
		int y =landscape.getDimensions().getHeight();
		int x = landscape.getDimensions().getWidth();
		GridValueLayer r = new GridValueLayer("new",true,x,y);
		
		//update current quantity according to regeneration rate
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				double nv =  Math.min(
									sgp.getCurrentQuantity().get(i,j) + sgp.getRegenerationRate().get(i,j), 
									sgp.getCapacity().get(i,j)
								);
				r.set(nv, i, j);
			}
		}
		
		//real tick is incremented
		SimulationContext.getInstance().incrementTick();
		return r;
	}
	
	
	
	
}
