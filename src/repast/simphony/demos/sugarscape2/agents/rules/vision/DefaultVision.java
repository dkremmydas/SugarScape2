package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Iterables;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.GridPoint;


/**
 * The vision of the agent is determined in the beginning of his life
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultVision implements VisionAbility {
		
		
	/**
	 * Moore or von-Neumman
	 */
	protected Utility.TypeOfVision typeOfVision;
	
	
	public DefaultVision(int levelOfVision, Utility.TypeOfVision typeOfVision) {
		this.typeOfVision  = typeOfVision;
	}	
	

	public DefaultVision() {}	
	
	
	
	@Override
	public Set<GridPoint> see(SugarAgent_ch2 a) {
		
		GridPoint agent_loc = a.getCurrentPosition();
		
		//add neighboring points
		Set<GridPoint> seen_all;
		if(this.typeOfVision==Utility.TypeOfVision.MOORE) {
			seen_all = NeighbourhoodFunctions.getVonNeumanPoints(agent_loc, a.getContext().getGrid(), a.getVision());
		} else {
			seen_all = NeighbourhoodFunctions.getMoorePoints(agent_loc, a.getContext().getGrid(), a.getVision());
		}
		
	
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
	public void configureFromEnvironment() {
		
		
		
		String typeOfVision_str = RunEnvironment.getInstance().getParameters().getString("VisionType");
		
		typeOfVision = (typeOfVision_str.equalsIgnoreCase("Moore"))?Utility.TypeOfVision.MOORE:Utility.TypeOfVision.NEUMMAN;
		

		
	}
	
	
	
	
	

	

}
