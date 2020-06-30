package repast.simphony.demos.sugarscape2.agents.rules.old.gathering;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;
import repast.simphony.demos.sugarscape2.landscape.old.SimulationContext;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class GatheringRule_p30 implements GatheringAbility {

	public GatheringRule_p30() {}

	/**
	 * Gather all existent sugar in the site
	 */
	@Override
	public Integer gather(SugarAgent owner) {
		SugarAgent_ch2p30 o = (SugarAgent_ch2p30) owner;
		
		//get current agent point
		DefaultGrid<SugarAgent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		
		int existQuant = (int)o.getMyLandscape().getSugarGridProperties().getCurrentQuantity().get(gp.getX(),gp.getY());
		return existQuant;
	}

}
