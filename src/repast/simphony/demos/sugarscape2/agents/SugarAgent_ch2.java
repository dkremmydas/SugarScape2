package repast.simphony.demos.sugarscape2.agents;

import java.util.List;

import com.google.common.collect.Sets;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.rules.death.DeathAbility;
import repast.simphony.demos.sugarscape2.agents.rules.gathering.GatheringAbility;
import repast.simphony.demos.sugarscape2.agents.rules.metabolism.MetabolismAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.demos.sugarscape2.products.Resource;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedulableAction;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

public class SugarAgent_ch2 {
	
	
	protected String id;
	
	/**
	 * We store the location as a Repast Simphony GridPoint
	 * TODO[explain why this is an advantage ... code reuse]
	 */
	protected GridPoint location;
	
	protected int metabolism;
	
	protected int levelOfVision;
	

	/**
	 * Properties related to sugar
	 * We have abstracted the properties and the operations related to a resource into a Resource class.
	 * TODO[explain the flexibility of this approach: We can use many resources in the future]
	 */
	protected Resource sugar;
	
	
	
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
	protected DeathAbility deathRule;
		
		

	//Set a private constructor, so that creating agents is forced through the Builder design pattern
	protected SugarAgent_ch2() {};
	
	 public static class Builder {
		 
		 	//properties
		 	private Integer id;
		 	private int ini_x, ini_y;
		 	private int visionLevel;
		 	private Resource sugarProperties;
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
	        
	        public SugarAgent_ch2 build() {
	        	SugarAgent_ch2 ag = new SugarAgent_ch2();
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
	        
	        public Builder withSugarProperties(Resource sugarProperties) {
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
			DefaultGrid<SugarAgent_ch2> g = SimulationContext.getInstance().getLandscape().getGrid();
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
		
			//remove scheduled actions of agent
//			List<ISchedulableAction> toR = this.getScheduledActions();
//			for(ISchedulableAction sa: toR) {
//				RunEnvironment.getInstance().getCurrentSchedule().removeAction(sa);
//			}

			System.out.println("Tick: " + RunEnvironment.getInstance().getCurrentSchedule().getTickCount());
			System.out.println("Number of Agents: " + SimulationContext.getInstance().getObjects(SugarAgent.class).size());
			System.out.println("Number of Scheduled Actions: " + RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
	}



	public int getVisionLevel() {
		return visionLevel;
	} 



	public LandscapeChapter2_p30 getMyLandscape() {
		return myLandscape;
	}

	
	public double getSugarWealth() {
		return this.sugarProperties.getHolding();
	}
	
	public double getMetabolism() {
		return this.sugarProperties.getMetabolism();
	}
	

}
