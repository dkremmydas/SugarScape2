package gr.kremmydas.sugarscape.agents.rules.gathering;

import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;

public class GatheringRule_p30 implements GatheringAbility {

	public GatheringRule_p30() {}

	/**
	 * Gather all existent sugar in the site
	 */
	@Override
	public Integer gather(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		//get current agent point
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		
		int existQuant = (int)o.getMyLandscape().getSugarGridProperties().getCurrentQuantity().get(gp.getX(),gp.getY());
		return existQuant;
	}

}
