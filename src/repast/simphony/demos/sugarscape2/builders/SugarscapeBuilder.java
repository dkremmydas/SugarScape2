package repast.simphony.demos.sugarscape2.builders;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class SugarscapeBuilder implements ContextBuilder<Object>{
	
	
	
	private int chapter;
	private String variant; 
	
	private Context<Object> initialContext;
	private Context<Object> finalContext;
	

	public SugarscapeBuilder() {
		
		//TODO[check if parameters return valid values]
		
		this.chapter = RunEnvironment.getInstance().getParameters().getInteger("Chapter");
		this.variant = RunEnvironment.getInstance().getParameters().getString("Variant");
		 
	}






	@Override
	public Context<Object> build(Context<Object> context) {
		
		this.initialContext = context;
		
		if(this.chapter==2) {
			if (this.variant.equalsIgnoreCase("p30")) {
				constructChapter2_p30();				
			}
		}
					
		
		return(this.finalContext);
	}
	
	
	
	private boolean constructChapter2_p30() {
		
		
		//1. create the agents
		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");
		RandomHelper.createUniform();
		
		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
		int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");


		SugarAgent_ch2[] agents = new SugarAgent_ch2[n];
		
		for(int i=0;i<n;i++) {
			
			agents[i] = new SugarAgent_ch2.Builder(Utility.getRandomString(10))
			.withVisionLevel(RandomHelper.nextIntFromTo(1, maxVision))
			.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
			.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
			.build();

		}
		
		
		//2. create the landscape of sugarscape
		
		
		
		//3. add the agents to the sugarscape
		
		return true;
		
	}
	

}
