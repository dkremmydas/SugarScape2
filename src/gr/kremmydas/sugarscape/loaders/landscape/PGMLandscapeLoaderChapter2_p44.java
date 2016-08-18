package gr.kremmydas.sugarscape.loaders.landscape;



import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackAbility;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackRule_p44;
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
public class PGMLandscapeLoaderChapter2_p44 extends PGMLandscapeLoaderChapter2_p30 {
	
	@Override
	public Landscape load() {
		LandscapeChapter2_p30 ls  = new LandscapeChapter2_p30(pgmreader.getxSize(), pgmreader.getySize());
		GrowbackAbility ga;
		try {
			ga = (GrowbackAbility) Class.forName(growbackRoot+RunEnvironment.getInstance().getParameters().getString("growbackRuleClass")).newInstance();
			ls.setGrowbackRule(ga);
			
			GrowbackRule_p44 ga44 = (GrowbackRule_p44) ga;
			GrowbackRule_p44.setSeasonSummer();
			ga44.setRegenerationRate(ls);
			
			
		} catch (InstantiationException e) {
			e.printStackTrace();
			RunEnvironment.getInstance().endRun();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			RunEnvironment.getInstance().endRun();
		}
		
		super.setInitialSugar(ls);
		ls.getGrid().setAdder(new RandomGridAdder<Agent>());
		
		return ls;
	}
	
	
}
