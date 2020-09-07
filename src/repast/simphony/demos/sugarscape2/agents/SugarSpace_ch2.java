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
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedulableAction;
import repast.simphony.engine.schedule.ScheduleParameters;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * <p>The space in which the SugarAgents act. This is the implementation for chapter 2. </p>
 * 
 * 
 * <p><b>Sugarspace contains Resources</b><br />
 * In the implementation of chapters 2 and 3, Sugarscape contains sugar and in chapter 4 it contains spice too. 
 * Thus we have introduced the more general notion of a "resource". 
 * We are providing methods so that {@link SugarAgent_ch2}s can interact with the resources of the Sugarspace.
 * The details of the implementation are encapsulated in the class. Currently, we have the inner protected class {@link SpaceResource}.
 * </p>
 * 
 * 
 * <p><b>Sugarspace is a Grid</b><br/>Sugarspace is a <a href="https://en.wikipedia.org/wiki/Torus">torus</a> grid. 
 * Each grid cell contains resources and can host up to
 * one {@link SugarAgent_ch2}. We are providing several methods with the grid* prefix that allow the interaction with the grid. 
 * We are encapsulating the implementation details in a {@link Grid} object from the Repast API.</p>
 * 
 * 
 * <p><b>Sugarspace has behavior</b><br/>As described in the book, sugarspace has its own behavior TODO[describe the three behaviors].
 * Annotations ...</p>
 *   
 * 
 * <p><b>Instantiation through the Singleton pattern</b><br/>
 * Only one object of this class is expected to be instantiated during the simulation. 
 * For this, we use a variant of the <a href="https://en.wikipedia.org/wiki/Singleton_pattern">Singleton</a> design pattern. <br />
 * The {@link #SugarSpace_ch2(String, GrowbackAbility, ReplacementAbility, PollutionDiffusionAbility) constructor} 
 * of the class is private and thus objects cannot be instantiated normally. 
 * In order to create an object you have to call the static 
 * method {@link #createInstance(String, GrowbackAbility, ReplacementAbility, PollutionDiffusionAbility) createInstance}. 
 * In order to access the single object of this class you have to call the static method 
 * {@link #getInstance() getInstance()}. More details on the implementation of the Singleton pattern can be found on the 
 * comments of those methods</p>
 * 
 * 
 * <p><b>Responsibilities:</b><br/>
 * 		The SugarSpace object is responsible for:
 *  	<ul>
 *  		<li>
 *  			<b>Add or remove {@link SugarAgent_ch2} from the space.</b> This class  
 *  				extends {@link DefaultContext} (class of Repast Simphony) and thus can deal with this
 *  				through the relevant inherited methods. 
 *  		</li>
 *  		<li>
 *  			<b>Handle the resources of Sugarscape.</b> The relevant methods start
 *  				with the resource* prefix. The first parameter is a {@link String}, denoting the
 *  				name of the resource the method call is about.
 *  		</li>
 *  		<li>
 *  			<b>Keep and apply the rules of the SugarSpace.</b> In chapter 2, there are three rules:
 *  			<ul>
 *  				<li>Growback: This scheduled with Repast {@link ScheduledMethod} annotation on {@link #applyGrowback() applyGrowback} method</li>
 *  				<li>Replacement of agents: This is programmed in the private constructor through the Listener pattern inherited from {@link DefaultContext}</li>
 *  				<li>Diffusion of pollution: This scheduled with Repast {@link ScheduledMethod} annotation on {@link #applyDiffuse_pollution() applyDiffuse_pollution} method</li>
 *  			</ul>
 *  		</li>
 *  	</ul> 
 * </p>
 * 
 * 
 * 
 * <p></p>
 *
 * @author Dimitris Kremmydas
 * @version %I%
 */

public class SugarSpace_ch2 extends DefaultContext<Object>  {



	protected SpaceResource sugar;

	protected GrowbackAbility growbackRule;

	protected ReplacementAbility replacementRule;

	protected PollutionDiffusionAbility diffusion_rule;

	protected Grid<Object> grid;




	// Instantiating the object
	//****************************************************************************************************************************************************


	


	/**
	 * <p>This conventional constructor is private, so that the Singleton pattern is enforced. 
	 * Creation of objects is allowed only by using the {@link #createInstance(String, GrowbackAbility, ReplacementAbility, PollutionDiffusionAbility) createInstance}
	 * method. </p>
	 * 
	 * <p>TODO: addContextLitener. The replacement behavior of the Sugarspace is injected here. We are using the 
	 * <a href="https://refactoring.guru/design-patterns/observer/java/example">Observer pattern</a> of 
	 * the parent {@link DefaultContext} class. We are adding a simple object of the {@link ContextListener} interface, that it is
	 * notified every time an event has occurred and in case this event is related to an agent removal, it triggers
	 * the {@link ReplacementAbility} rule.</p>
	 * 
	 * @param pgm_file The full path to the <a href="http://netpbm.sourceforge.net/doc/pgm.html">PGM file</a> that gives the sugar capacity allocation in space. 
	 * @param growbackRule The rule to increase the available sugar at each grid cell every clock tick. It must follow the {@link GrowbackAbility} interface.
	 * @param replacementRule The rule to replace agent when they die. It must follow the {@link ReplacementAbility} interface.
	 * @param diffusionRule The rule to diffuse pollution. It must follow the {@link PollutionDiffusionAbility} interface.
	 * @return The {@link SugarSpace_ch2 instance created}
	 */
	public SugarSpace_ch2(String pgm_file,GrowbackAbility growbackRule,ReplacementAbility replacementRule,PollutionDiffusionAbility diffusionRule) {

		super("sugarspace");

		this.growbackRule = growbackRule;
		this.replacementRule = replacementRule;
		this.diffusion_rule = diffusionRule;

		this.configureGridFromPGM(pgm_file);

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




	// Application of Rules of  the Sugarspace
	//****************************************************************************************************************************************************

	@ScheduledMethod(start=10d,interval=10d,priority = -1000d)
	public void diagnostics() {
		Utility.logMessage( Level.DEBUG, 
				"Number of scheduled methods: " + RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	}

	/**
	 * The growback rule. Every second tick, the sugar resource in each grid cell is updated.
	 */
	@ScheduledMethod(start=6d,interval=20d)
	public void applyGrowback() {	

		GridValueLayer sugarHoldingNew = this.growbackRule.growback(this);

		this.sugar.updateHolding(sugarHoldingNew);

	}


	/**
	 * The pollution diffusion behavior. Each 4th tick, the pollution of each grid cell is updated.
	 */
	@ScheduledMethod(start=8d,interval=5d)
	public void applyDiffuse_pollution() {	

		GridValueLayer pollution_vl = (GridValueLayer) this.getValueLayer("pollution");

		Utility.updateGridValueLayer(pollution_vl, this.diffusion_rule.diffuse(this));
	}



	public void addSugarAgent(SugarAgent_ch2 a) {
		
		this.add(a);

		double cur_tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		ISchedulableAction ac = RunEnvironment.getInstance().getCurrentSchedule().schedule(
				ScheduleParameters.createRepeating(cur_tick+1+1, 10d), 
				a, 
				"step"
				);

		Utility.logMessage( Level.DEBUG, "Agent added in SugarSpace_ch2: " + a + 
				"\n\t'Step' action scheduled to take place at tick " + ac.getNextTime());
		
	}
	
	




	//********************************************************************************************************
	//Methods related to Resources


	/**
	 * <p>Get the capacity of the resource throughout the Sugarspace. It returns a {@link GridValueLayer} with the resource.
	 * Any write operations (e.g. change the capacity) on the returned {@link GridValueLayer}, will not have any effect
	 * on the Sugarspace, since the returned object is a clone of the original one TODO[explain better].</p>
	 * 
	 * <p> We assume that the capacity of grid cells of the Sugarspace cannot be changed during the simulation.</p>
	 * 
	 * @param resource The name of the resource to get the capacity for.  E.g "sugar"
	 * @return A {@link GridValueLayer} with the capacity of the resource. Operations on this object do not have any effect on Sugarspace.
	 * @throws RunTimeException if the resource does not exist
	 */
	public GridValueLayer resourceGetCapacity(String resource) {
		if(resource.equalsIgnoreCase("sugar")) {
			return Utility.cloneGridValueLayer(this.sugar.getCapacity());
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}
	}

	/**
	 * <p>Get the current holding of a resource throughout the Sugarspace. It returns a {@link GridValueLayer} with the resource.
	 * Any write operations (e.g. change the capacity) on the returned {@link GridValueLayer}, will not have any effect
	 * on the Sugarspace, since the returned object is a clone of the original one TODO[explain better].</p>
	 * 
	 * <p>In order to add or remove resource from the Sugarspace's holding, use one of the following methods of this class:
	 * <ul>
	 * <li>{@link #resourceAddEverywhere(String, int) #resourceAddEverywhere}</li>
	 * <li>{@link #resourceAddToXY(String, GridPoint, int) #resourceAddToXY}</li>
	 * <li>{@link #resourceGatherFromXY(String, int, int, int) #resourceGatherFromXY}</li>
	 * </ul></p>
	 * 
	 * @param resource The name of the resource to get the holding for.  E.g "sugar"
	 * @return A {@link GridValueLayer} with the holding of the resource. Operations on the returned object do not have any effect on Sugarspace.
	 * @throws RunTimeException if the resource does not exist
	 */
	public GridValueLayer resourceGetHolding(String resource) {
		if(resource.equalsIgnoreCase("sugar")) {
			return Utility.cloneGridValueLayer(this.sugar.getHolding());
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}
	}


	/**
	 * Add in every grid cell of the Sugarspace, the specified quantity of the resource
	 * 
	 * @param resource A {@link String} with the name of the resource. E.g "sugar"
	 * @param quant An int with the quantity to add everywhere
	 * @throws RuntimeException if the resource name does not exist
	 */
	public void resourceAddEverywhere(String resource,int quant) {

		if(resource.equalsIgnoreCase("sugar")) {
			sugar.addEverywhere(quant);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * Adds a quantity of a resource in a X-Y coordinate of the space. It takes care so as, if
	 * the quantity added to the X-Y cell plus the existing quantity exceeds the capacity of
	 * the cell, only the difference [capacity]-[existing quantity] is added.
	 * 
	 * @param resource A {@link String} with the name of the resource. E.g "sugar"
	 * @param x The x-coordinate
	 * @param y The y-coordinate
	 * @param quant An int with the quantity to add
	 * @return The actual quantity added. If the quantity added to a grid cell goes over its capacity, 
	 * 			the actual quantity added will be lower than the quantity added so that it always hold
	 * 			[existing quantity]+[quantity added] <= [capacity] 
	 * @throws RunTimeException if the resource does not exist
	 */
	public int resourceAddToXY(String resource,int x, int y, int quant) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.addToXY(x, y, quant);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * Adds a quantity of a resource in a X-Y coordinate of the space. It takes care so as, if
	 * the quantity added to the X-Y cell plus the existing quantity exceeds the capacity of
	 * the cell, only the difference [capacity]-[existing quantity] is added.
	 * 
	 * @param resource A {@link String} with the name of the resource. E.g "sugar"
	 * @param gp
	 * @param quant
	 * @return
	 * @throws RunTimeException if the resource does not exist
	 */
	public int resourceAddToXY(String resource,GridPoint gp, int quant) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.addToXY(gp, quant);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * Removes the specified quantity of resource from X-Y coordinate of Sugarspace. If the
	 * specified quantity does not exist in the cell, it gather the available one. It returns the
	 * actual quantity gathered.
	 * 
	 * @param resource A {@link String} with the name of the resource. E.g "sugar"
	 * @param x
	 * @param y
	 * @param amountRequested
	 * @return
	 * @throws RunTimeException if the resource does not exist
	 */
	public int resourceGatherFromXY(String resource,int x, int y, int amountRequested) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.gatherFromXY(x, y, amountRequested);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * Queries and returns the available quantity of the resource available at X-Y point 
	 * of the Sugarspace.
	 * 
	 * @param resource A {@link String} with the name of the resource. E.g "sugar"
	 * @param x
	 * @param y
	 * @return
	 * @throws RunTimeException if the resource does not exist
	 */
	public int resourceGetHoldingAtXY(String resource,int x, int y) {

		if(resource.equalsIgnoreCase("sugar")) {
			return sugar.availableAtXY(x, y);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}



	//********************************************************************************************************
	//Methods delegating grid object

	/**
	 * Returns the location of a {@link SugarAgent_ch2} in the Sugarspace 
	 * 
	 * @param a the {@link SugarAgent_ch2} to find the location for
	 * @return {@link GridPoint} of the location of agent
	 */
	public GridPoint gridGetAgentLocation(SugarAgent_ch2 a) {
		return grid.getLocation(a);
	}

	/**
	 * Moves a {@link SugarAgent_ch2} to a new location in the SugarSpace
	 * 
	 * @param a a the {@link SugarAgent_ch2} to move
	 * @param x int of the x coordinate of the new location
	 * @param y int of the y coordinate of the new location
	 * @return true if the move was successful, otherwise false.
	 */
	public boolean gridMoveAgentTo(SugarAgent_ch2 a,int x, int y) {
		return grid.moveTo(a, x, y);
	}

	/**
	 * Gets the neighboring {@link GridPoint}s of a {@link SugarAgent_ch2} up to a certain radius. 
	 * The definition of 'neighboring point' (MOORE or von-NEUMAN) must be provided.
	 * 
	 * @param a The {@link SugarAgent_ch2} object
	 * @param radius An int with the radius up to which to looks for neighboring {@link GridPoint}s
	 * @param typeOfVision A {@link Utility.TypeOfVision} that defines whether adjacent points are MOORE or vonNEUMAN
	 * @return An {@link Iterable} with all neighboring {@link GridPoint}s 
	 */
	public Iterable<GridPoint> gridGetNeighboringPoints(SugarAgent_ch2 a, int radius, Utility.TypeOfVision typeOfVision) {
		return this.gridGetNeighboringPoints(a.getCurrentPosition(), radius, typeOfVision);		
	}



	/**
	 * Gets the neighboring {@link GridPoint}s of another {@link GridPoint} up to a certain radius. 
	 * The definition of 'neighboring point' (MOORE or von-NEUMAN) must be provided.
	 * 
	 * @param gp The {@link GridPoint} for which to calculate the neighboring {@link GridPoint}s
	 * @param radius An int with the radius up to which to find the neighboring {@link GridPoint}s
	 * @param typeOfVision A {@link Utility.TypeOfVision} that defines whether adjacent points are MOORE or vonNEUMAN
	 * @return An {@link Iterable} with all neighboring {@link GridPoint}s 
	 */
	public Iterable<GridPoint> gridGetNeighboringPoints(GridPoint gp, int radius, Utility.TypeOfVision typeOfVision) {
		if(typeOfVision==Utility.TypeOfVision.MOORE) {
			return NeighbourhoodFunctions.getMoorePoints(gp, grid, radius);
		} else {
			return NeighbourhoodFunctions.getVonNeumanPoints(gp, grid, radius);
		}		
	}

	/**
	 * Gets the {@link SugarAgent_ch2} that is located in a certain {@link GridPoint} at space. If no agent is 
	 * present it returns a null
	 * 
	 * @param x int of the x-coordinate of the requested {@link GridPoint}
	 * @param y int of the y-coordinate of the requested {@link GridPoint}
	 * @return the {@link SugarAgent_ch2} that is located in the requested location. Null if no agent is present there.
	 */
	public SugarAgent_ch2 gridGetSugarAgentAt(int x, int y) {

		Iterable<Object> objects = grid.getObjectsAt(x,y);
		SugarAgent_ch2 a = null;

		for (Object o : objects) {
			try {
				a = (SugarAgent_ch2) o;
				break;
			}
			catch (ClassCastException e) {}
		}	

		return a;

	}

	/**
	 * 
	 * @return the width of the Sugarspace
	 */
	public int gridGetWidth() {
		return grid.getDimensions().getWidth();
	}

	/**
	 * 
	 * @return the height of the Sugarspace
	 */
	public int gridGetHeight() {
		return grid.getDimensions().getHeight();
	}

	/**
	 * Computes the euclidian distance between two points in Sugarspace. It takes into account that
	 * topology of Sugarspace is a toroid.
	 * 
	 * @param point1 The first {@link GridPoint}
	 * @param point2 The second {@link GridPoint}
	 * @return the euclidian distance between the points
	 */
	public double gridGetDistance(GridPoint point1, GridPoint point2) {
		return grid.getDistance(point1, point2);
	}



	/**
	 * We configure the grid of the Sugarspace based on a PGM file. The grid's width and height are set and
	 * the capacity of sugar in each grid cell is loaded.
	 * 
	 * @param pgm_file
	 */
	private void configureGridFromPGM (String pgm_file) {

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
				.createGrid("sugarscape", 
						this, 
						GridBuilderParameters.singleOccupancy2DTorus(
								new RandomGridAdder<Object>(), 
								(int)sugar.capacity.getDimensions().getWidth(), 
								(int)sugar.capacity.getDimensions().getHeight()
								)
						);


	}



	/**
	 * We encapsulate the details of a Sugarspace's resource inside this <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html">inner class</a>. 
	 * 
	 * This class is kept inside the {@link SugarSpace_ch2} class on purpose, so that the visibility of the class is kept at minimum.
	 * The only thing the users of the {@link SugarSpace_ch2} object should know is how to use the resource* family of methods. The
	 * handling of the resources inside the class is a internal concern of the class and should not be exposed outside. In this way, 
	 * if in the future the implementation of a resource changes, the contract with the user will not break (he can still keep
	 * using the *resource methods).
	 * 
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
		 //TODO return A GridValueLayer with the actual quantities added (since we cannot exceed capacity)
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