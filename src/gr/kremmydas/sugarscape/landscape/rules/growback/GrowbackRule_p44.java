package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.products.ProductGridProperties;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.valueLayer.GridValueLayer;

public class GrowbackRule_p44 extends GrowbackRule_p30 {
	
	private static Season season = Season.Summer;

	public GrowbackRule_p44() {}

	@Override
	public GridValueLayer growback(LandscapeChapter2_p30 landscape) {
		//modify regeneration
		boolean doModify = false;
		
		int gamma = RunEnvironment.getInstance().getParameters().getInteger("SeasonDuration");
		int realTick = SimulationContext.getInstance().getRealTick();
		System.out.println(realTick);
		
		//regenerationRate change
		if(  (((realTick%(2*gamma))/gamma)<1 && season==Season.Winter) 
			|| (((realTick%(2*gamma))/gamma)>1 && season==Season.Summer) 
				
		) {doModify = true;}
		
		if(doModify){	
			
			//flip season
			if(season==Season.Winter) {season=Season.Summer;} else {season=Season.Winter;}
			this.setRegenerationRate(landscape);	
			
		}
		
		//return the growback
		return super.growback(landscape);
	}
	
	/**
	 * What season is in the top half ?
	 * @author jkr
	 *
	 */
	enum Season {
		Summer, Winter
	}

	public static Season getSeason() {
		return season;
	}

	public static void setSeasonSummer() {
		GrowbackRule_p44.season = Season.Summer;
	}
	
	public static void setSeasonWinter() {
		GrowbackRule_p44.season = Season.Winter;
	}
	
	public void setRegenerationRate(LandscapeChapter2_p30 landscape) {
		
		int alpha = RunEnvironment.getInstance().getParameters().getInteger("SummerRegenerationRate");
		int beta = RunEnvironment.getInstance().getParameters().getInteger("WinterRegenerationRate");
		ProductGridProperties sgp = landscape.getSugarGridProperties();
		int y =landscape.getDimensions().getHeight();
		int x = landscape.getDimensions().getWidth();
		GridValueLayer r = new GridValueLayer("new",true,x,y);
		
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				if(j>(y/2)) {
					if(season==Season.Summer) {r.set(alpha,i,j);} else {r.set(beta,i,j);}
				}
				else {
					if(season==Season.Summer) {r.set(beta,i,j);} else {r.set(alpha,i,j);}
				}
				
			}
		}
		sgp.setRegenerationRate(r);
	}

}
