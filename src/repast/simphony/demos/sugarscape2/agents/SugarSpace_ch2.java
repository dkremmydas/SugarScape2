package repast.simphony.demos.sugarscape2.agents;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.demos.sugarscape2.agents.abilities.space.GrowbackAbility;
import repast.simphony.demos.sugarscape2.agents.behaviors.SpaceBehavior_ch2;
import repast.simphony.demos.sugarscape2.utilities.PGMReader;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The space in which the SugarAgents act. 
 *
 * @author Dimitris Kremmydas
 * @version 
 */

public class SugarSpace_ch2 extends DefaultContext<Object>  {

	protected SpaceResource sugar;

	protected GrowbackAbility growbackRule;

	protected Grid<Object> grid;




	public SugarSpace_ch2(SpaceResource sugar,GrowbackAbility growbackRule) {
		super("sugarspace");

		this.sugar = sugar;
		this.addValueLayer(sugar.capacity);
		this.addValueLayer(sugar.holding);

		this.growbackRule = growbackRule;

		this.grid = GridFactoryFinder.createGridFactory(null)
				.createGrid("sugarscape", this, new GridBuilderParameters<Object>(
						new WrapAroundBorders(), new RandomGridAdder<Object>(), true, 
						(int)sugar.capacity.getDimensions().getWidth(),
						(int)sugar.capacity.getDimensions().getHeight()));


	}


	/**
	 * 
	 * @param json
	 * @return
	 */
	public static SugarSpace_ch2 fromJSON (String json) {

		try {
			JSONObject obj = (JSONObject) (new JSONParser()).parse(json);

			int rate = (int) ((JSONObject)obj.get("growback")).get("rate");

			//1.1 read the pgm file
			PGMReader pgmreader = new PGMReader((String) obj.get("pgm_file"));


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

			GrowbackAbility g = new SpaceBehavior_ch2(rate);

			return new SugarSpace_ch2(sugar,g);	

		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("JSON string does not have the proper information");
		}

	}


	/**
	 * 
	 * @return
	 */
	public static SugarSpace_ch2 fromRunenvParameters () {

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
		int sugar_regeneration_rate = RunEnvironment.getInstance().getParameters().getInteger("regenerationRate");
		
		SpaceBehavior_ch2 b = new SpaceBehavior_ch2(sugar_regeneration_rate);
		

		return new SugarSpace_ch2(sugar,b);

	}



	// The actual implementation of growback rule G, pg 182 (Appendix B).
	@ScheduledMethod(start=2d,interval=5d)
	public void updateSugar() {	

		GridValueLayer sugarHoldingNew = this.growbackRule.growback(this);

		this.sugar.updateHolding(sugarHoldingNew);

	}




	public SpaceResource getSugar() {
		return sugar;
	}

	/**
	 * 
	 * @return
	 */
	public Grid<Object> getGrid() {
		return grid;
	}








}