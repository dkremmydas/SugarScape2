package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Iterables;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.GridPoint;


/**
 * The vision of the agent is determined in the beginning of his life
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultVision implements VisionAbility {
		
	
	
	/**
	 * How many lattices they can see 
	 */
	protected int levelOfVision;
	
	
	/**
	 * Moore or von-Neumman
	 */
	enum TypeOfVision {  MOORE, NEUMMAN }
	
	protected TypeOfVision typeOfVision;
	
	

	
	
	public DefaultVision(int levelOfVision, TypeOfVision typeOfVision) {
		this.typeOfVision  = typeOfVision;
		this.levelOfVision = levelOfVision;
	}	
	
	
	
	@Override
	public Set<GridPoint> see(SugarAgent_ch2 a) {
		
		GridPoint agent_loc = a.getCurrentPosition();
		
		//add neighboring points
		Set<GridPoint> seen_all;
		if(this.typeOfVision==TypeOfVision.MOORE) {
			seen_all = NeighbourhoodFunctions.getVonNeumanPoints(agent_loc, a.getContext().getGrid(), levelOfVision);
		} else {
			seen_all = NeighbourhoodFunctions.getMoorePoints(agent_loc, a.getContext().getGrid(), levelOfVision);
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
	public int getVisionLevel(SugarAgent_ch2 a) {
		return levelOfVision;
	}
	
	


	public static DefaultVision fromRunenvParameters() {
		
		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision");
		
		String typeOfVision_str = RunEnvironment.getInstance().getParameters().getString("VisionType");
		
		DefaultVision.TypeOfVision typeOfVision = (typeOfVision_str.equalsIgnoreCase("Moore"))?DefaultVision.TypeOfVision.MOORE:DefaultVision.TypeOfVision.NEUMMAN;
		
		return new DefaultVision(RandomHelper.nextIntFromTo(1, maxVision),typeOfVision);
		
	}
	
	
	
	
	

	

}
