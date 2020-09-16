package repast.simphony.demos.sugarscape2.agents.rules.sex;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.space.grid.GridPoint;

public class DefaultSexAbilitySugarSpice extends DefaultSexAbility {

	@Override
	public Pair<SugarAgent_ch3, GridPoint> giveBirth(SugarAgent_ch3 a1, SugarAgent_ch3 a2) {
		
		Pair<SugarAgent_ch3,GridPoint> birth = super.giveBirth(a1, a2);
		
		//SugarAgent_ch4 child = SugarAgentFactory.createChapter4RandomAgent(variant)
		
		return birth;
		
	}
	
	

}
