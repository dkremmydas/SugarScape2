package repast.simphony.demos.sugarscape2.agents.rules.pollution;

import java.util.Map;

import org.apache.commons.collections15.map.HashedMap;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.utilities.ConfigurableFromRepastEnvironment;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.GridPoint;

public class DefaultPollution implements PollutionAbility, ConfigurableFromRepastEnvironment {

	private int alpha;
	private int beta;
	
	
	


	@Override
	public Map<GridPoint, Integer> pollute(SugarAgent_ch2 a) {

		HashedMap<GridPoint, Integer> r = new HashedMap<GridPoint, Integer>();

		//int pollution = alpha*owner.getAmountGathered() + beta*owner.getAmountMetabolized();
		int pollution = alpha*a.getGatherRule().getAmountGathered(a).get("sugar") + beta*a.resourceGetMetabolism("sugar");

		r.put(a.getCurrentPosition(), pollution);

		return r;		

	}



	@Override
	public void configureFromEnvironment() {

		alpha = RunEnvironment.getInstance().getParameters().getInteger("pollutionAlpha");
		beta = RunEnvironment.getInstance().getParameters().getInteger("pollutionBeta");

	}




}
