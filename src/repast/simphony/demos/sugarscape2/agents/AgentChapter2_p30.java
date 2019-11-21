package repast.simphony.demos.sugarscape2.agents;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.rules.death.DeathAbility;
import repast.simphony.demos.sugarscape2.agents.rules.gathering.GatheringAbility;
import repast.simphony.demos.sugarscape2.agents.rules.metabolism.MetabolismAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.products.ProductAgentProperties;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

public class AgentChapter2_p30 extends Agent {
	
	/**
	 * Properties related to sugar
	 */
	protected ProductAgentProperties sugarProperties;
	
	/**
	 * Level of vision
	 */
	protected int visionLevel;
	
	/**
	 * Is the agent alive ?
	 */
	protected boolean isAlive = true;
	
	/**
	 * Reference to the parent landscape
	 */
	protected LandscapeChapter2_p30 myLandscape; 	
	
	// Rules
	protected MetabolismAbility metabolismRule;	
	protected GatheringAbility gatheringRule;	
	protected MovementAbility movementRule;	
	protected VisionAbility visionRule;	
	protected DeathAbility deathRule;
		
		

	//Set a private constructor, so that creating agents is forced through the Builder design pattern
	private AgentChapter2_p30() {};
	
	 public static class Builder {
		 
		 	//properties
		 	private Integer id;
		 	private int ini_x, ini_y;
		 	private int visionLevel;
		 	private ProductAgentProperties sugarProperties;
		 	private LandscapeChapter2_p30 myLandscape; 	
			
			// Rules
		 	private MetabolismAbility metabolismRule;	
		 	private GatheringAbility gatheringRule;	
		 	private MovementAbility movementRule;	
		 	private VisionAbility visionRule;	
		 	private DeathAbility deathRule;
		 	
	        public Builder(Integer id) {
	        	this.id=id;
	        }
	        
	        public AgentChapter2_p30 build() {
	        	AgentChapter2_p30 ag = new AgentChapter2_p30();
	        	ag.id=this.id;
	        	ag.ini_x = this.ini_x;
	        	ag.ini_y = this.ini_y;
	        	ag.visionLevel = this.visionLevel;
	        	ag.sugarProperties = this.sugarProperties;
	        	ag.myLandscape = this.myLandscape;
	        	ag.metabolismRule = this.metabolismRule;
	        	ag.gatheringRule = this.gatheringRule;
	        	ag.movementRule = this.movementRule;
	        	ag.visionRule = this.visionRule;
	        	ag.deathRule = this.deathRule;        	
	        	
	        	return ag;
	        }
	        
	        public Builder atLocationX(int x) {
	        	this.ini_x=x;
	        	return this;
	        }
	        
	        public Builder atLocationY(int y) {
	        	this.ini_y=y;
	        	return this;
	        }
	        
	        public Builder withVisionLevel(int visionLevel) {
	        	this.visionLevel=visionLevel;
	        	return this;
	        }
	        
	        public Builder withSugarProperties(ProductAgentProperties sugarProperties) {
	        	this.sugarProperties=sugarProperties;
	        	return this;
	        }
	        
	        public Builder onLandscape(LandscapeChapter2_p30 myLandscape) {
	        	this.myLandscape=myLandscape;
	        	return this;
	        }
	        
	        public Builder withMetabolismRule(MetabolismAbility metabolismRule) {
	        	this.metabolismRule=metabolismRule;
	        	return this;
	        }
	        
	        public Builder withGatheringRule(GatheringAbility gatheringRule) {
	        	this.gatheringRule=gatheringRule;
	        	return this;
	        }
	        
	        public Builder withMovementRule(MovementAbility movementRule) {
	        	this.movementRule=movementRule;
	        	return this;
	        }
	        
	        public Builder withVisionRule(VisionAbility visionRule) {
	        	this.visionRule=visionRule;
	        	return this;
	        }
	        
	        public Builder withDeathRule(DeathAbility deathRule) {
	        	this.deathRule=deathRule;
	        	return this;
	        }
		 
	 }
	
	@ScheduledMethod(start=1d,interval=5d)
	public void move() {
		if(isAlive) {
			DefaultGrid<Agent> g = SimulationContext.getInstance().getLandscape().getGrid();
			GridPoint gp;
			gp=this.movementRule.move(this);
			g.moveTo(this, gp.getX(),gp.getY());
		}
	}
	
	@ScheduledMethod(start=2.5d,interval=5d)
	public void metabolize() {
		if(isAlive) {
			int toMetabolize = this.metabolismRule.metabolize(this);
			
			//eat necessary sugar
			this.sugarProperties.setHolding(this.sugarProperties.getHolding()-toMetabolize);
	
		}
	}
	
	@ScheduledMethod(start=2d,interval=5d)
	public void gather() {
		if(isAlive) {
			int toGather = this.gatheringRule.gather(this);
			
			this.sugarProperties.setHolding(
					this.sugarProperties.getHolding() + toGather
				);
	
			//remove sugar from landscape
			myLandscape.removeSugar(this, toGather);
	
		}
	}
	
	@ScheduledMethod(start=3d,interval=5d)
	public void die() {
		if(isAlive) {
			if(this.deathRule.die(this)) {
				//System.out.println("Agent with id=" + this.id + " is dead");
				this.isAlive = false;
				
				//remove from context
				SimulationContext.getInstance().remove(this);
			}
		}
		
			
			//advanced
		/*
			//remove scheduled actions of agent
			List<ISchedulableAction> toR = this.getScheduledActions();
			for(ISchedulableAction sa: toR) {
				RunEnvironment.getInstance().getCurrentSchedule().removeAction(sa);
			}

			System.out.println("Tick: " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());
			System.out.println("Number of Agents: " + SimulationContext.getInstance().getObjects(Agent.class).size());
			System.out.println("Number of Scheduled Actions: " + RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		*/
	}


	public ProductAgentProperties getSugarProperties() {
		return sugarProperties;
	}

	public int getVisionLevel() {
		return visionLevel;
	} 

	public MetabolismAbility getMetabolismRule() {
		return metabolismRule;
	}

	public GatheringAbility getGatheringRule() {
		return gatheringRule;
	}

	public MovementAbility getMovementRule() {
		return movementRule;
	}

	public VisionAbility getVisionRule() {
		return visionRule;
	}

	public DeathAbility getDeathRule() {
		return deathRule;
	}


	public LandscapeChapter2_p30 getMyLandscape() {
		return myLandscape;
	}

	public void setMyLandscape(LandscapeChapter2_p30 myLandscape) {
		this.myLandscape = myLandscape;
	}
	
	public double getSugarWealth() {
		return this.sugarProperties.getHolding();
	}
	
	public double getMetabolism() {
		return this.sugarProperties.getMetabolism();
	}
	

}
