package repast.simphony.demos.sugarscape2.agents.builders.loaders;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.space.grid.Grid;

public class SugarscapeAgentsLoaderChapter2_p42 extends
		SugarscapeAgentsLoaderChapter2_p30 {

	public SugarscapeAgentsLoaderChapter2_p42() {}

	@Override
	public void addAgents(SimulationContext sc) {
		super.addAgents(sc);
		
		int square_size = (int) Math.ceil(Math.sqrt(sc.size()));
		Grid<SugarAgent> g = sc.getLandscape().getGrid();
		
		int x=1, y=1;
		Iterable<SugarAgent> ai = SimulationContext.getInstance().getAgentLayer(SugarAgent.class);
		for(SugarAgent a : ai) {
			g.moveTo(a, x, y);
			x++;
			if(x>square_size) {x=1; y++;}
		}
	}
	
	

}
