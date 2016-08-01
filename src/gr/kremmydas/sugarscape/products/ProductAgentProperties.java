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
	double holding;
	
	/**
	 * Amount of product consumed per time step (p. 24)
	 */
	double metabolism;	
	

	public ProductAgentProperties(double quantityStored, double quantityNeeded) {
		super();
		this.holding = quantityStored;
		this.metabolism = quantityNeeded;
	}

	public double getHolding() {
		return holding;
	}

	public void setHolding(double quantityStored) {
		this.holding = quantityStored;
	}

	public double getMetabolism() {
		return metabolism;
	}

	public void setMetabolism(double quantityNeeded) {
		this.metabolism = quantityNeeded;
	}

	
	
	

}
