package repast.simphony.demos.sugarscape2.landscape;

import repast.simphony.demos.sugarscape2.landscape.old.rules.growback.GrowbackAbility;

public class Landscape_ch2 {

	/**
	 * The properties of sugar
	 */
	LandscapeResource sugar;

	int x,y;

	/**
	 * The growback rule
	 */
	GrowbackAbility growbackRule;


	public Landscape_ch2(int x, int y) {
		this.x = x;
		this.y = y;
		this.sugar = new LandscapeResource(x, y, "sugar");
	}



	public  Landscape_ch2 updateSugarLevel(double[][] level) {

		for(int x =0 ; x < level.length; ++x)
			for(int y =0; y<level[x].length;++y)
				this.sugar.setLevelXY(x, y, level[x][y]);

		return this;
	}
	
	public  Landscape_ch2 updateSugarLevel(int[][] level) {

		for(int x =0 ; x < level.length; ++x)
			for(int y =0; y<level[x].length;++y)
				this.sugar.setLevelXY(x, y, level[x][y]);

		return this;
	}



	public  Landscape_ch2 updateSugarLevel(int x, int y, double level) {
		this.sugar.setLevelXY(x, y, level);		
		return this;

	}
	
	
	public  Landscape_ch2 updateSugarCapacity(double[][] level) {

		for(int x =0 ; x < level.length; ++x)
			for(int y =0; y<level[x].length;++y)
				this.sugar.setCapacityXY(x, y, level[x][y]);

		return this;

	}


	public  Landscape_ch2 updateSugarCapacity(int x, int y, double level) {
		this.sugar.setCapacityXY(x, y, level);		
		return this;

	}


	public Landscape_ch2 updateSugarCapacity(int[][] level) {
		
		for(int x =0 ; x < level.length; ++x)
			for(int y =0; y<level[x].length;++y)
				this.sugar.setCapacityXY(x, y, level[x][y]);

		return this;

		
	}


}
