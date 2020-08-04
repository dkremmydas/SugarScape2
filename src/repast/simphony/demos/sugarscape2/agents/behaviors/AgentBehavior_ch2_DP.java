package repast.simphony.demos.sugarscape2.agents.behaviors;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

public class AgentBehavior_ch2_DP extends AgentBehavior_ch2 {
	
	int age = 1;
	int age_max;

	public AgentBehavior_ch2_DP(String valueLayerName, int age_max) {
		super(valueLayerName);
		this.age_max = age_max;
	}

	
	@Override
	public boolean shallDie(SugarAgent_ch2 a) {
		
		boolean dieFromStavation = super.shallDie(a);
		
		boolean dieFromAge = ((this.age>=age_max)?true:false);
		
		this.age++;
		
		return dieFromStavation&&dieFromAge;
		
	}


	@Override
	public int gather(SugarAgent_ch2 a, GridPoint g) {
		// TODO Auto-generated method stub
		return super.gather(a, g);
	}
	
	
	
	
	
	

}
