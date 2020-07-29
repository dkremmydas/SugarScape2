package repast.simphony.demos.sugarscape2.agents.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.GatherAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.VisionAbility;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;

public class AgentBehavior_ch2 implements VisionAbility,MovementAbility,GatherAbility {
	
	

	
	/**
	 * The name of the {@link ValueLayer} of the resource
	 */
	private String valueLayerName;
	
	
	
	public AgentBehavior_ch2(String valueLayerName) {
		this.valueLayerName=valueLayerName;
	}	
	
	
	
	@Override
	public Set<GridPoint> see(SugarAgent_ch2 a) {
		
		return NeighbourhoodFunctions.getVonNeumanPoints(a.getContext().getGrid().getLocation(a), a.getContext().getGrid(), a.getVisionLevel());
		
	}
	
	

	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> gs) {
		
		//1. Get points that the agent can sees
		List<GridPoint> gps = new ArrayList<GridPoint>(gs);
		
		GridPoint myPoint = a.getContext().getGrid().getLocation(a);
		
		
		//2. Sort the points by available quantity
		Collections.sort(gps, new Comparator<GridPoint>() {
			@Override
			public int compare(GridPoint arg0, GridPoint arg1) {
				GridValueLayer gvl = (GridValueLayer) a.getContext().getValueLayer(valueLayerName);
				
				Double q1 = gvl.get(arg0.getX(),arg0.getY());
				Double q2 = gvl.get(arg1.getX(),arg1.getY());
				int tr = q2.compareTo(q1);
				if(tr==0) { //there is the same amount of sugar, so check distance
					Double dis1 = a.getContext().getGrid().getDistance(myPoint, arg0);
					Double dis2 = a.getContext().getGrid().getDistance(myPoint, arg1);
					tr = dis1.compareTo(dis2);
				}
				return tr;
			}
		});

		
		//3. Return the GridPoint that is higher in the list and no one else is there

		for(GridPoint gp: gps) {
			if(! a.getContext().getGrid().getObjectsAt(gp.getX(),gp.getY()).iterator().hasNext()) return gp;
		}
		
		//4. If everything else is occupied by others, stay at the same point
		return myPoint;
	}
		


	@Override
	public int gather(SugarAgent_ch2 a,GridPoint g) {
		
		GridValueLayer gvl = (GridValueLayer) a.getContext().getValueLayer(valueLayerName);
		
		return (int) gvl.get(g.getX(),g.getY());
		
	}

	
	

	

}
