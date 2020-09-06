package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;


/**
 * Move to the point with the more resource
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultMovement implements MovementAbility {
	
	
	/**
	 * The name of the {@link ValueLayer} of the resource
	 */
	private String valueLayerName;
	
	

	public DefaultMovement(String valueLayerName) {
		this.valueLayerName  = valueLayerName;
	}	
	
		
	

	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> gs) {
		
		if(gs.isEmpty()) {
			return a.getCurrentPosition();
		}
		
		//1. Get points that the agent can sees
		List<GridPoint> gps = new ArrayList<GridPoint>(gs);
		
		GridPoint myPoint = a.getCurrentPosition();
		
		
		//2. Sort the points by available quantity
		Collections.sort(gps, new Comparator<GridPoint>() {
			@Override
			public int compare(GridPoint arg0, GridPoint arg1) {
				GridValueLayer gvl = (GridValueLayer)  SugarSpaceFactory.getSugarspace().getValueLayer(valueLayerName);
				
				Double q1 = gvl.get(arg0.getX(),arg0.getY());
				Double q2 = gvl.get(arg1.getX(),arg1.getY());
				int tr = q2.compareTo(q1);
				if(tr==0) { //there is the same amount of sugar, so check distance
					Double dis1 =  SugarSpaceFactory.getSugarspace().gridGetDistance(myPoint, arg0);
					Double dis2 =  SugarSpaceFactory.getSugarspace().gridGetDistance(myPoint, arg1);
					tr = dis1.compareTo(dis2);
				}
				return tr;
			}
		});

		
		//3. Return the GridPoint that is higher in the list
		//	 Since the points passed to the method contain only empty GridPoints, we do not check for emptiness
		return gps.get(0);
	}
		

	@Override
	public String getValueLayerName() {
		return valueLayerName;
	}

	

}
