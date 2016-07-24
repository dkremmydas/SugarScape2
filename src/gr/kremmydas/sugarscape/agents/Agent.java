package gr.kremmydas.sugarscape.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import gr.kremmydas.sugarscape.rules.consumption.AbstractConsumptionRule;
import gr.kremmydas.sugarscape.rules.movement.AbstractMovementRule;
import gr.kremmydas.sugarscape.rules.vision.AbstractVisionRule;
import repast.simphony.context.RepastElement;
import repast.simphony.engine.schedule.ScheduledMethod;


/**
 * 
 * An Sugarscape agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public class Agent implements RepastElement {
	
	private int id;

	// Rules
	private AbstractConsumptionRule cr;
	
	private AbstractMovementRule mr;
	
	private AbstractVisionRule vr;

	
	// State variables
	/**
	 * Position on the grid
	 */	
	private ProductAgentProperties sugarProperties;
	
	private ProductAgentProperties pepperProperties;
	
	private AgentProperties properties;

	public Agent(AbstractConsumptionRule cr, AbstractMovementRule mr,
			AbstractVisionRule vr, int x, int y, ProductAgentProperties sugarProperties,
			ProductAgentProperties pepperProperties, AgentProperties properties) {
		super();
		this.cr = cr;
		this.mr = mr;
		this.vr = vr;
		this.sugarProperties = sugarProperties;
		this.pepperProperties = pepperProperties;
		this.properties = properties;
		
		//position in the grid
		SimulationContext.getInstance().getLandscape().getGrid().moveTo(this, x,y);
	}	
	
	public AbstractConsumptionRule getConsumptionRule() {
		return cr;
	}

	public AbstractMovementRule getMovementRule() {
		return mr;
	}

	public AbstractVisionRule getVisionRule() {
		return vr;
	}

	@ScheduledMethod(start=1d,interval=2d)
	public void move() {
		//SimulationContext.getInstance().getProjection("sugarscapeGrid").
	}
	
	@ScheduledMethod(start=2d,interval=2d)
	public void consume() {
		//SimulationContext.getInstance().getProjection("sugarscapeGrid").
	}

	@Override
	public void setId(Object id) {
		this.id = Integer.valueOf(id.toString());
	}

	@Override
	public Object getId() {
		return this.id;
	}

	public ProductAgentProperties getSugarProperties() {
		return sugarProperties;
	}

	public ProductAgentProperties getPepperProperties() {
		return pepperProperties;
	}

	public AgentProperties getProperties() {
		return properties;
	}
	
	

}
