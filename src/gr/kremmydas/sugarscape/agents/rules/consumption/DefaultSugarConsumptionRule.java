package gr.kremmydas.sugarscape.agents.rules.consumption;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class DefaultSugarConsumptionRule implements ConsumeAbility {

	public DefaultSugarConsumptionRule() {
		super();
	}

	/**
	 * Consume all existent sugar
	 */
	@Override
	public Integer consume(Agent owner) {
		//get current agent point
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		
		int existQuant = (int)SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCurrentQuantity().get(gp.getX(),gp.getY());
		return existQuant;
	}



}
