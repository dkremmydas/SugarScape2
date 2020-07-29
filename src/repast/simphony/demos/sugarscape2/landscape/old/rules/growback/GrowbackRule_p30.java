package repast.simphony.demos.sugarscape2.landscape.old.rules.growback;

import repast.simphony.demos.sugarscape2.agents.abilities.space.GrowbackAbility;
import repast.simphony.demos.sugarscape2.landscape.LandscapeResource;
import repast.simphony.demos.sugarscape2.landscape.old.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.landscape.old.SimulationContext;
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
		LandscapeResource sgp = landscape.getSugarGridProperties();
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
