package repast.simphony.demos.sugarscape2.agents;

import repast.simphony.context.DefaultContext;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.demos.sugarscape2.agents.behaviors.SpaceBehavior_ch2;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.GridDimensions;
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
	
	protected SpaceBehavior_ch2 behavior;
	
	protected Grid<Object> grid;
	
	protected int regeneration_rate;
		

	public SugarSpace_ch2(SpaceResource sugar,SpaceBehavior_ch2 behavior, int regeneration_rate) {
		super("sugarspace");
		
		this.sugar = sugar;
		this.addValueLayer(sugar.capacity);
		this.addValueLayer(sugar.holding);
		
		this.behavior = behavior;
		
		this.regeneration_rate = regeneration_rate;
		
		this.grid = GridFactoryFinder.createGridFactory(null)
				.createGrid("sugarscape", this, new GridBuilderParameters<Object>(
						new WrapAroundBorders(), new RandomGridAdder<Object>(), true, 
						(int)sugar.capacity.getDimensions().getWidth(),
						(int)sugar.capacity.getDimensions().getHeight()));

		
	}

	// The actual implementation of growback rule G, pg 182 (Appendix B).
	@ScheduledMethod(start=2d,interval=5d)
	public void updateSugar() {	
		
		GridValueLayer sugarHoldingNew = this.behavior.growback(this,regeneration_rate);
		
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