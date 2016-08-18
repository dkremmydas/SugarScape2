package gr.kremmydas.sugarscape.landscape.rules.pollution;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p50;
import repast.simphony.valueLayer.GridValueLayer;

public interface PollutionAbility {
	
	/**
	 * Returns the new amount of pollution as a {@link GridValueLayer}
	 * @param ls
	 * @return  {@link GridValueLayer}
	 */
	public GridValueLayer pollute(LandscapeChapter2_p50 ls);
}
