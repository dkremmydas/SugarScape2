package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;


/**
 * Move to the point with the more resource
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultMovementSugarSpice implements MovementAbility {
	
	

	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> gs) {
		
		SugarAgent_ch4 a_ch4 = (SugarAgent_ch4) a;
		
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
				GridValueLayer gvl_sugar = (GridValueLayer)  SugarSpaceFactory.getSugarspace().getValueLayer("sugar level");
				GridValueLayer gvl_spice = (GridValueLayer)  SugarSpaceFactory.getSugarspace().getValueLayer("spice level");
				
				Double q1_sugar = gvl_sugar.get(arg0.getX(),arg0.getY());
				Double q2_sugar = gvl_sugar.get(arg1.getX(),arg1.getY());
				
				Double q1_spice = gvl_spice.get(arg0.getX(),arg0.getY());
				Double q2_spice = gvl_spice.get(arg1.getX(),arg1.getY());
				
				Double p1_welfare = a_ch4.getWelfareAbility().estimateWelfare(
						a_ch4, 
						a.resourceGetHolding("sugar")+(int)Math.floor(q1_sugar), 
						a.resourceGetHolding("spice")+(int)Math.floor(q1_spice)
						);
				
				Double p2_welfare = a_ch4.getWelfareAbility().estimateWelfare(
						a_ch4, 
						a.resourceGetHolding("sugar")+(int)Math.floor(q2_sugar), 
						a.resourceGetHolding("spice")+(int)Math.floor(q2_spice)
						);
				
//				Utility.logMessage(Level.DEBUG, 
//
//						"Agent: " + a + "\n\t" +
//						"compares points: p1=" + arg0 + " / p2=" + arg1 + "\n\t" +
//						"[sugar,spice]: " + " p1=[sug=" + q1_sugar + ", spic:"+q1_spice + "] / p1=[sug=" + q2_sugar + ", spic:"+q2_spice + "]\n\t" +
//						"welfare:  p1=" + p1_welfare + "  /  p2=" + p2_welfare + "\n\t"+
//						"welfare comparison: " + p1_welfare.compareTo(p2_welfare) 
//						
//						);
//				
				
				int tr = p1_welfare.compareTo(p2_welfare);
				if(tr==0) { //welfare change is the same, so check distance
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
		

	

}
