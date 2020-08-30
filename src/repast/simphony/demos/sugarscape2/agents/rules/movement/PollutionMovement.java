package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;


/**
 * Move to the point with the more resource
 * 
 * @author Dimitris Kremmydas
 *
 */
public class PollutionMovement extends DefaultMovement {
	
	

	public PollutionMovement(String valueLayerName) {
		super(valueLayerName);
	}	
	
		
	

	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> gs) {
		
		String valueLayerName = this.getValueLayerName();
		
		//1. Get points that the agent can sees
		List<GridPoint> gps = new ArrayList<GridPoint>(gs);
		
		GridPoint myPoint = a.getCurrentPosition();
		
		
		//2. Sort the points by available quantity
		Collections.sort(gps, new Comparator<GridPoint>() {
			@Override
			public int compare(GridPoint arg0, GridPoint arg1) {
				GridValueLayer gvl = (GridValueLayer) a.getContext().getValueLayer(valueLayerName);
				GridValueLayer pollution_vl = (GridValueLayer) a.getContext().getValueLayer("pollution");
				
				Double q1 = gvl.get(arg0.getX(),arg0.getY())/pollution_vl.get(arg0.getX(),arg0.getY());
				Double q2 = gvl.get(arg1.getX(),arg1.getY())/pollution_vl.get(arg0.getX(),arg0.getY());
				int tr = q2.compareTo(q1);
				if(tr==0) { //there is the same amount of sugar, so check distance
					Double dis1 = a.getContext().getDistance(myPoint, arg0);
					Double dis2 = a.getContext().getDistance(myPoint, arg1);
					tr = dis1.compareTo(dis2);
				}
				return tr;
			}
		});

		
		//3. Return the GridPoint that is higher in the list
		//	 Since the points passed to the method contain only empty GridPoints, we do not check for emptiness
		return gps.get(0);
	}
	

	

}
