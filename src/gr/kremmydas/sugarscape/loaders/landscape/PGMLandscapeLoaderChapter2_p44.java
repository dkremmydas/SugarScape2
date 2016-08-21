package gr.kremmydas.sugarscape.loaders.landscape;



import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackRule_p44;

/**
 * <p>Set the season to be summer and initialize the regenerationRate accordingly</p>
 * 
 * @author Dimitris Kremmydas
 *
 */
public class PGMLandscapeLoaderChapter2_p44 extends PGMLandscapeLoaderChapter2_p30 {
	
	@Override
	public Landscape load() {
		LandscapeChapter2_p30 ls  = (LandscapeChapter2_p30) super.load();
		
		GrowbackRule_p44 ga44 = (GrowbackRule_p44) ls.getGrowbackRule();
		GrowbackRule_p44.setSeasonSummer();
		ga44.setRegenerationRate(ls);
	
		
		return ls;
	}
	
	
}
