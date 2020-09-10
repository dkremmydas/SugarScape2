package repast.simphony.demos.sugarscape2.agents.rules.sex;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.space.grid.GridPoint;

/**
 * This is the Rule S, as described in the book
 * TODO[give exact description]
 * 
 * @author Dimitris Kremmydas
 *
 */
public class CulturalSexAbility extends DefaultSexAbility {

	@Override
	public Pair<SugarAgent_ch3, GridPoint> giveBirth(SugarAgent_ch3 a1, SugarAgent_ch3 a2) {

		Pair<SugarAgent_ch3, GridPoint> birth = super.giveBirth(a1, a2);
		
		if(!(birth.getLeft()==null)) {
			
			//compute the cultural tags
			for (int position = 1; position <= birth.getLeft().tagGetSize(); position++) {
				
				if(a1.tagGetAtPosition(position).equals(a2.tagGetAtPosition(position))) {
					birth.getLeft().tagSetAtPosition(position,a1.tagGetAtPosition(position));
				}
				
				//else, we retain the random value given in the random creation of the child
				
			}
		}	
		
		return birth;
	}






}
