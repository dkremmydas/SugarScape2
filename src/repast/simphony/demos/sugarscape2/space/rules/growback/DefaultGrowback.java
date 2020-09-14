package repast.simphony.demos.sugarscape2.space.rules.growback;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.utilities.ConfigurableFromRepastEnvironment;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class DefaultGrowback implements GrowbackAbility, ConfigurableFromRepastEnvironment {

	/**
	 * If <0, growback immediately to full capacity
	 */
	protected int regeneration_rate;



	@Override
	public CaseInsensitiveMap<String, GridValueLayer> growback(SugarSpace_ch2 s) {

		CaseInsensitiveMap<String, GridValueLayer> r = new CaseInsensitiveMap<String, GridValueLayer>();
		
		if(regeneration_rate<0) {
			r.put("sugar", s.resourceGetCapacity("sugar"));
		} else {
			s.resourceAddEverywhere("sugar",regeneration_rate);
			r.put("sugar", s.resourceGetHolding("sugar"));
		}
		
		return r;

	}




	@Override
	public void configureFromEnvironment() {
		regeneration_rate = RunEnvironment.getInstance().getParameters().getInteger("regenerationRate");		
	}




}
