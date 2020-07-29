package repast.simphony.demos.sugarscape2.agents;

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
