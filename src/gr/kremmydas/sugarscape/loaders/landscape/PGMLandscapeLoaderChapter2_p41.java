package gr.kremmydas.sugarscape.loaders.landscape;



import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p41;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackAbility;
import gr.kremmydas.sugarscape.products.ProductGridProperties;
import gr.kremmydas.sugarscape.utilities.PGMReader;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.RandomGridAdder;

/**
 * <p>{@link LandscapeLoader} implementation. Reads the capacity
 * of the cells of the sugarscape grid from a "sugarscape.pgm" file that is located 
 * in ./data folder.</p>
 * <p>Change the <i>growbackRuleClass</i> parameter for selecting {@link GrowbackAbility}
 * rule. Also set <i>regenerationRate</i> parameter for setting the value of <i>a</i> (i.e. the
 * regeneration rate).</p>
 * 
 * @author Dimitris Kremmydas
 *
 */
public class PGMLandscapeLoaderChapter2_p41 implements LandscapeLoader {
	
	private String inputFile = "./data/sugarspace.pgm";
	private PGMReader pgmreader;
	private String growbackRoot = "gr.kremmydas.sugarscape.landscape.rules.growback.";

	public PGMLandscapeLoaderChapter2_p41() {
		pgmreader = new PGMReader(inputFile);
	}
	


	@Override
	public Landscape load() {
		LandscapeChapter2_p41 ls  = new LandscapeChapter2_p41(pgmreader.getxSize(), pgmreader.getySize());
		GrowbackAbility ga;
		try {
			ga = (GrowbackAbility) Class.forName(growbackRoot+RunEnvironment.getInstance().getParameters().getString("growbackRuleClass")).newInstance();
			ls.setGrowbackRule(ga);
		} catch (InstantiationException e) {
			e.printStackTrace();
			RunEnvironment.getInstance().endRun();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			RunEnvironment.getInstance().endRun();
		}
		
		
		ProductGridProperties pgp = ls.getSugarGridProperties();
		for(int x=0;x<ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<ls.getDimensions().getHeight(); y++) {
				int s1 = (int)pgmreader.getMatrix()[x][y];
				int s2 = (int)pgmreader.getMatrix()[x][y];
				pgp.getCurrentQuantity().set((double)s1, x,y);
				pgp.getCapacity().set((double)s2, x,y);
				pgp.getRegenerationRate().set(
						(double)RunEnvironment.getInstance().getParameters().getInteger("regenerationRate"), 
						x,y);
			}
		}
		ls.getGrid().setAdder(new RandomGridAdder<Agent>());
		
		return ls;
	}
	
	
	

}
