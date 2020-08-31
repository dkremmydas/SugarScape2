package repast.simphony.demos.sugarscape2.agents;

import org.apache.log4j.Level;

import repast.simphony.context.ContextEvent;
import repast.simphony.context.ContextEvent.EventType;
import repast.simphony.context.ContextListener;
import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.demos.sugarscape2.agents.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion.PollutionDiffusionAbility;
import repast.simphony.demos.sugarscape2.agents.rules.replacement.ReplacementAbility;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.demos.sugarscape2.utilities.PGMReader;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.valueLayer.GridValueLayer;
import simphony.util.messages.MessageCenter;

/**
 * <p>The space in which the SugarAgents act. This is the implementation for chapter 2</p>.
 * 
 * <p>This class implements the Singleton design pattern since only one object is allowed</p>
 *
 * @author Dimitris Kremmydas
 * @version 
 */

public class SugarSpace_ch2 extends DefaultContext<Object>  {

	private static SugarSpace_ch2 single_instance = null;

	private static boolean configured = false;


	protected SpaceResource sugar;

	protected GrowbackAbility growbackRule;

	protected ReplacementAbility replacementRule;

	protected PollutionDiffusionAbility diffusion_rule;

	protected Grid<Object> grid;



	public static SugarSpace_ch2 getInstance()  {

		if(SugarSpace_ch2.configured) {
			return SugarSpace_ch2.single_instance;
		} else {
			throw new RuntimeException("SugarSpace has not been configured yet.");
		}


	}

	public static SugarSpace_ch2 createInstance(String pgm_file,GrowbackAbility growbackRule,ReplacementAbility replacementRule,PollutionDiffusionAbility diffusionRule) {

		if(SugarSpace_ch2.configured) {
			SugarSpace_ch2.logMessage(SugarSpace_ch2.class, Level.INFO, "SugarSpace has already been configured. You can not re-configure it.");
		} else {
			SugarSpace_ch2 s = new SugarSpace_ch2(pgm_file,growbackRule,replacementRule,diffusionRule);
			SugarSpace_ch2.single_instance = s;
			SugarSpace_ch2.configured = true;
		}
		
		return SugarSpace_ch2.single_instance;
	}


	private SugarSpace_ch2(String pgm_file,GrowbackAbility growbackRule,ReplacementAbility replacementRule,PollutionDiffusionAbility diffusionRule) {

		super("sugarspace");

		this.growbackRule = growbackRule;
		this.replacementRule = replacementRule;
		this.diffusion_rule = diffusionRule;
		
		this.configureFromPGM(pgm_file);

		SugarSpace_ch2 me = this;
		super.addContextListener(new ContextListener<Object>() {

			@Override
			public void eventOccured(ContextEvent<Object> ev) {

				if(ev.getType().equals(EventType.AGENT_REMOVED) && ev.getTarget().getClass().equals(SugarAgent_ch2.class)) {
					replacementRule.replace( me, (SugarAgent_ch2) ev.getTarget());
				}


			}
		});
	}




	// The actual implementation of growback rule G, pg 182 (Appendix B).
	/**
	 * 
	 */
	@ScheduledMethod(start=2d,interval=5d)
	public void updateSugar() {	

		GridValueLayer sugarHoldingNew = this.growbackRule.growback(this);

		this.sugar.updateHolding(sugarHoldingNew);

	}


	@ScheduledMethod(start=4d,interval=5d)
	public void diffuse_pollution() {	

		GridValueLayer pollution_vl = (GridValueLayer) this.getValueLayer("pollution");

		Utility.updateGridValueLayer(pollution_vl, this.diffusion_rule.diffuse(this));
	}



	//********************************************************************************************************
	//Methods delegating sugar object

	/**
	 * 
	 * @return
	 */
	public SpaceResource getSugar() {
		return sugar;
	}

	/**
	 * 
	 * @param resource
	 * @return
	 */
	public GridValueLayer getResourceCapacity(String resource) {
		if(resource.equalsIgnoreCase("sugar")) {
			return Utility.cloneGridValueLayer(this.sugar.getCapacity());
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}
	}

	/**
	 * 
	 * @param resource
	 * @return
	 */
	public GridValueLayer getResourceHolding(String resource) {
		if(resource.equalsIgnoreCase("sugar")) {
			return Utility.cloneGridValueLayer(this.sugar.getHolding());
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}
	}


	/**
	 * 
	 * @param resource
	 * @param quant
	 */
	public void addResourceEverywhere(String resource,int quant) {

		if(resource.equalsIgnoreCase("sugar")) {
			sugar.addEverywhere(quant);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * 
	 * @param resource
	 * @param x
	 * @param y
	 * @param quant
	 * @return
	 */
	public int addResourceToXY(String resource,int x, int y, int quant) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.addToXY(x, y, quant);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * 
	 * @param resource
	 * @param gp
	 * @param quant
	 * @return
	 */
	public int addResourceToXY(String resource,GridPoint gp, int quant) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.addToXY(gp, quant);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * 
	 * @param resource
	 * @param x
	 * @param y
	 * @param amountRequested
	 * @return
	 */
	public int gatherResourceFromXY(String resource,int x, int y, int amountRequested) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.gatherFromXY(x, y, amountRequested);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * 
	 * @param resource
	 * @param x
	 * @param y
	 * @return
	 */
	public int availableResourceAtXY(String resource,int x, int y) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.availableAtXY(x, y);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}



	//********************************************************************************************************
	//Methods delegating grid object

	/**
	 * 
	 * @param a
	 * @return
	 */
	public GridPoint getSugarAgentLocation(SugarAgent_ch2 a) {
		return grid.getLocation(a);
	}

	/**
	 * 
	 * @param a
	 * @param x
	 * @param y
	 */
	public void moveAgentTo(SugarAgent_ch2 a,int x, int y) {
		grid.moveTo(a, x, y);
	}

	/**
	 * 
	 * @param a
	 * @param radius
	 * @param v
	 * @return
	 */
	public Iterable<GridPoint> getSugarAgentNeighboringPoints(SugarAgent_ch2 a, int radius, Utility.TypeOfVision v) {
		if(v==Utility.TypeOfVision.MOORE) {
			return NeighbourhoodFunctions.getMoorePoints(a.getCurrentPosition(), grid, radius);
		} else {
			return NeighbourhoodFunctions.getVonNeumanPoints(a.getCurrentPosition(), grid, radius);
		}		
	}

	/**
	 * 
	 * @param gp
	 * @param radius
	 * @param v
	 * @return
	 */
	public Iterable<GridPoint> getGridPointNeighboringPoints(GridPoint gp, int radius, Utility.TypeOfVision v) {
		if(v==Utility.TypeOfVision.MOORE) {
			return NeighbourhoodFunctions.getMoorePoints(gp, grid, radius);
		} else {
			return NeighbourhoodFunctions.getVonNeumanPoints(gp, grid, radius);
		}		
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Iterable<Object> getObjectsAt(int x, int y) {
		return grid.getObjectsAt(x,y);
	}

	/**
	 * 
	 * @return
	 */
	public int getWidth() {
		return grid.getDimensions().getWidth();
	}

	/**
	 * 
	 * @return
	 */
	public int getHeight() {
		return grid.getDimensions().getHeight();
	}

	/**
	 * 
	 * @param point1
	 * @param point2
	 * @return
	 */
	public double getDistance(GridPoint point1, GridPoint point2) {
		return grid.getDistance(point1, point2);
	}







	public void setGrowbackRule(GrowbackAbility growbackRule) {
		this.growbackRule = growbackRule;
	}


	public void setReplacementRule(ReplacementAbility replacementRule) {
		this.replacementRule = replacementRule;
	}


	public void setDiffusion_rule(PollutionDiffusionAbility diffusion_rule) {
		this.diffusion_rule = diffusion_rule;
	}


	/**
	 * 
	 * @param pgm_file
	 */
	private void configureFromPGM (String pgm_file) {

		//1.1 read the pgm file
		PGMReader pgmreader = new PGMReader( pgm_file); //"./data/sugarspace.pgm"


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
		this.sugar = new SpaceResource(landscape_sugarCapacity, landscape_sugarLevel);

		this.addValueLayer(sugar.capacity);
		this.addValueLayer(sugar.holding);
		this.addValueLayer(landscape_pollution);

		this.grid = GridFactoryFinder.createGridFactory(null)
				.createGrid("sugarscape", this, new GridBuilderParameters<Object>(
						new WrapAroundBorders(), new RandomGridAdder<Object>(), true, 
						(int)sugar.capacity.getDimensions().getWidth(),
						(int)sugar.capacity.getDimensions().getHeight()));


	}


	/**
	 * Write log messages to log4j
	 * 
	 * @param clazz
	 * @param level
	 * @param message
	 */
	public static void logMessage(Class<?> clazz, Level level,Object message) {
		MessageCenter mc = MessageCenter.getMessageCenter(clazz);
		mc.fireMessageEvent(level, message, null);
	}



	/**
	 * The set of agent's properties (fixed or variables) that are related to a product
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	protected class SpaceResource {

		GridValueLayer capacity;

		GridValueLayer holding;

		public SpaceResource(GridValueLayer capacity, GridValueLayer holding) {
			super();
			this.capacity = capacity;
			this.holding = holding;
		}


		public int gatherFromXY(int x,int y,int amountRequested) {

			if(amountRequested<0) {amountRequested=0;}

			int amountGathered;

			int amountAvailable = (int) this.holding.get(x,y);

			if(amountAvailable>amountRequested) {
				amountGathered = amountRequested;
			} else {
				amountGathered = amountAvailable;
			}

			this.holding.set(amountAvailable-amountGathered, x,y);

			return amountGathered;
		}


		public int availableAtXY(int x, int y) {

			return( (int) this.holding.get(x,y) );


		}

		/**
		 * Add a quantity of resource to a x-y GridPoint
		 * The sum of the existing and the added quantity cannot go over the capacity of the point 
		 * 
		 * @param x the x-location
		 * @param y the y-location
		 * @param quant the quantity to add
		 * @return the actual quantity added
		 */
		public int addToXY(int x,int y,int quant) {

			int added;

			if( (this.holding.get(x,y)+quant) > this.capacity.get(x,y) ) {
				added = (int) (this.capacity.get(x,y)-this.holding.get(x,y));
			} else {
				added = quant;
			}


			this.holding.set(this.holding.get(x,y)+added,x, y );

			return added;

		}

		/**
		 * Add a quantity of resource to a x-y GridPoint
		 * The sum of the existing and the added quantity cannot go over the capacity of the point
		 * 
		 * @param gp The {@link GridPoint} where the resource is added
		 * @param quant the quantity to add
		 * @return the actual quantity added
		 */
		public int addToXY(GridPoint gp, int quant) {
			return this.addToXY(gp.getX(), gp.getY(), quant);
		}


		/**
		 * Add in every {@link GridPoint} of the Resource the specified quantity
		 * @param quant the quantity of resource to add
		 */
		public void addEverywhere(int quant) {
			int x,y;
			int x_max= (int) this.holding.getDimensions().getWidth();
			int y_max=(int) this.holding.getDimensions().getHeight();

			for( x=0; x< x_max;x++) {
				for( y=0; y< y_max;y++) {
					this.addToXY(x, y, quant);
				}
			}
		}


		/**
		 * Replace the holding {@link GridValueLayer} with the provided one. 
		 * The replacement is done in a 'by value' way
		 * @param newValueLayer
		 */
		public void updateHolding(GridValueLayer newValueLayer) {
			int x,y;

			for( x=0; x< this.holding.getDimensions().getWidth();x++) {
				for( y=0; y< this.holding.getDimensions().getHeight();y++) {
					this.holding.set(newValueLayer.get(x,y), x,y);
				}
			}
		}


		public GridValueLayer getCapacity() {
			return capacity;
		}


		public GridValueLayer getHolding() {
			return holding;
		}






	}





}