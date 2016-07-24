package gr.kremmydas.sugarscape.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import gr.kremmydas.sugarscape.rules.consumption.ConsumeAbility;
import gr.kremmydas.sugarscape.rules.movement.MoveAbility;
import gr.kremmydas.sugarscape.rules.vision.VisionAbility;
import repast.simphony.context.RepastElement;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;


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
	private ConsumeAbility cr;
	
	private MoveAbility mr;
	
	private VisionAbility vr;

	
	// State variables
	/**
	 * Position on the grid
	 */	
	private ProductAgentProperties sugarProperties;
	
	private ProductAgentProperties pepperProperties;
	
	private AgentProperties properties;

	public Agent(ConsumeAbility cr, MoveAbility mr,
			VisionAbility vr, int x, int y, ProductAgentProperties sugarProperties,
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
	
	public ConsumeAbility getConsumptionRule() {
		return cr;
	}

	public MoveAbility getMovementRule() {
		return mr;
	}

	public VisionAbility getVisionRule() {
		return vr;
	}

	@ScheduledMethod(start=1d,interval=2d)
	public void move() {
		DefaultGrid<Agent> g = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp;
		gp=this.mr.move();
		g.moveTo(this, gp.getX(),gp.getY());
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
