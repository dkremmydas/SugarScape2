package gr.kremmydas.sugarscape.loaders.landscape;



import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
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
public class PGMLandscapeLoaderChapter2_p30 implements LandscapeLoader {
	
	protected String inputFile = "./data/sugarspace.pgm";
	protected PGMReader pgmreader;
	protected String growbackRoot = "gr.kremmydas.sugarscape.landscape.rules.growback.";

	public PGMLandscapeLoaderChapter2_p30() {
		pgmreader = new PGMReader(inputFile);
	}
	


	@Override
	public Landscape load() {
		LandscapeChapter2_p30 ls  = new LandscapeChapter2_p30(pgmreader.getxSize(), pgmreader.getySize());
		
		this.setGrowback(ls);
		this.setInitialSugarQuantity(ls);
		this.setInitialSugarRate(ls);
		ls.getGrid().setAdder(new RandomGridAdder<Agent>());
		
		return ls;
	}
	
	/**
	 * Defines the {@link PGMLandscapeLoaderChapter2_p30} growback rule and set it
	 * @param ls
	 */
	protected void setGrowback(LandscapeChapter2_p30 ls) {
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
	}
	
	protected void setInitialSugarQuantity(LandscapeChapter2_p30 ls) {
		ProductGridProperties pgp = ls.getSugarGridProperties();
		for(int x=0;x<ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<ls.getDimensions().getHeight(); y++) {
				int s1 = (int)pgmreader.getMatrix()[x][y];
				int s2 = (int)pgmreader.getMatrix()[x][y];
				pgp.getCurrentQuantity().set((double)s1, x,y);
				pgp.getCapacity().set((double)s2, x,y);
			}
		}
	}
	
	protected void setInitialSugarRate(LandscapeChapter2_p30 ls) {
		ProductGridProperties pgp = ls.getSugarGridProperties();
		for(int x=0;x<ls.getDimensions().getWidth(); x++) {
			for(int y=0;y<ls.getDimensions().getHeight(); y++) {
				pgp.getRegenerationRate().set(
						(double)RunEnvironment.getInstance().getParameters().getInteger("regenerationRate"), 
						x,y);
			}
		}
		
	}
	
	
}
