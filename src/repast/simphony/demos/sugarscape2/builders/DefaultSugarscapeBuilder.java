package repast.simphony.demos.sugarscape2.builders;

import javax.management.RuntimeErrorException;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SpaceResource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.behaviors.SpaceBehavior_ch2;
import repast.simphony.demos.sugarscape2.utilities.PGMReader;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.random.RandomHelper;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The concern of this class is to create the Sugarscape {@link}Context
 * 
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class DefaultSugarscapeBuilder implements ContextBuilder<Object>{


	private int chapter;
	private String variant; 



	@Override
	public Context<Object> build(Context<Object> context) {

		
		this.chapter = RunEnvironment.getInstance().getParameters().getInteger("Chapter");
		this.variant = RunEnvironment.getInstance().getParameters().getString("Variant");

		if(this.chapter==2) {
			if (this.variant.equalsIgnoreCase("p30")) {
				return(constructChapter2_p30());				
			}
		}


		throw new RuntimeErrorException(null, "The Chapter and Variant parameters provided, are not yet implemented" );
	}



	private SugarSpace_ch2 constructChapter2_p30() {
		
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();

				
		//1.1 read the pgm file
		PGMReader pgmreader = new PGMReader( "./data/sugarspace.pgm");
				

		//1.2 create GridValueLayer of 'sugar capacity'
		GridValueLayer landscape_sugarCapacity = new  GridValueLayer("sugar capacity", true, pgmreader.getxSize(),pgmreader.getySize());

		for(int x =0 ; x < pgmreader.getxSize(); ++x)
			for(int y =0; y<pgmreader.getySize();++y)
				landscape_sugarCapacity.set(
						pgmreader.getMatrix()[x][y], 
						x,y);



		//1.3 create GridValueLayer of 'sugar level'
		GridValueLayer landscape_sugarLevel = new  GridValueLayer("sugar level", true, pgmreader.getxSize(),pgmreader.getySize());

		for(int x =0 ; x < pgmreader.getxSize(); ++x)
			for(int y =0; y<pgmreader.getySize();++y)
				landscape_sugarLevel.set(
						pgmreader.getMatrix()[x][y], 
						x,y);


		//1.4 create GridValueLayer of 'pollution'
		GridValueLayer landscape_pollution = new  GridValueLayer("pollution", true, pgmreader.getxSize(),pgmreader.getySize());

		for(int x =0 ; x < pgmreader.getxSize(); ++x)
			for(int y =0; y<pgmreader.getySize();++y)
				landscape_pollution.set(
						0, 
						x,y);

		
		//1.5 create the sugar SpaceResource
		SpaceResource sugar = new SpaceResource(landscape_sugarCapacity, landscape_sugarLevel);
		
		//1.6 instatianate the SugarSpace behavior
		String  spaceBehavior_classString = RunEnvironment.getInstance().getParameters().getString("SpaceBehavior");
		
		SpaceBehavior_ch2 b;
		try {
			b = (SpaceBehavior_ch2) Class.forName(spaceBehavior_classString).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}
		
		
		
		//1.7 create the SugarSpace
		int sugar_regeneration_rate = RunEnvironment.getInstance().getParameters().getInteger("regenerationRate");
		
		SugarSpace_ch2 agentsContext = new SugarSpace_ch2(sugar,b,sugar_regeneration_rate);


		
		//2. create the agents
		
		//2.1 read user parameters on the properties of the simulation (e.g. number of agents, maximum vision, etc.)
		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");
		RandomHelper.createUniform();

		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
		int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
		String agentBehavior_ch2_classString = RunEnvironment.getInstance().getParameters().getString("AgentBehavior");
		

		//2.2 create the agents and add them to the context and to the Grid projection
				
		for(int i=0;i<n;i++) {

			SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10),agentsContext)
					.withSugarVisionLevel(RandomHelper.nextIntFromTo(1, maxVision))
					.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
					.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
					.withBehaviorClass(agentBehavior_ch2_classString)
					.build();

			agentsContext.add(agent);			
			schedule.schedule(agent); //TODO why do we have to add the annotated methods to the schedule manually?

		}

		

		//3. if everything is ok, return true
		return agentsContext;

	}


}
