package gr.kremmydas.sugarscape.products;

/**
 * The set of agent's properties (fixed or variables) that are related to a product
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ProductAgentProperties {
	
	/**
	 * Product stored (p. 24)
	 */
	int holding;
	
	/**
	 * Amount of product consumed per time step (p. 24)
	 */
	int metabolism;	
	

	public ProductAgentProperties(int quantityStored, int quantityNeeded) {
		super();
		this.holding = quantityStored;
		this.metabolism = quantityNeeded;
	}

	public int getHolding() {
		return holding;
	}

	public void setHolding(int quantityStored) {
		this.holding = quantityStored;
	}

	public int getMetabolism() {
		return metabolism;
	}

	public void setMetabolism(int quantityNeeded) {
		this.metabolism = quantityNeeded;
	}

	
	
	

}
