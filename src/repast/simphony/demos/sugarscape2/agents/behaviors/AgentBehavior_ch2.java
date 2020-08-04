package repast.simphony.demos.sugarscape2.agents.behaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Iterables;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.DieAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.GatherAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.abilities.agents.VisionAbility;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;


/**
 * Methods of this class should not change the state of agents
 * 
 * @author Dimitris Kremmydas
 *
 */
public class AgentBehavior_ch2 implements VisionAbility,MovementAbility,GatherAbility,DieAbility {
	
	
	/**
	 * The name of the {@link ValueLayer} of the resource
	 */
	private String valueLayerName;
	
	
	
	/**
	 * How many lattices they can see 
	 */
	protected int levelOfVision;
	
	
	
	
	public AgentBehavior_ch2(String valueLayerName, int levelOfVision) {
		this.valueLayerName  =valueLayerName;
		this.levelOfVision = levelOfVision;
	}	
	
	
	
	@Override
	public Set<GridPoint> see(SugarAgent_ch2 a) {
		
		GridPoint agent_loc = a.getCurrentPosition();
		
		//add neighboring points
		Set<GridPoint> seen_all = NeighbourhoodFunctions.getVonNeumanPoints(agent_loc, a.getContext().getGrid(), levelOfVision);
	
		Set<GridPoint> seen_empty = new HashSet<GridPoint>();
		//remove occupied space
		for(GridPoint s : seen_all) {
			if(  Iterables.size(a.getContext().getGrid().getObjectsAt(s.getX(),s.getY()))==0 ) {
				seen_empty.add(s);
			}
		}
		
		if(seen_empty.size()==0) {
			seen_empty.add(a.getCurrentPosition());
		}
		
		return seen_empty;
		
		
	}
	
	

	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> gs) {
		
		//1. Get points that the agent can sees
		List<GridPoint> gps = new ArrayList<GridPoint>(gs);
		
		GridPoint myPoint = a.getCurrentPosition();
		
		
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

		
		//3. Return the GridPoint that is higher in the list
		//	 Since the points passed to the method contain only empty GridPoints, we do not check for emptyness
		return gps.get(0);
	}
		


	@Override
	public int gather(SugarAgent_ch2 a,GridPoint g) {
		
		GridValueLayer gvl = (GridValueLayer) a.getContext().getValueLayer(valueLayerName);
		
		return (int) gvl.get(g.getX(),g.getY());
		
	}



	@Override
	public boolean shallDie(SugarAgent_ch2 a) {
		if(a.getSugarWealth() < 0) {
			return  true;	
		} 	else {
			return false;
		}
	}


	

	public String getValueLayerName() {
		return valueLayerName;
	}



	public int getLevelOfVision() {
		return levelOfVision;
	}
	
	
	
		
	
		
	

	

}
