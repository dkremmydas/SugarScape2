package repast.simphony.demos.sugarscape2.landscape.rules.pollution;

import repast.simphony.demos.sugarscape2.landscape.LandscapeChapter2_p50;
import repast.simphony.valueLayer.GridValueLayer;

public interface PollutionDiffusionAbility {
	
	/**
	 * Returns the new amount of pollution as a {@link GridValueLayer} after diffusion
	 * @param ls
	 * @return
	 */
	public GridValueLayer diffuse(LandscapeChapter2_p50 ls);

}
