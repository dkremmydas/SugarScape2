package repast.simphony.demos.sugarscape2.agents.rules.combat;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public class NoCombat implements CombatAbility {

	@Override
	public Pair<SugarAgent_ch3, Integer> getVictim(SugarAgent_ch3 a) {
		return Pair.of(null, null);
	}

}
