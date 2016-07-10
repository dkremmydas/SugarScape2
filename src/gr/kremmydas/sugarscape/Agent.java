package gr.kremmydas.sugarscape;

import gr.kremmydas.sugarscape.rules.consumption.AbstractConsumptionRule;
import gr.kremmydas.sugarscape.rules.movement.AbstractMovementRule;
import gr.kremmydas.sugarscape.rules.vision.AbstractVisionRule;
import repast.simphony.engine.schedule.ScheduledMethod;


/**
 * 
 * An Sugarscape agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public class Agent {

	// Rules
	private AbstractConsumptionRule cr;
	
	private AbstractMovementRule mr;
	
	private AbstractVisionRule vr;

	
	// State variables
	/**
	 * Position on the grid
	 */
	private int x,y;
	
	private int sugarStored;
	
	private int pepperStored;

	public Agent(AbstractConsumptionRule cr, AbstractMovementRule mr,
			AbstractVisionRule vr, int x, int y, int sugarStored,
			int pepperStored) {
		super();
		this.cr = cr;
		this.mr = mr;
		this.vr = vr;
		this.x = x;
		this.y = y;
		this.sugarStored = sugarStored;
		this.pepperStored = pepperStored;
	}
	
	
	@ScheduledMethod(start=1d,interval=2d)
	public void move() {
		//SimulationContext.getInstance().getProjection("sugarscapeGrid").
	}
	
	@ScheduledMethod(start=2d,interval=2d)
	public void consume() {
		//SimulationContext.getInstance().getProjection("sugarscapeGrid").
	}
	


}
