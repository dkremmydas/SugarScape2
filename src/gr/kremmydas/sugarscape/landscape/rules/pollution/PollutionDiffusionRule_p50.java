package gr.kremmydas.sugarscape.landscape.rules.pollution;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p50;
import repast.simphony.valueLayer.GridValueLayer;

public class PollutionDiffusionRule_p50 implements PollutionDiffusionAbility {

	public PollutionDiffusionRule_p50() {}

	@Override
	public GridValueLayer diffuse(LandscapeChapter2_p50 ls) {
		return ls.getPollution();
	}

}
