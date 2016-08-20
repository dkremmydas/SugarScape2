package gr.kremmydas.sugarscape.landscape.rules.pollution;

import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p50;
import repast.simphony.valueLayer.GridValueLayer;

public interface PollutionDiffusionAbility {
	
	/**
	 * Returns the new amount of pollution as a {@link GridValueLayer} after diffusion
	 * @param ls
	 * @return
	 */
	public GridValueLayer diffuse(LandscapeChapter2_p50 ls);

}
