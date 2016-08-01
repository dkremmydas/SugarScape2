package gr.kremmydas.sugarscape.landscape;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import repast.simphony.context.RepastElement;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridAdder;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridDimensions;
import repast.simphony.space.grid.WrapAroundBorders;

/**
 * 
 * The 2-D lattice and any accompanying information (e.g. sugar/pepper on x-y)
 * 
 * @author Dimitris Kremmydas
 *
 */
public class Landscape implements RepastElement {
	
	protected int id = 100;
	
	/**
	 * The size of the grid
	 */
	protected int x,y;
	
	/**
	 * The grid of the landscape
	 */
	protected DefaultGrid<Agent> grid;
	
	

	public Landscape(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		GridFactory gf = GridFactoryFinder.createGridFactory(null);
		grid = (DefaultGrid<Agent>) gf.createGrid("sugarscapeGrid", 
				SimulationContext.getInstance(), 
				new GridBuilderParameters <Agent> (new WrapAroundBorders (),new GridXYAdder(),true , x, y)
			);
	}
	
	
	public GridDimensions getDimensions() {
		return this.grid.getDimensions();
	}


	public DefaultGrid<Agent> getGrid() {
		return grid;
	}

	
	@Override
	public void setId(Object id) {
		//We do nothing since only one Landscape is avaiable and id equals always to 0		
	}


	@Override
	public Object getId() {
		return this.id;
	}
	
	class GridXYAdder implements GridAdder<Agent> {

		@Override
		public void add(Grid<Agent> destination, Agent object) {
			destination.moveTo(object, object.getIni_x(),object.getIni_y());
		}	
	} //end inner class
	
	
} //end outer class
