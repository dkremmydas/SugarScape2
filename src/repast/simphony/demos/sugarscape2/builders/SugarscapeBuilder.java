package repast.simphony.demos.sugarscape2.builders;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.utilities.PGMReader;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The concern of this class is to create the Sugarscape {@link}Context
 * 
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class SugarscapeBuilder implements ContextBuilder<Object>{



	private int chapter;
	private String variant; 

	private Context<Object> initialContext;


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


		return(this.initialContext);
	}



	private boolean constructChapter2_p30() {


		//1. create the sugarscape landscape
		
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


		//1.5 add the above ValueLayers to the context
		this.initialContext.addValueLayer(landscape_sugarCapacity);
		this.initialContext.addValueLayer(landscape_sugarLevel);
		this.initialContext.addValueLayer(landscape_pollution);
		



		//2. create the agents
		
		//2.1 create the 'agents' context and the Gird projection of 'sugargrid'
		DefaultContext<SugarAgent_ch2> agentsContext = new DefaultContext<SugarAgent_ch2>("agents");
		
		Grid<SugarAgent_ch2> sugargrid = GridFactoryFinder.createGridFactory(null)
				.createGrid("sugarscape", agentsContext, new GridBuilderParameters<SugarAgent_ch2>(
						new WrapAroundBorders(), new RandomGridAdder<SugarAgent_ch2>(), true, pgmreader.getxSize(),pgmreader.getySize()));


		//2.2 read user parameters on the properties of the simulation (e.g. number of agents, maximum vision, etc.)
		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");
		RandomHelper.createUniform();

		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
		int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");


		//2.3 create the agents and add them to the context and to the Grid projection
		for(int i=0;i<n;i++) {

			SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10))
					.withVisionLevel(RandomHelper.nextIntFromTo(1, maxVision))
					.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
					.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
					.build();

			agentsContext.add(agent);
			sugargrid.getAdder().add(sugargrid, agent);

		}

		
		//2.4 add the 'agents' context to the top-level context
		//		when adding the context,  the Gird projection of 'sugargrid' is added automatically, since 
		//		they are contained inside the 'agent' context
		//agentsContext.addProjection(sugargrid);
		this.initialContext.addSubContext(agentsContext);



		//3. if everything is ok, return true
		return true;

	}


}
