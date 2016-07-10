package gr.kremmydas.sugarscape.products;

public class ProductAgentProperties {
	
	int quantityStored;
	
	int quantityNeeded;
	
	int visibility;
	
	

	public ProductAgentProperties(int quantityStored, int quantityNeeded, int visibility) {
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

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}
	
	
	

}
