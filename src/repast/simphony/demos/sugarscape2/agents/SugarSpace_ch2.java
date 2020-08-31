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
 * <p>The space in which the SugarAgents act. This is the implementation for chapter 2. </p>
 * 
 * <p><b>Space is a Grid</b><br/></p>
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
 * 		The space object is responsible for:
 *  	<ul>
 *  		<li>
 *  			<b>Add or remove {@link SugarAgent_ch2} from the space.</b> This class  
 *  				extends {@link DefaultContext} (class of Repast Simphony) and thus can deal with this
 *  				through the relevant inherited methods. 
 *  		</li>
 *  		<li>
 *  			<b>Handle the resources of Sugarscape.</b> We have generalized the notion of ¨sugar¨ 
 *  				to that of ¨resource¨. In the implementation of chapters 2 and 3, sugarspace contains only 
 *  				sugar but in chapter 4 it contains spice too. Both are resources. The relevant methods start
 *  				with the resource* prefix.
 *  		</li>
 *  		<li>
 *  			Apply the following steps in each turn: 
 *  			<ul>
 *  				<li>Growback sugar in the sugarscape</li>
 *  				<li>Replacement of agents</li>
 *  				<li>Diffusion of pollution</li>
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


	
	
	// Instantiating the object
	//****************************************************************************************************************************************************
	

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
	//****************************************************************************************************************************************************
	/**
	 * 
	 */
	@ScheduledMethod(start=2d,interval=5d)
	public void applyGrowback() {	

		GridValueLayer sugarHoldingNew = this.growbackRule.growback(this);

		this.sugar.updateHolding(sugarHoldingNew);

	}


	@ScheduledMethod(start=4d,interval=5d)
	public void applyDiffuse_pollution() {	

		GridValueLayer pollution_vl = (GridValueLayer) this.getValueLayer("pollution");

		Utility.updateGridValueLayer(pollution_vl, this.diffusion_rule.diffuse(this));
	}



	//********************************************************************************************************
	//Methods delegating sugar object


	/**
	 * 
	 * @param resource
	 * @return
	 */
	public GridValueLayer resourceGetCapacity(String resource) {
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
	public GridValueLayer resourceGetHolding(String resource) {
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
	public void resourceAddEverywhere(String resource,int quant) {

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
	public int resourceAddToXY(String resource,int x, int y, int quant) {

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
	public int resourceAddToXY(String resource,GridPoint gp, int quant) {

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
	public int resourceGatherFromXY(String resource,int x, int y, int amountRequested) {

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