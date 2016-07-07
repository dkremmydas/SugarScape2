package gr.kremmydas.sugarscape.rules.consumption;

import gr.kremmydas.sugarscape.Agent;

import java.util.Map;

import repast.simphony.space.grid.GridPoint;

public interface ConsumptionRule {

	public Map<GridPoint,Integer> consume(Agent a);
	
}
