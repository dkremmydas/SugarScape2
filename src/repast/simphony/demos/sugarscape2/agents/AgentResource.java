package repast.simphony.demos.sugarscape2.agents;

/**
 * The set of agent's properties (fixed or variables) that are related to a product
 * 
 * @author Dimitris Kremmydas
 *
 */
public class AgentResource {
	
	/**
	 * Initial endowment
	 */
	int initial;
	
	
	/**
	 * Product stored (p. 24)
	 */
	int holding;
	
	/**
	 * Amount of product consumed per time step (p. 24)
	 */
	int metabolism;	
	
	

	public AgentResource(int initialEndownment, int metabolism) {
		this.initial = initialEndownment;
		this.holding = initialEndownment;
		this.metabolism = metabolism;
	}


	/**
	 * The amount of resource to use (i.e. remove from holding)
	 * 
	 * @param int quantity
	 * @return int The quantity of resource that has been subtracted from the resource olding
	 */
	public void use (int quantity) {
		
		if(quantity>0) {
			holding -= quantity;
		}	

	}
	
	public void store(int quantity) {	
		
		if(quantity>0) {
			holding += quantity;
		}		
		
	}

	
	public int getMetabolism() {
		return metabolism;
	}

	
	public int getHolding() {
		return holding;
	}

	
	
	

}
