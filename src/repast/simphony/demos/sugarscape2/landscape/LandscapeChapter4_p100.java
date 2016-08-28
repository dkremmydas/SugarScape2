package repast.simphony.demos.sugarscape2.landscape;

import repast.simphony.demos.sugarscape2.products.ProductGridProperties;

public class LandscapeChapter4_p100 extends LandscapeChapter2_p30 {

	/**
	 * The properties of spice
	 */
	ProductGridProperties spiceGridProperties;
	
	public LandscapeChapter4_p100() {
		super();
	}

	@Override
	public void init(int x, int y) {
		super.init(x, y);
		this.spiceGridProperties = new ProductGridProperties(x, y, "spice");
	}

	
}
