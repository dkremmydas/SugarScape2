package repast.simphony.demos.sugarscape2.agents.rules.vision;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.demos.sugarscape2.utilities.ConfigurableFromRepastEnvironment;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.demos.sugarscape2.utilities.Utility.TypeOfVision;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.GridPoint;


/**
 * The vision of the agent is determined in the beginning of his life
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultVision implements VisionAbility, ConfigurableFromRepastEnvironment {
		
		
	/**
	 * Moore or von-Neumman
	 */
	protected Utility.TypeOfVision typeOfVision;
	
	
	public DefaultVision(int levelOfVision, Utility.TypeOfVision typeOfVision) {
		this.typeOfVision  = typeOfVision;
	}	
	

	public DefaultVision() {}	
	
	


	@Override
	public Set<GridPoint> seeAll(SugarAgent_ch2 a) {
		Set<GridPoint> seen_all = (Set<GridPoint>)  SugarSpaceFactory.getSugarspace().gridGetNeighboringPoints(a, a.getVision(), this.typeOfVision);;
		return seen_all;
	}
	
	@Override
	public Set<GridPoint> seeEmpty(SugarAgent_ch2 a) {
		
		//add neighboring points
		Set<GridPoint> seen_all = this.seeAll(a);		
	
		Set<GridPoint> seen_empty = new HashSet<GridPoint>();
		//remove occupied space
		for(GridPoint s : seen_all) {
			if(   SugarSpaceFactory.getSugarspace().gridGetSugarAgentAt(s.getX(),s.getY())==null ) {
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


	@Override
	public TypeOfVision getTypeOfVision(SugarAgent_ch2 a) {
		return typeOfVision;
	}

	
	
	
	
	

	

}
