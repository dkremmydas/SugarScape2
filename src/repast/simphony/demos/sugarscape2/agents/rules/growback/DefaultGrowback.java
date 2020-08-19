package repast.simphony.demos.sugarscape2.agents.rules.growback;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class DefaultGrowback implements GrowbackAbility{

	/**
	 * If <0, growback immediately to full capacity
	 */
	protected int regeneration_rate;



	/**
	 * Constructor
	 * @param regeneration_rate
	 */
	public DefaultGrowback(int regeneration_rate) {
		this.regeneration_rate = regeneration_rate;
	}
	
	public DefaultGrowback() {	}




	@Override
	public GridValueLayer growback(SugarSpace_ch2 s) {


		if(regeneration_rate<0) {
			return s.getSugar().getCapacity();
		} else {
			s.getSugar().addEverywhere(regeneration_rate);
			return s.getSugar().getHolding();
		}

	}




	@Override
	public void configureFromEnvironment() {
		regeneration_rate = RunEnvironment.getInstance().getParameters().getInteger("regenerationRate");		
	}




}