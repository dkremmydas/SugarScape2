package repast.simphony.demos.sugarscape2.agents.behaviors;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.abilities.space.GrowbackAbility;
import repast.simphony.valueLayer.GridValueLayer;

public class SpaceBehavior_ch2 implements GrowbackAbility{
	
	

	@Override
	public GridValueLayer growback(SugarSpace_ch2 s) {

		return s.getSugar().getCapacity();
	}
	
	
	

}