package gr.kremmydas.sugarscape.agents;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.rules.consumption.ConsumeAbility;
import gr.kremmydas.sugarscape.agents.rules.death.DeathAbility;
import gr.kremmydas.sugarscape.agents.rules.movement.MoveAbility;
import gr.kremmydas.sugarscape.agents.rules.vision.VisionAbility;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2;
import gr.kremmydas.sugarscape.products.ProductAgentProperties;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class AgentChapter2 extends Agent {
	
	/**
	 * Properties related to sugar
	 */
	private ProductAgentProperties sugarProperties;
	
	/**
	 * Level of vision
	 */
	int visionLevel;
	
	/**
	 * Reference to the parent landscape
	 */
	LandscapeChapter2 myLandscape; 
	
	
	// Rules
	private ConsumeAbility consumptionRule;
	
	private MoveAbility movementRule;
	
	private VisionAbility visionRule;
	
	private DeathAbility deathRule;
		
		

	public AgentChapter2() {
		super();
	}


	@ScheduledMethod(start=1d,interval=2d)
	public void move() {
		DefaultGrid<Agent> g = SimulationContext.getInstance().getLandscape().getGrid();
		GridPoint gp;
		gp=this.movementRule.move(this);
		g.moveTo(this, gp.getX(),gp.getY());
	}
	
	@ScheduledMethod(start=2d,interval=2d)
	public void consume() {
		int toConsume = this.consumptionRule.consume(this);
		
		this.sugarProperties.setHolding(
				this.sugarProperties.getHolding() + toConsume
			);

		myLandscape.removeSugar(this, toConsume);
	}


	public ProductAgentProperties getSugarProperties() {
		return sugarProperties;
	}


	public void setSugarProperties(ProductAgentProperties sugarProperties) {
		this.sugarProperties = sugarProperties;
	}


	public int getVisionLevel() {
		return visionLevel;
	}


	public void setVisionLevel(int visionLevel) {
		this.visionLevel = visionLevel;
	}


	public ConsumeAbility getConsumptionRule() {
		return consumptionRule;
	}


	public void setConsumptionRule(ConsumeAbility consumptionRule) {
		this.consumptionRule = consumptionRule;
	}


	public MoveAbility getMovementRule() {
		return movementRule;
	}


	public void setMovementRule(MoveAbility movementRule) {
		this.movementRule = movementRule;
	}


	public VisionAbility getVisionRule() {
		return visionRule;
	}


	public void setVisionRule(VisionAbility visionRule) {
		this.visionRule = visionRule;
	}


	public DeathAbility getDeathRule() {
		return deathRule;
	}


	public void setDeathRule(DeathAbility deathRule) {
		this.deathRule = deathRule;
	}


	public LandscapeChapter2 getMyLandscape() {
		return myLandscape;
	}


	public void setMyLandscape(LandscapeChapter2 myLandscape) {
		this.myLandscape = myLandscape;
	}
	
	

}
