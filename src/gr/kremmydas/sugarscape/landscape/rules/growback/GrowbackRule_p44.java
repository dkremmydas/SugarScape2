package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
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
		//System.out.println(realTick + " | check:" + ((realTick%(2*gamma))/gamma) + " | season: " + GrowbackRule_p44.season);
		
		//regenerationRate change
		if(  (((realTick%(2*gamma))/gamma)>=1 && GrowbackRule_p44.season==Season.Winter) 
			|| (((realTick%(2*gamma))/gamma)<1 && GrowbackRule_p44.season==Season.Summer) 
				
		) {doModify = true;}
		
		if(doModify){	
			
			//flip season
			GrowbackRule_p44.changeSeason();
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
		return GrowbackRule_p44.season;
	}

	public static void setSeasonSummer() {
		GrowbackRule_p44.season = Season.Summer;
	}
	
	public static void setSeasonWinter() {
		GrowbackRule_p44.season = Season.Winter;
	}
	
	public static void changeSeason() {
		if(GrowbackRule_p44.season==Season.Winter) 
			{GrowbackRule_p44.season=Season.Summer;} 
		else 
			{GrowbackRule_p44.season=Season.Winter;}
	}
	
	public void setRegenerationRate(LandscapeChapter2_p30 landscape) {
		
		int alpha = RunEnvironment.getInstance().getParameters().getInteger("SummerRegenerationRate");
		int beta = RunEnvironment.getInstance().getParameters().getInteger("WinterRegenerationRate");
		GridValueLayer gvl = landscape.getSugarGridProperties().getRegenerationRate();
		
		int y =landscape.getDimensions().getHeight();
		int x = landscape.getDimensions().getWidth();
		
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				if(j>(y/2)) {
					if(season==Season.Summer) {gvl.set((double)alpha,i,j);} else {gvl.set((double)beta,i,j);}
				}
				else {
					if(season==Season.Summer) {gvl.set((double)beta,i,j);} else {gvl.set((double)alpha,i,j);}
				}
				
			}
		}
	}

}
