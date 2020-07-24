package repast.simphony.demos.sugarscape2.agents;

import java.util.Set;

import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.rules.MetabolismRule_ch2;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;

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
	
	
	
	// Rules
	protected MetabolismRule_ch2 sugar_metabolismRule;	
	
		
		

	//Set a private constructor, so that creating agents is forced through the Builder design pattern
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
	
	
	/* Scheduled actions of the agent */
	
	@SuppressWarnings("unchecked")
	@ScheduledMethod(start=1d,interval=5d)
	public void applyRuleM() {
		
		
		Set<GridPoint> points_seen = this.sugar_metabolismRule.see(this);
		
		GridPoint new_position = this.sugar_metabolismRule.move(this, points_seen);
		
		DefaultContext<SugarAgent_ch2>context = (DefaultContext<SugarAgent_ch2>) RunState.getInstance().getMasterContext().getSubContext("agents"); 
		((DefaultGrid<SugarAgent_ch2>) context.getProjection("sugarscape")).moveTo(this, new_position.getX(),new_position.getY());
		
		this.sugar.store(this.sugar_metabolismRule.gather(this, new_position));
		
		if(this.getSugarWealth() < this.sugar.getMetabolism()) {
			this.isAlive=false;
			context.remove(this);
		} else {
			this.sugar.use(this.getMetabolism());
		}
					
	}
	
	
	
	
	
	
	/**
	 * Builder design pattern
	 * 
	 * @author jkr
	 *
	 */
	public static class Builder {
		 
	 	//properties
	 	private String id;
	 	private int visionLevel;
	 	private int sugarInitial;
	 	private int sugarMetabolism;
		
		// Rules
	 	private MetabolismRule_ch2 sugar_metabolismRule;

	 	
        public Builder(String id) {
        	this.id=id;
        }
        
        public SugarAgent_ch2 build() {
        	SugarAgent_ch2 ag = new SugarAgent_ch2();
        	
        	//TODO check that all required fields have been defined
        	
        	ag.id=this.id;
        	ag.levelOfVision = this.visionLevel;
        	ag.sugar = new AgentResource(this.sugarInitial, this.sugarMetabolism);
        	ag.sugar_metabolismRule=this.sugar_metabolismRule;
      	
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
        
        public Builder set_SugarMetabolismRule(MetabolismRule_ch2 sugar_metabolismRule) {
        	this.sugar_metabolismRule=sugar_metabolismRule;
        	return this;
        }
        
       
        
	 
 }

}
