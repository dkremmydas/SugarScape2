package repast.simphony.demos.sugarscape2.agents.rules.gather;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public class DefaultGatherSugarSpice extends DefaultGather {

	int spice_gathered_last_time = 0;


	@Override
	public CaseInsensitiveMap<String, Integer> gather(SugarAgent_ch2 a, GridPoint g) {

		CaseInsensitiveMap<String, Integer> r = super.gather(a, g);    //sugar

		GridValueLayer gvl = (GridValueLayer)  SugarSpaceFactory.getSugarspace().getValueLayer("spice level");

		spice_gathered_last_time = (int) gvl.get(g.getX(),g.getY());

		r.put("spice", spice_gathered_last_time);


		return r;
	}

	@Override
	public CaseInsensitiveMap<String, Integer> getAmountGathered(SugarAgent_ch2 a) {

		CaseInsensitiveMap<String, Integer> r = super.getAmountGathered(a);

		r.put("spice", spice_gathered_last_time);

		return r;
	}





}
