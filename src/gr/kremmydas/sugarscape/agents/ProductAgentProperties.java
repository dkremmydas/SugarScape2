package gr.kremmydas.sugarscape.agents;

public class ProductAgentProperties {
	
	int quantityStored;
	
	int quantityNeeded;
	
	

	public ProductAgentProperties(int quantityStored, int quantityNeeded) {
		super();
		this.quantityStored = quantityStored;
		this.quantityNeeded = quantityNeeded;
	}

	public int getQuantityStored() {
		return quantityStored;
	}

	public void setQuantityStored(int quantityStored) {
		this.quantityStored = quantityStored;
	}

	public int getQuantityNeeded() {
		return quantityNeeded;
	}

	public void setQuantityNeeded(int quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}
	
	
	

}
