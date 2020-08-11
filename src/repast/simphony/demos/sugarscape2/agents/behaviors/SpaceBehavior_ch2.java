package repast.simphony.demos.sugarscape2.agents.behaviors;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.growback.GrowbackAbility;
import repast.simphony.valueLayer.GridValueLayer;

public class SpaceBehavior_ch2 implements GrowbackAbility{
	
	
	protected int regeneration_rate;
	
	
	
	
		
	/**
	 * Constructor
	 * @param regeneration_rate
	 */
	public SpaceBehavior_ch2(int regeneration_rate) {
		super();
		this.regeneration_rate = regeneration_rate;
	}
	
	



	@Override
	public GridValueLayer growback(SugarSpace_ch2 s) {
		

		if(regeneration_rate<0) {
			return s.getSugar().getCapacity();
		} else {
			s.getSugar().addEverywhere(regeneration_rate);
			return s.getSugar().getHolding();
		}
		
	}
	
	
	
	
	
	
	

}
