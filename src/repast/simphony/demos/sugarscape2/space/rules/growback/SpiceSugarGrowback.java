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
public class SpiceSugarGrowback implements GrowbackAbility, ConfigurableFromRepastEnvironment {

	/**
	 * If <0, growback immediately to full capacity
	 */
	protected int sugar_regeneration_rate;
	
	protected int spice_regeneration_rate;



	@Override
	public CaseInsensitiveMap<String, GridValueLayer> growback(SugarSpace_ch2 s) {
		
		CaseInsensitiveMap<String, GridValueLayer> r = new CaseInsensitiveMap<String, GridValueLayer>();

		//sugar
		if(sugar_regeneration_rate<0) {
			r.put("sugar", s.resourceGetCapacity("sugar"));
		} else {
			s.resourceAddEverywhere("sugar",sugar_regeneration_rate);
			r.put("sugar", s.resourceGetHolding("sugar"));
		}
		
		//spice
		if(spice_regeneration_rate<0) {
			r.put("spice", s.resourceGetCapacity("spice"));
		} else {
			s.resourceAddEverywhere("spice",spice_regeneration_rate);
			r.put("spice", s.resourceGetHolding("spice"));
		}
		
		return r;

	}




	@Override
	public void configureFromEnvironment() {
		sugar_regeneration_rate = RunEnvironment.getInstance().getParameters().getInteger("regenerationRate");	
		spice_regeneration_rate = RunEnvironment.getInstance().getParameters().getInteger("spice_regenerationRate");	
	}




}
