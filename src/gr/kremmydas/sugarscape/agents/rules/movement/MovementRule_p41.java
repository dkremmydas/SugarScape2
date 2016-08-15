package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p41;

import java.util.HashSet;
import java.util.Set;

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
	public GridPoint move(Agent owner) {
		//get some definitions
		AgentChapter2_p30 owner30 = (AgentChapter2_p30)owner;		
		Network<Agent> n = ((LandscapeChapter2_p41)owner30.getMyLandscape()).getNet();
		
		//calculate the move
		GridPoint gp = super.move(owner);
		
		//delete old edges
		Iterable<RepastEdge<Agent>> dai = n.getOutEdges(owner);
		Set<RepastEdge<Agent>> str = new HashSet<>();
		for(RepastEdge<Agent> da: dai) {
			str.add(da);
		}
		for(RepastEdge<Agent> da: str) {
			n.removeEdge(da);
		}
		
		//build new edges (for the point where the agent will move)
		VNQuery<Agent> q = new VNQuery<Agent>(SimulationContext.getInstance().getLandscape().getGrid(), owner, 1,1);
		Iterable<Agent> neighs = q.query();
		for(Agent a: neighs ) {
			if(! a.equals(owner)) n.addEdge(owner, a);
		}
		
		//make the move
		return gp;
	}


}
