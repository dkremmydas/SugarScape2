package gr.kremmydas.sugarscape;

import gr.kremmydas.sugarscape.rules.ConsumptionRule;
import gr.kremmydas.sugarscape.rules.MovementRule;
import gr.kremmydas.sugarscape.rules.VisionRule;


/**
 * 
 * An sugarscape agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public class Agent {

	private ConsumptionRule cr;
	
	private MovementRule mr;
	
	private VisionRule vr;
	
	/**
	 * Position on the grid
	 */
	private int x,y;
}
