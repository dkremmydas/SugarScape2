package repast.simphony.demos.sugarscape2.space;

import repast.simphony.demos.sugarscape2.space.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.space.rules.pollution_diffusion.PollutionDiffusionAbility;
import repast.simphony.demos.sugarscape2.space.rules.replacement.ReplacementAbility;
import repast.simphony.demos.sugarscape2.utilities.PGMReader;
import repast.simphony.valueLayer.GridValueLayer;

public class SugarSpace_ch4 extends SugarSpace_ch3 {
	
	
	
	public SugarSpace_ch4(String sugar_pgm_file, String spice_pgm_file, GrowbackAbility growbackRule,
			ReplacementAbility replacementRule, PollutionDiffusionAbility diffusionRule) {

		super(sugar_pgm_file, growbackRule,	replacementRule, diffusionRule);

		configureGridFromPGM(spice_pgm_file);
		
		
	}
	
	
	/**
	 * We configure the grid of the Sugarspace based on a PGM file. The grid's width and height are set and
	 * the capacity of sugar in each grid cell is loaded.
	 * 
	 * @param pgm_file
	 */
	private void configureGridFromPGM (String spice_pgm_file) {

		//1.1 read the pgm file
		PGMReader spice_pgmreader = new PGMReader(  spice_pgm_file); 


		//1.2 create GridValueLayer of 'spice capacity'	
		GridValueLayer landscape_spiceCapacity = new  GridValueLayer("spice capacity", true, spice_pgmreader.getxSize(),spice_pgmreader.getySize());

		for(int x =0 ; x < spice_pgmreader.getxSize(); ++x)
			for(int y =0; y<spice_pgmreader.getySize();++y)
				landscape_spiceCapacity.set(
						spice_pgmreader.getMatrix()[x][y], 
						x,y);
		



		//1.3 create GridValueLayer of 'spice level'
		GridValueLayer landscape_spiceLevel = new  GridValueLayer("spice level", true, spice_pgmreader.getxSize(),spice_pgmreader.getySize());

		for(int x =0 ; x < spice_pgmreader.getxSize(); ++x)
			for(int y =0; y<spice_pgmreader.getySize();++y)
				landscape_spiceLevel.set(
						spice_pgmreader.getMatrix()[x][y], 
						x,y);
		


		//1.5 create the sugar SpaceResource
		this.resources.put("spice", new SpaceResource(landscape_spiceCapacity, landscape_spiceLevel));
		
		
		//1.6 add valueLayers to context
		this.addValueLayer(this.resources.get("spice").capacity);
		this.addValueLayer(this.resources.get("spice").holding);
		

	}

}
