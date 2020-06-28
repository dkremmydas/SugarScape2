package repast.simphony.demos.sugarscape2.landscape.old;

import repast.simphony.demos.sugarscape2.landscape.LandscapeResource;

public class LandscapeChapter4_p100 extends LandscapeChapter2_p30 {

	/**
	 * The properties of spice
	 */
	LandscapeResource spiceGridProperties;
	
	public LandscapeChapter4_p100() {
		super();
	}

	@Override
	public void init(int x, int y) {
		super.init(x, y);
		this.spiceGridProperties = new LandscapeResource(x, y, "spice");
	}

	
}
