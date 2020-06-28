package repast.simphony.demos.sugarscape2.agents;

import repast.simphony.demos.sugarscape2.agents.rules.old.death.DeathAbility;
import repast.simphony.demos.sugarscape2.agents.rules.old.metabolism.MetabolismAbility;
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
	protected MetabolismAbility metabolismRule;	
	protected DeathAbility deathRule;
		
		

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

	 	
        public Builder(String id) {
        	this.id=id;
        }
        
        public SugarAgent_ch2 build() {
        	SugarAgent_ch2 ag = new SugarAgent_ch2();
        	ag.id=this.id;
        	ag.levelOfVision = this.visionLevel;
        	ag.sugar = new AgentResource(this.sugarInitial, this.sugarMetabolism);
      	
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
        
       
        
	 
 }

}
