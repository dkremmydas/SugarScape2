package repast.simphony.demos.sugarscape2.agents.rules.pollution;

import java.util.Map;

import org.apache.commons.collections15.map.HashedMap;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.space.grid.GridPoint;

public class NoPollution implements PollutionAbility {

	@Override
	public Map<GridPoint, Integer> pollute(SugarAgent_ch2 a) {
		
		return new HashedMap<GridPoint, Integer>();
		
	}

	@Override
	public void configureFromEnvironment() {}

}
