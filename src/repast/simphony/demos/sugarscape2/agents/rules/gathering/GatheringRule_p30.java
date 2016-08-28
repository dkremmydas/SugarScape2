package repast.simphony.demos.sugarscape2.agents.rules.gathering;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.Agent;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p30;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

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
