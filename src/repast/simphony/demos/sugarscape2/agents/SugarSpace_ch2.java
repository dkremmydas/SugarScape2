package repast.simphony.demos.sugarscape2.agents;

import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.demos.sugarscape2.agents.behaviors.SpaceBehavior_ch2;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.RandomGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The space in which the SugarAgents act. In particular this space implements
 * the growback rule G from Growing Artificial Societies.
 *
 * The source has been annotated so see that for further details.
 *
 * @author Eric Tatara
 * @author Nick Collier
 * @version 
 */

public class SugarSpace_ch2 extends DefaultContext<Object>  {
	
	protected SpaceResource sugar;
	
	protected SpaceBehavior_ch2 behavior;
	
	protected Grid<Object> grid;
		

	public SugarSpace_ch2(SpaceResource sugar,SpaceBehavior_ch2 behavior ) {
		super("sugarspace");
		
		this.sugar = sugar;
		this.addValueLayer(sugar.capacity);
		this.addValueLayer(sugar.holding);
		
		this.behavior = behavior;
		
		this.grid = GridFactoryFinder.createGridFactory(null)
				.createGrid("sugarscape", this, new GridBuilderParameters<Object>(
						new WrapAroundBorders(), new RandomGridAdder<Object>(), true, 
						(int)sugar.capacity.getDimensions().getWidth(),
						(int)sugar.capacity.getDimensions().getHeight()));

		
	}

	// The actual implementation of growback rule G, pg 182 (Appendix B).
	@ScheduledMethod(start=0,interval=1)
	public void updateSugar() {	
		
		GridValueLayer sugarHoldingNew = this.behavior.growback(this);
		
		this.sugar.updateHolding(sugarHoldingNew);
		
	}
	

	/**
	 * Takes all the sugar at this coordinate, leaving no sugar
	 * @param x
	 * @param y
	 * @return
	 */
	public int takeAllSugarAt(int x, int y) {
		
		int taken = this.takeSomeSugarAt(x, y, this.sugar.availableAtXY(x, y));
		
		return taken;
	}
	
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param quant
	 * @return the quantity of sugar taken
	 */
	public int takeSomeSugarAt(int x, int y, int quant) {
		
		int taken = this.sugar.gatherFromXY(x, y, quant);
		
		return taken;
	}

	/**
	 * 
	 * @return
	 */
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