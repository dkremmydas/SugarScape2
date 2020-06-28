package repast.simphony.demos.sugarscape2.landscape;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Various properties of a Product (sugar, pepper) on the grid
 * 
 * @author Dimitris Kremmydas
 *
 */
public class LandscapeResource {

	private GridValueLayer currentQuantity;
	
	private GridValueLayer regenerationRate;
	
	private GridValueLayer capacity;
	
	public LandscapeResource(int x, int y, String name) {
		this.currentQuantity = new GridValueLayer(name + "_quantity", true, x,y);
		SimulationContext.getInstance().addValueLayer(this.currentQuantity);
		
		this.regenerationRate = new GridValueLayer(name + "_rate", true, x,y);
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
	
	public DescriptiveStatistics getQuantityDescriptiveStats() {
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for(int i=0;i<currentQuantity.getDimensions().getWidth();i++) {
			for(int j=0;j<currentQuantity.getDimensions().getHeight();j++) {
				stats.addValue(currentQuantity.get(i,j));
			}
		}
		return stats;
	}
	
}
