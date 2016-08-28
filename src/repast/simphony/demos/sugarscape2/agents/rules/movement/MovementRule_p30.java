package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.agents.Agent;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p30;
import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p30;
import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The movement rule applied in the book
 * 
 * @author Dimitris Kremmydas
 *
 */
public class MovementRule_p30 implements MovementAbility {

	public MovementRule_p30() {
		super();
	}

	/**
	 * Move to the visible point with the greatest concentration of sugar
	 */
	@Override
	public GridPoint move(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		//1. Get points that the agent can sees
		List<GridPoint> gps = new ArrayList<GridPoint>(o.getVisionRule().getVisionedPoints(owner));
		
		//in order for the inner class to be able to see theLandscape, it has to be final
		final LandscapeChapter2_p30 theLandscape = o.getMyLandscape();
		final GridPoint myPoint = theLandscape.getGrid().getLocation(o);
		
		//2. Sort the points by available quantity
		Collections.sort(gps, new Comparator<GridPoint>() {
			@Override
			public int compare(GridPoint arg0, GridPoint arg1) {
				GridValueLayer gvl = theLandscape.getSugarGridProperties().getCurrentQuantity();
				
				Double q1 = gvl.get(arg0.getX(),arg0.getY());
				Double q2 = gvl.get(arg1.getX(),arg1.getY());
				int tr = q2.compareTo(q1);
				if(tr==0) { //there is the same amount of sugar, so check distance
					Double dis1 = theLandscape.getGrid().getDistance(myPoint, arg0);
					Double dis2 = theLandscape.getGrid().getDistance(myPoint, arg1);
					tr = dis1.compareTo(dis2);
				}
				return tr;
			}
		});
		//GridValueLayer gvl = SimulationContext.getInstance().getLandscape().getSugarGridProperties().getCurrentQuantity();
		//for(GridPoint gp: gps) {
		//	System.out.print(gp + ", q:"+gvl.get(gp.getX(),gp.getY()));
		//}
		//System.out.println();
		//System.out.println(Arrays.toString(gps.toArray()));
		
		//3. Return the GridPoint that is higher in the list and no one else is there
		DefaultGrid<Agent> dg = SimulationContext.getInstance().getLandscape().getGrid();
		for(GridPoint gp: gps) {
			if(! dg.getObjectsAt(gp.getX(),gp.getY()).iterator().hasNext()) return gp;
		}
		
		//4. If everything else is occupied by others, stay at the same point
		return dg.getLocation(owner);
	}

}
