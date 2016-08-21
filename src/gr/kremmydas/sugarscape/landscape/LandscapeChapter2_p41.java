package gr.kremmydas.sugarscape.landscape;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.space.graph.Network;

public class LandscapeChapter2_p41 extends LandscapeChapter2_p30 {
	
	Network<Agent> net ;
	
	public LandscapeChapter2_p41() {
		super();
	}

	@Override
	public void init(int x, int y) {
		super.init(x, y);
		NetworkBuilder<Agent> netb = new NetworkBuilder<Agent>("neigbours", SimulationContext.getInstance(), true);
		net = netb.buildNetwork();
	}



	public Network<Agent> getNet() {
		return net;
	}
	
	

}
