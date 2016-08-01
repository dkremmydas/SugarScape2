package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2;
import gr.kremmydas.sugarscape.products.ProductGridProperties;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Grows back to the full capacity. The Ga rule (p. 23)
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultGrowbackRule implements GrowbackAbility {
	

	public DefaultGrowbackRule() {
		super();
	}

	@Override
	public GridValueLayer growback(LandscapeChapter2 landscape) {
		ProductGridProperties sgp = landscape.getSugarGridProperties();
		int y =landscape.getDimensions().getHeight();
		int x = landscape.getDimensions().getWidth();
		GridValueLayer r = new GridValueLayer("new",true,x,y);
		
		//regenerationRate
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				double nv =  Math.min(
									sgp.getCurrentQuantity().get(i,j) + sgp.getRegenerationRate().get(i,j), 
									sgp.getCapacity().get(i,j)
								);
				r.set(nv, i, j);
			}
		}
		return r;
	}
	
	
	
	
}
