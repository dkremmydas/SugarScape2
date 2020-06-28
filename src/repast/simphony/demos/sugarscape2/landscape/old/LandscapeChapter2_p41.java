package repast.simphony.demos.sugarscape2.landscape.old;

import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.space.graph.Network;

public class LandscapeChapter2_p41 extends LandscapeChapter2_p30 {
	
	Network<SugarAgent> net ;
	
	public LandscapeChapter2_p41() {
		super();
	}

	@Override
	public void init(int x, int y) {
		super.init(x, y);
		NetworkBuilder<SugarAgent> netb = new NetworkBuilder<SugarAgent>("neigbours", SimulationContext.getInstance(), true);
		net = netb.buildNetwork();
	}



	public Network<SugarAgent> getNet() {
		return net;
	}
	
	

}
