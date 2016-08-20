package gr.kremmydas.sugarscape.agents.rules.metabolism;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class MetabolismRule_p30 implements MetabolismAbility {

	public MetabolismRule_p30() {
		super();
	}

	/**
	 * Metabolize sugar according to agent's metabolism needs
	 */
	@Override
	public Integer metabolize(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		//get current agent point
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		
		int existQuant = (int)o.getMyLandscape().getSugarGridProperties().getCurrentQuantity().get(gp.getX(),gp.getY());
		return existQuant;
	}



}
