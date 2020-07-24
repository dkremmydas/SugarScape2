package repast.simphony.demos.sugarscape2.landscape.old.loaders;

import repast.simphony.demos.sugarscape2.agents.Landscape;
import repast.simphony.demos.sugarscape2.landscape.LandscapeResource;
import repast.simphony.demos.sugarscape2.landscape.old.LandscapeChapter4_p100;
import repast.simphony.engine.environment.RunEnvironment;

public class PGMLandscapeLoaderChapter4_p100 extends
		PGMLandscapeLoaderChapter2_p30 {
	
	protected String SpiceInputFile = "./data/spicespace.pgm";

	public PGMLandscapeLoaderChapter4_p100() {
		super();
	}

	@Override
	public Landscape load() {
		//load sugar landscape
		LandscapeChapter4_p100 ls = (LandscapeChapter4_p100) super.load();
		
		//now add spice properties (remember LandscapeChapter4_p100 -spice- EXTENDS LandscapeChapter2_p30 -sugar-)
		
		
		return ls;
	}
	
	protected void setInitialSpiceQuantity(LandscapeChapter4_p100 ls) {
		LandscapeResource pgp = ls.getSugarGridProperties();
		for(int x=0;x<ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<ls.getDimensions().getHeight(); y++) {
				int s1 = (int)pgmreader.getMatrix()[x][y];
				int s2 = (int)pgmreader.getMatrix()[x][y];
				pgp.getCurrentQuantity().set((double)s1, x,y);
				pgp.getCapacity().set((double)s2, x,y);
			}
		}
	}
	
	protected void setInitialSpiceRate(LandscapeChapter4_p100 ls) {
		LandscapeResource pgp = ls.getSugarGridProperties();
		for(int x=0;x<ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<ls.getDimensions().getHeight(); y++) {
				pgp.getRegenerationRate().set(
						(double)RunEnvironment.getInstance().getParameters().getInteger("regenerationRate"), 
						x,y);
			}
		}
		
	}
	
	

}
