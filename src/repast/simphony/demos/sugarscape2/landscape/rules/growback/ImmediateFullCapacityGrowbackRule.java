package repast.simphony.demos.sugarscape2.landscape.rules.growback;

import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.products.ProductGridProperties;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Grows back the full capacity. 
 * The Ga rule of p. 26
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ImmediateFullCapacityGrowbackRule implements GrowbackAbility {
	

	public ImmediateFullCapacityGrowbackRule() {
		super();
	}

	@Override
	public GridValueLayer growback(LandscapeChapter2_p30 landscape) {
		ProductGridProperties sgp = landscape.getSugarGridProperties();
		int y =landscape.getDimensions().getHeight();
		int x = landscape.getDimensions().getWidth();
		GridValueLayer r = new GridValueLayer("new",true,x,y);
		
		//regenerationRate
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				double nv =  sgp.getCapacity().get(i,j);
				r.set(nv, i, j);
			}
		}
		return r;
	}
	
	
	
	
}
