package repast.simphony.demos.sugarscape2.landscape.loaders;



import repast.simphony.demos.sugarscape2.landscape.Landscape;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p50;
import repast.simphony.demos.sugarscape2.landscape.rules.pollution.PollutionDiffusionAbility;
import repast.simphony.engine.environment.RunEnvironment;


public class PGMLandscapeLoaderChapter2_p50 extends PGMLandscapeLoaderChapter2_p30 {
	
	String pollutionDiffusionRuleString;
	int pollutionDiffusionPeriod;
	
	
	public PGMLandscapeLoaderChapter2_p50() {
		super();
		pollutionDiffusionRuleString = RunEnvironment.getInstance().getParameters().getString("pollutionDiffusionRuleClass"); 
		pollutionDiffusionPeriod = RunEnvironment.getInstance().getParameters().getInteger("pollutionDiffusionPeriod");
	}
	
	/**
	 * Set the {@link PollutionDiffusionAbility} rule for the {@link LandscapeChapter2_p50}
	 */
	@Override
	public Landscape load() {
		LandscapeChapter2_p50 ls  = (LandscapeChapter2_p50) super.load();
		
		ls.setPollutionDiffusionPeriod(pollutionDiffusionPeriod);
		
		PollutionDiffusionAbility pda;
		try {
			pda = (PollutionDiffusionAbility) Class.forName(pollutionDiffusionRuleString).newInstance();
			ls.setPollutionDiffusionRule(pda);
			
			return ls;
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
			String err = "Could not initialize PollutionDiffusionAbility class. Possibly "
					+ "wrong definition of class in parameters.xml";
			RunEnvironment.getInstance().endRun();
			throw new RuntimeException(err);
		}
		
	}
	
	
}
