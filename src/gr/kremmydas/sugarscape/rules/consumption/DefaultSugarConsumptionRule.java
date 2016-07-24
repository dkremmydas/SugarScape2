package gr.kremmydas.sugarscape.rules.consumption;

import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;

public class DefaultSugarConsumptionRule extends AbstractConsumptionRule {

	public DefaultSugarConsumptionRule(Agent owner) {
		super(owner);
	}

	/**
	 * Consume all existent sugar
	 */
	@Override
	Integer consume() {
		//get current agent point
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(this.owner);
		
		int existQuant = (int)SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCurrentQuantity().get(gp.getX(),gp.getY());
		return existQuant;
	}

}
