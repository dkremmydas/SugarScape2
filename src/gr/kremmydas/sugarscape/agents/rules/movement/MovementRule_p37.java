package gr.kremmydas.sugarscape.agents.rules.movement;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p37;
import repast.simphony.space.grid.GridPoint;

/**
 * The movement rule applied in the book
 * 
 * @author Dimitris Kremmydas
 *
 */
public class MovementRule_p37 extends MovementRule_p30 {

	public MovementRule_p37() {
		super();
	}

	/**
	 * We follow the same movement rule of p30 run-configuration
	 * and only increase age by 1 unit
	 */
	@Override
	public GridPoint move(Agent owner) {
		
		AgentChapter2_p37 o = (AgentChapter2_p37) owner;
		o.setCurrentAge(o.getCurrentAge()+1);
		
		return super.move(owner);
		
	}

}
