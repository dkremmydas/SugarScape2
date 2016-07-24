package gr.kremmydas.sugarscape.rules.consumption;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.rules.AbstractRule;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class DefaultSugarConsumptionRule extends AbstractRule implements ConsumeAbility {

	public DefaultSugarConsumptionRule(Agent owner) {
		super(owner);
	}

	/**
	 * Consume all existent sugar
	 */
	@Override
	public Integer consume() {
		//get current agent point
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(this.owner);
		
		int existQuant = (int)SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCurrentQuantity().get(gp.getX(),gp.getY());
		return existQuant;
	}

}
