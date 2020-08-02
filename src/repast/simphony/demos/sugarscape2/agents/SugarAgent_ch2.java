package repast.simphony.demos.sugarscape2.agents;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.GridPoint;


/**
 * 
 * 
 * @author Dimitris Kremmydas
 *
 */
public class SugarAgent_ch2 {
	
	
	protected String id;
	
//	/**
//	 * We store the location as a Repast Simphony GridPoint
//	 * TODO[explain why this is an advantage ... code reuse]
//	 */
//	protected GridPoint location;
//	
	
	/**
	 * How many lattices they can see 
	 */
	protected int levelOfVision;
	

	/**
	 * Properties related to sugar
	 * We have abstracted the properties and the operations related to a resource into a Resource class.
	 * TODO[explain the flexibility of this approach: We can use many resources in the future]
	 */
	protected AgentResource sugar;
	
	
	
	/**
	 * Is the agent alive ?
	 */
	protected boolean isAlive = true;
	
	/**
	 * The behavior of the Agent 
	 */
	protected AgentBehavior_ch2 behavior;	
	
	/**
	 * Reference to the context
	 */
	protected SugarSpace_ch2 context;
		
		
	/**
	 * Set a private constructor, so that creating agents is forced through the Builder design pattern 
	 */
	protected SugarAgent_ch2() {};
	



	public int getVisionLevel() {
		return levelOfVision;
	} 

	
	public int getSugarWealth() {
		return this.sugar.getHolding();
	}
	
	public int getMetabolism() {
		return this.sugar.getMetabolism();
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	
	public SugarSpace_ch2 getContext() {
		return context;
	}
	
	public GridPoint getCurrentPosition() {
		return this.context.grid.getLocation(this);
	}
	


	@Override
	public String toString() {
		
		int x = this.getCurrentPosition().getX(); 
		int y = this.getCurrentPosition().getY();
		
		String r = "{Id:"+this.id+", Vision: "+this.levelOfVision +
				", Sugar.metab: " + this.sugar.metabolism + 
				", Sugar.hold: " + this.sugar.holding + 
				", Position: [X:"+x+", Y:"+y+", Sugar:"+this.context.getSugar().getHolding().get(x,y)+"]"+
				"}";
		
		
		
		return r;
	}
	

	
	
	/* Scheduled actions of the agent */
	

	@ScheduledMethod(start=1d,interval=5d)
	public void applyRuleM() {
		
		if(isAlive) {
			Set<GridPoint> points_seen = this.behavior.see(this);
			
			GridPoint new_pos = this.behavior.move(this, points_seen);
			
			context.getGrid().moveTo(this, new_pos.getX(),new_pos.getY());
			
			int sugar_gathered = this.behavior.gather(this, new_pos);
			
			this.sugar.store(sugar_gathered);
			this.context.sugar.gatherFromXY(new_pos.getX(), new_pos.getY(), sugar_gathered);
			
			this.sugar.use(this.getMetabolism());
			
			//die if sugar holding<0
			if(this.getSugarWealth() < 0) {
				this.die();		
			} 	
		}
				
	}
	
	
	
	private void die() {
		this.isAlive=false;
		this.context.remove(this);	
		//System.out.println("DIED: " + this);
	}
	
	
	
	
	
	
	
	/**
	 * Builder design pattern
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	public static class Builder {
		 
	 	//properties
	 	private String id;
	 	private int visionLevel;
	 	private int sugarInitial;
	 	private int sugarMetabolism;
	 	private SugarSpace_ch2 context;
		
		// Rules
	 	private AgentBehavior_ch2 behavior_builder;

	 	
        public Builder(String id,SugarSpace_ch2 context) {
        	this.id=id;
        	this.context=context;
        }
        
        public SugarAgent_ch2 build() {
        	SugarAgent_ch2 ag = new SugarAgent_ch2();
        	
        	//TODO check that all required fields have been defined
        	
        	ag.context=this.context;
        	ag.id=this.id;
        	ag.levelOfVision = this.visionLevel;
        	ag.sugar = new AgentResource(this.sugarInitial, this.sugarMetabolism);
        	ag.behavior=this.behavior_builder;
      	
        	return ag;
        }
        
               
        public Builder withVisionLevel(int visionLevel) {
        	this.visionLevel=visionLevel;
        	return this;
        }
        
        public Builder withSugarInitial(int sugar) {
        	this.sugarInitial=sugar;
        	return this;
        }
        
        public Builder withSugarMetabolism(int metabolism) {
        	this.sugarMetabolism=metabolism;
        	return this;
        }
        
        public Builder set_SugarMetabolismRule(AgentBehavior_ch2 behavior) {
        	this.behavior_builder=behavior;
        	return this;
        }
        
       
        
	 
 }

}
