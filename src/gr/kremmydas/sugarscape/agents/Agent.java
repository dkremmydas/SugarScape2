package gr.kremmydas.sugarscape.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.rules.consumption.ConsumeAbility;
import gr.kremmydas.sugarscape.agents.rules.movement.MoveAbility;
import gr.kremmydas.sugarscape.agents.rules.vision.VisionAbility;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
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
	
	private AgentGeneticCharacteristics properties;

	public Agent() {};
	
	public Agent(ConsumeAbility cr, MoveAbility mr,
			VisionAbility vr, ProductAgentProperties sugarProperties,
			ProductAgentProperties pepperProperties, AgentGeneticCharacteristics properties) {
		this();
		this.setProperties(sugarProperties,pepperProperties,properties);
		this.setRules(cr, mr, vr);
	}
	
	public void setRules(ConsumeAbility cr, MoveAbility mr,	VisionAbility vr) {
		this.cr = cr;
		this.mr = mr;
		this.vr = vr;
	}
	
	public void setProperties (ProductAgentProperties sugarProperties,
			ProductAgentProperties pepperProperties, AgentGeneticCharacteristics properties) {
		this.sugarProperties = sugarProperties;
		this.pepperProperties = pepperProperties;
		this.properties = properties;
		
		//position in the grid
		SimulationContext.getInstance().add(this);
		//SimulationContext.getInstance().getLandscape().getGrid().moveTo(this, properties.getIni_x(),properties.getIni_y());
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
		gp=this.mr.move(this);
		g.moveTo(this, gp.getX(),gp.getY());
	}
	
	@ScheduledMethod(start=2d,interval=2d)
	public void consume() {
		int toConsume = this.cr.consume(this);
		
		this.sugarProperties.setHolding(
				this.sugarProperties.getHolding() + toConsume
			);

		SimulationContext.getInstance().getLandscape().removeSugar(this, toConsume);
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

	public AgentGeneticCharacteristics getProperties() {
		return properties;
	}

	public void setConsumptionRule(ConsumeAbility cr) {
		this.cr = cr;
	}

	public void setMovementRule(MoveAbility mr) {
		this.mr = mr;
	}

	public void setVisionRule(VisionAbility vr) {
		this.vr = vr;
	}
	
	

}
