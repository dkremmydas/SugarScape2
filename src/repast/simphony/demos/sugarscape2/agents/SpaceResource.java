package repast.simphony.demos.sugarscape2.agents;

import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The set of agent's properties (fixed or variables) that are related to a product
 * 
 * @author Dimitris Kremmydas
 *
 */
public class SpaceResource {
	
	GridValueLayer capacity;
	
	GridValueLayer holding;

	public SpaceResource(GridValueLayer capacity, GridValueLayer holding) {
		super();
		this.capacity = capacity;
		this.holding = holding;
	}
	
		
	public int gatherFromXY(int x,int y,int amountRequested) {
		
		int amountGathered;
		
		int amountAvailable = (int) this.holding.get(x,y);
		
		if(amountAvailable>amountRequested) {
			amountGathered = amountRequested;
		} else {
			amountGathered = amountAvailable;
		}
		
		this.holding.set(amountAvailable-amountGathered, x,y);
		
		return amountGathered;
	}
	
	
	public int availableAtXY(int x, int y) {
		
		return( (int) this.holding.get(x,y) );
		
		
	}
	
	/**
	 * Add a quantity of resource to a x-y GridPoint
	 * The sum of the existing and the added quantity cannot go over the capacity of the point 
	 * 
	 * @param x the x-location
	 * @param y the y-location
	 * @param quant the quantity to add
	 * @return the actual quantity added
	 */
	public int addToXY(int x,int y,int quant) {
		
		int added;
		
		if((this.holding.get(x,y)+quant>this.capacity.get(x,y))) {
			added = (int) (this.capacity.get(x,y)-this.holding.get(x,y));
		} else {
			added = quant;
		}
		
		this.holding.set(this.holding.get(x,y,added),x, y );
		
		return added;
		
	}
	
	/**
	 * Add a quantity of resource to a x-y GridPoint
	 * The sum of the existing and the added quantity cannot go over the capacity of the point
	 * 
	 * @param gp The {@link GridPoint} where the resource is added
	 * @param quant the quantity to add
	 * @return the actual quantity added
	 */
	public int addToXY(GridPoint gp, int quant) {
		return this.addToXY(gp.getX(), gp.getY(), quant);
	}
	
	
	/**
	 * Add in every {@link GridPoint} of the Resource the specified quantity
	 * @param quant the quantity of resource to add
	 */
	public void addEverywhere(int quant) {
		int x,y;
		
		for( x=0; x< this.holding.getDimensions().getWidth();x++) {
			for( y=0; y< this.holding.getDimensions().getHeight();y++) {
				this.addToXY(x, y, quant);
			}
		}
	}
	
	
	/**
	 * Replace the holding {@link GridValueLayer} with the provided one. 
	 * The replacement is done in a 'by value' way
	 * @param newValueLayer
	 */
	public void updateHolding(GridValueLayer newValueLayer) {
		int x,y;
		
		for( x=0; x< this.holding.getDimensions().getWidth();x++) {
			for( y=0; y< this.holding.getDimensions().getHeight();y++) {
				this.holding.set(newValueLayer.get(x,y), x,y);
			}
		}
	}


	public GridValueLayer getCapacity() {
		return capacity;
	}


	public GridValueLayer getHolding() {
		return holding;
	}
	
	
	
		
	

}
