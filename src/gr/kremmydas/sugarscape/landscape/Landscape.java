package gr.kremmydas.sugarscape.landscape;

import gr.kremmydas.sugarscape.Agent;
import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.products.ProductGridProperties;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridDimensions;

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

	public Landscape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		grid = new DefaultGrid<>("sugarscapeGrid", x, y);
		SimulationContext.getInstance().addProjection(grid);
		
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
	
	
	
	
}
