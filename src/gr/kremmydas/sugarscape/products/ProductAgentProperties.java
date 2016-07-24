package gr.kremmydas.sugarscape.products;

public class ProductAgentProperties {
	
	int quantityStored;
	
	int metabolism;	
	

	public ProductAgentProperties(int quantityStored, int quantityNeeded) {
		super();
		this.quantityStored = quantityStored;
		this.metabolism = quantityNeeded;
	}

	public int getQuantityStored() {
		return quantityStored;
	}

	public void setQuantityStored(int quantityStored) {
		this.quantityStored = quantityStored;
	}

	public int getMetabolism() {
		return metabolism;
	}

	public void setMetabolism(int quantityNeeded) {
		this.metabolism = quantityNeeded;
	}

	
	
	

}
