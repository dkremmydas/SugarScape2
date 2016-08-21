package gr.kremmydas.sugarscape.loaders.landscape;



import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p30;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackAbility;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackRule_p44;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.RandomGridAdder;

/**
 * <p>Set the season to be summer and initialize the regenerationRate accordingly</p>
 * 
 * @author Dimitris Kremmydas
 *
 */
public class PGMLandscapeLoaderChapter2_p44 extends PGMLandscapeLoaderChapter2_p30 {
	
	@Override
	public Landscape load() {
		LandscapeChapter2_p30 ls  = new LandscapeChapter2_p30(pgmreader.getxSize(), pgmreader.getySize());
		
		GrowbackAbility ga; GrowbackRule_p44 ga44;
		try {
			ga = (GrowbackAbility) Class.forName(RunEnvironment.getInstance().getParameters().getString("growbackRuleClass")).newInstance();
			ls.setGrowbackRule(ga);
			
			ga44 = (GrowbackRule_p44) ga;
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
		
		super.setInitialSugarQuantity(ls);
		ls.getGrid().setAdder(new RandomGridAdder<Agent>());
		
		return ls;
	}
	
	
}
