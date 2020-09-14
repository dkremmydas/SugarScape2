package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.GridPoint;


/**
 * Move to the point with the more resource
 * 
 * @author Dimitris Kremmydas
 *
 */
public class RandomMovement extends DefaultMovement {
	

	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> gs) {

		List<GridPoint> gps = new ArrayList<GridPoint>(gs);
		
		return gps.get(RandomHelper.nextIntFromTo(0, ArrayUtils.getLength(gps.toArray())));
		
	}







}
