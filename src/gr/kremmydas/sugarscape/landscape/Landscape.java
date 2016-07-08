package gr.kremmydas.sugarscape.landscape;

import gr.kremmydas.sugarscape.Agent;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.valueLayer.GridValueLayer;

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
	 * The concentration of sugar
	 */
	GridValueLayer sugar;
	
	/**
	 * The concentration of pepper
	 */
	GridValueLayer pepper;

	public Landscape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		grid = new DefaultGrid<>("sugarscapeGrid", x, y);
		
	}
	
	
	
	
	
}
