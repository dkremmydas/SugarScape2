package gr.kremmydas.sugarscape.loaders.landscape;



import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p50;
import repast.simphony.space.grid.RandomGridAdder;

/**
 * <p>Set the season to be summer and initialize the regenerationRate accordingly</p>
 * 
 * @author Dimitris Kremmydas
 *
 */
public class PGMLandscapeLoaderChapter2_p50 extends PGMLandscapeLoaderChapter2_p30 {
	
	@Override
	public Landscape load() {
		LandscapeChapter2_p50 ls  = new LandscapeChapter2_p50(pgmreader.getxSize(), pgmreader.getySize());
	
		super.setGrowback(ls);
		super.setInitialSugarRate(ls);
		super.setInitialSugarQuantity(ls);
		ls.getGrid().setAdder(new RandomGridAdder<Agent>());
		
		return ls;
	}
	
	
}
