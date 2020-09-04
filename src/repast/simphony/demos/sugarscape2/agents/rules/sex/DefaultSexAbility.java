package repast.simphony.demos.sugarscape2.agents.rules.sex;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.ConfigurableFromRepastEnvironment;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.space.grid.GridPoint;

public class DefaultSexAbility implements SexAbility,ConfigurableFromRepastEnvironment {


	protected Utility.TypeOfVision typeOfVision;


	@Override
	public Iterable<SugarAgent_ch3> selectPotentialMates(SugarAgent_ch3 a) {

		ArrayList<SugarAgent_ch3> r = new ArrayList<SugarAgent_ch3>();

		SugarSpace_ch2.getInstance().gridGetNeighboringSugarAgents(a)
		.forEach(new Consumer<SugarAgent_ch2>() {

			@Override
			public void accept(SugarAgent_ch2 t) {
				SugarAgent_ch3 t_ch3 = (SugarAgent_ch3) t;

				if(! a.getSex().equals(t_ch3.getSex())) {
					if(t_ch3.hasFertileAge()) {
						r.add((SugarAgent_ch3) t_ch3);
					}
				}
			}
		});

		return r;
	}

	@Override
	public Map<SugarAgent_ch3, GridPoint> giveBirth(SugarAgent_ch3 a1, SugarAgent_ch3 a2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configureFromEnvironment() {
		// TODO Auto-generated method stub

	}


}
