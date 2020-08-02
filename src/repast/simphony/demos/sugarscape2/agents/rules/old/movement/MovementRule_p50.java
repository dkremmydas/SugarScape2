package repast.simphony.demos.sugarscape2.agents.rules.old.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import repast.simphony.demos.sugarscape2.agents.SugarAgent;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.old.SugarAgent_ch2p50;
import repast.simphony.demos.sugarscape2.landscape.old.LandscapeChapter2_p50;
import repast.simphony.demos.sugarscape2.landscape.old.SimulationContext;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public class MovementRule_p50 implements MovementAbility {

	public MovementRule_p50() {}

	@Override
	/**
	 * Move to the visible point with the maximum sugar to pollution ratio
	 */
	public GridPoint move(SugarAgent owner) {
		SugarAgent_ch2p50 o = (SugarAgent_ch2p50) owner;
		
		//1. Get points that the agent can see
		List<GridPoint> gps = new ArrayList<GridPoint>(o.getVisionRule().getVisionedPoints(owner));
		
		//in order for the inner class to be able to see theLandscape, it has to be final
		final LandscapeChapter2_p50 theLandscape = (LandscapeChapter2_p50) o.getMyLandscape();
		final GridPoint myPoint = theLandscape.getGrid().getLocation(o);
		
		//2. Sort the points by available quantity
		Collections.sort(gps, new Comparator<GridPoint>() {
			@Override
			public int compare(GridPoint arg0, GridPoint arg1) {
				GridValueLayer sugar_vl = theLandscape.getSugarGridProperties().getCurrentQuantity();
				GridValueLayer pollution_vl = theLandscape.getPollution();
				
				Double q1 = sugar_vl.get(arg0.getX(),arg0.getY())/pollution_vl.get(arg0.getX(),arg0.getY());
				Double q2 = sugar_vl.get(arg1.getX(),arg1.getY())/pollution_vl.get(arg1.getX(),arg1.getY());
				int tr = q2.compareTo(q1);
				if(tr==0) { //there is the same amount of sugar, so check distance
					Double dis1 = theLandscape.getGrid().getDistance(myPoint, arg0);
					Double dis2 = theLandscape.getGrid().getDistance(myPoint, arg1);
					tr = dis1.compareTo(dis2);
				}
				return tr;
			}
		});
		
		//3. Return the GridPoint that is higher in the list and no one else is there
		DefaultGrid<SugarAgent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		for(GridPoint gp: gps) {
			if(! dg.getObjectsAt(gp.getX(),gp.getY()).iterator().hasNext()) return gp;
		}
		
		//4. If everything else is occupied by others, stay at the same point
		return dg.getLocation(owner);
	}
	
	

}
