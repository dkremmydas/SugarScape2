package repast.simphony.demos.sugarscape2.landscape.old.loaders;



import repast.simphony.demos.sugarscape2.agents.Landscape;
import repast.simphony.demos.sugarscape2.landscape.old.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.landscape.old.rules.growback.GrowbackRule_p44;

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
