package repast.simphony.demos.sugarscape2.landscape.old;

import repast.simphony.context.RepastElement;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridAdder;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridDimensions;
import repast.simphony.space.grid.RandomGridAdder;
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
	protected DefaultGrid<SugarAgent_ch2> grid;
	
	

	/**
	 * Costructor. Default values are assigned by calling {@link #init(width, height)}
	 */
	public Landscape() {
		super();		
	}
	
	/**
	 * Initialize any values from here
	 * 
	 * @param x the width of the landscape grid
	 * @param y the height of the landscape grid
	 */
	public void init(int x, int y) {
		this.x = x;
		this.y = y;
		GridFactory gf = GridFactoryFinder.createGridFactory(null);
		grid = (DefaultGrid<SugarAgent_ch2>) gf.createGrid("sugarscapeGrid", 
				SimulationContext.getInstance(), 
				new GridBuilderParameters <SugarAgent_ch2> (new WrapAroundBorders (),new RandomGridAdder<SugarAgent_ch2>(),true , x, y)
			);
	}
	
	
	public GridDimensions getDimensions() {
		return this.grid.getDimensions();
	}


	public DefaultGrid<SugarAgent_ch2> getGrid() {
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
	
	class GridXYAdder implements GridAdder<SugarAgent_ch2> {

		@Override
		public void add(Grid<SugarAgent_ch2> destination, SugarAgent_ch2 object) {
			destination.moveTo(object, object.getIni_x(),object.getIni_y());
		}	
	} //end inner class
	
	
} //end outer class
