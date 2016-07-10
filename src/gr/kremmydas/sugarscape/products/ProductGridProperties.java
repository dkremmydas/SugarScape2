package gr.kremmydas.sugarscape.products;

import gr.kremmydas.sugarscape.SimulationContext;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Various properties of a Product (sugar, pepper) on the grid
 * 
 * @author Dimitris Kremmydas
 *
 */
public class ProductGridProperties {

	private GridValueLayer currentQuantity;
	
	private GridValueLayer regenerationRate;
	
	private GridValueLayer capacity;
	
	public ProductGridProperties(int x, int y, String name) {
		this.currentQuantity = new GridValueLayer(name + "_quantity", true, x,y);
		SimulationContext.getInstance().addValueLayer(this.currentQuantity);
		
		this.regenerationRate = new GridValueLayer(name + "rate", true, x,y);
		SimulationContext.getInstance().addValueLayer(this.regenerationRate);
		
		this.capacity = new GridValueLayer(name + "_capacity", true, x,y);
		SimulationContext.getInstance().addValueLayer(this.capacity);
	}

	public GridValueLayer getCurrentQuantity() {
		return currentQuantity;
	}

	public void setCurrentQuantity(GridValueLayer currentQuantity) {
		this.currentQuantity = currentQuantity;
	}

	public GridValueLayer getRegenerationRate() {
		return regenerationRate;
	}

	public void setRegenerationRate(GridValueLayer regenerationRate) {
		this.regenerationRate = regenerationRate;
	}

	public GridValueLayer getCapacity() {
		return capacity;
	}

	public void setCapacity(GridValueLayer capacity) {
		this.capacity = capacity;
	}
	
	
	
}
