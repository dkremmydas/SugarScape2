package gr.kremmydas.sugarscape.landscape.rules.growback;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.products.ProductGridProperties;
import repast.simphony.valueLayer.GridValueLayer;

public class GrowbackRule_p44 extends GrowbackRule_p30 {
	
	private static Season season = Season.Summer;

	public GrowbackRule_p44() {}

	@Override
	public GridValueLayer growback(LandscapeChapter2_p30 landscape) {
		//modify regeneration
		ProductGridProperties sgp = landscape.getSugarGridProperties();
		int y =landscape.getDimensions().getHeight();
		int x = landscape.getDimensions().getWidth();
		//regenerationRate
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				sgp.setRegenerationRate(regenerationRate);
			}
		}
		
		//return the growback
		return super.growback(landscape);
	}
	
	enum Season {
		Summer, Winter
	}
	
	

}
