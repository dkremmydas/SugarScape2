package gr.kremmydas.sugarscape.rules.vision;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.rules.AbstractRule;

import java.util.Set;

import repast.simphony.space.grid.GridPoint;

public abstract class AbstractVisionRule extends AbstractRule {


	public AbstractVisionRule(Agent owner) {
		super(owner);
	}

	/**
	 * Decision of the she set of points that are currently visible
	 * 
	 * @return
	 */
	abstract Set<GridPoint> getVisionedPoints();
	
}
