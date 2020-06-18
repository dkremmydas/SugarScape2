package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2p30;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p41;
import repast.simphony.query.space.grid.VNQuery;
import repast.simphony.space.graph.Network;
import repast.simphony.space.graph.RepastEdge;
import repast.simphony.space.grid.GridPoint;

/**
 * Implements the M rule of page 39: "The first agent now executes M,
 * moves to a new site, and the builds a list of its von Neuwmann
 * neighbors, which maintains until its next move"
 * 
 * @author Dimitris Kremmydas
 *
 */
public class MovementRule_p41 extends MovementRule_p30 {

	public MovementRule_p41() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public GridPoint move(SugarAgent owner) {
		//get some definitions
		SugarAgent_ch2p30 owner30 = (SugarAgent_ch2p30)owner;		
		Network<SugarAgent> n = ((LandscapeChapter2_p41)owner30.getMyLandscape()).getNet();
		
		//calculate the move
		GridPoint gp = super.move(owner);
		
		//delete old edges
		Iterable<RepastEdge<SugarAgent>> dai = n.getOutEdges(owner);
		Set<RepastEdge<SugarAgent>> str = new HashSet<>();
		for(RepastEdge<SugarAgent> da: dai) {
			str.add(da);
		}
		for(RepastEdge<SugarAgent> da: str) {
			n.removeEdge(da);
		}
		
		//build new edges (for the point where the agent will move)
		VNQuery<SugarAgent> q = new VNQuery<SugarAgent>(SimulationContext.getInstance().getLandscape().getGrid(), owner, 1,1);
		Iterable<SugarAgent> neighs = q.query();
		for(SugarAgent a: neighs ) {
			if(! a.equals(owner)) n.addEdge(owner, a);
		}
		
		//make the move
		return gp;
	}


}
