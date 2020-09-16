package repast.simphony.demos.sugarscape2.agents.rules.sex;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.space.grid.GridPoint;

public class NoSexAbility implements SexAbility {

	@Override
	public Iterable<SugarAgent_ch3> selectPotentialMates(SugarAgent_ch3 a) {

		return new HashSet<SugarAgent_ch3>();

	}

	@Override
	public Pair<SugarAgent_ch3, GridPoint> giveBirth(SugarAgent_ch3 a1, SugarAgent_ch3 a2) {

		return Pair.of(null, null);
	}

	@Override
	public Set<SugarAgent_ch3> getChildren(SugarAgent_ch3 a) {
		
		return new HashSet<SugarAgent_ch3>();
		
	}

}
