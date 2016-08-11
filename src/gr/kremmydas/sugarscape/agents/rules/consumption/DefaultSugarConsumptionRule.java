package gr.kremmydas.sugarscape.agents.rules.consumption;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
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
	public Integer consume(AgentChapter2_p30 owner) {
		//get current agent point
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		
		int existQuant = (int)owner.getMyLandscape().getSugarGridProperties().getCurrentQuantity().get(gp.getX(),gp.getY());
		return existQuant;
	}



}
