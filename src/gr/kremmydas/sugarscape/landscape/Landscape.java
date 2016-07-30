package gr.kremmydas.sugarscape.landscape;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackAbility;
import gr.kremmydas.sugarscape.products.ProductGridProperties;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridAdder;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridDimensions;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.valueLayer.ValueLayer;

/**
 * 
 * The 2-D lattice and any accompanying information (e.g. sugar/pepper on x-y)
 * 
 * @author Dimitris Kremmydas
 *
 */
public class Landscape {
	
	/**
	 * The size of the grid
	 */
	int x,y;
	
	/**
	 * The grid of the landscape
	 */
	DefaultGrid<Agent> grid;
	
	/**
	 * The properties of sugar
	 */
	ProductGridProperties sugarGridProperties;
	
	/**
	 * The properties of pepper
	 */
	ProductGridProperties pepperGridProperties;
	
	/**
	 * The growback rule
	 */
	GrowbackAbility growbackRule;

	public Landscape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		GridFactory gf = GridFactoryFinder.createGridFactory(null);
		grid = (DefaultGrid<Agent>) gf.createGrid("sugarscapeGrid", 
				SimulationContext.getInstance(), 
				new GridBuilderParameters <Agent> (new WrapAroundBorders (),new GridXYAdder(),true , x, y)
			);
		 
		//SimulationContext.getInstance().addProjection(grid);
		
		this.sugarGridProperties = new ProductGridProperties(x, y, "sugar");
		this.pepperGridProperties = new ProductGridProperties(x, y, "pepper");
	}
	
	
	public GridDimensions getDimensions() {
		return this.grid.getDimensions();
	}


	public ProductGridProperties getSugarGridProperties() {
		return sugarGridProperties;
	}


	public ProductGridProperties getPepperGridProperties() {
		return pepperGridProperties;
	}


	public DefaultGrid<Agent> getGrid() {
		return grid;
	}


	public GrowbackAbility getGrowbackRule() {
		return growbackRule;
	}


	public void setGrowbackRule(GrowbackAbility growbackRule) {
		this.growbackRule = growbackRule;
	}
	
	/**
	 * The sugarscape growsback <br/>
	 * Before I just set sugarGridProperties.getCurrentQuantity() = {new value layer} but it did not work.
	 * I had to copy value-by-value the contents of the second valuelayer to the first
	 */
	@ScheduledMethod(start=3d,interval=2d)
	public void growback() {
		System.out.println("Before Growback: " + this.sugarGridProperties.getQuantityDescriptiveStats());
		ValueLayer newvl = this.growbackRule.growback(this);

		for(int i=0;i<sugarGridProperties.getCurrentQuantity().getDimensions().getWidth();i++) {
			for(int j=0;j<sugarGridProperties.getCurrentQuantity().getDimensions().getHeight();j++) {
				sugarGridProperties.getCurrentQuantity().set(newvl.get(i,j), i,j);
			}
		}
		
		//this.sugarGridProperties.setCurrentQuantity();
		System.out.println("After Growback: " + this.sugarGridProperties.getQuantityDescriptiveStats());
	}
	
	public void removeSugar(Agent a, int q) {
		GridPoint gp = this.grid.getLocation(a);
		int nq = (int) this.sugarGridProperties.getCurrentQuantity().get(gp.getX(),gp.getY())-q;
		this.sugarGridProperties.getCurrentQuantity().set(nq, gp.getX(),gp.getY());
	}
	
	class GridXYAdder implements GridAdder<Agent> {

		@Override
		public void add(Grid<Agent> destination, Agent object) {
			destination.moveTo(object, object.getProperties().getIni_x(),object.getProperties().getIni_y());
		}
		
	}
}
