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
	public int use (int quantity) {
		int used = 0;
		
		if(quantity<0) {
			used=0;
		}
		else if(quantity>this.holding) {
			used = this.holding;
		} else {
			used=quantity;
		}
		
		this.holding =- used;
		return(used);
	}
	
	public void store(int quantity) {
		int stored = 0;
		
		if(quantity>0) {
			stored=quantity;
		}
		
		this.holding =+ stored;
	}

	
	public int getMetabolism() {
		return metabolism;
	}

	
	public int getHolding() {
		return holding;
	}

	
	
	

}
