package repast.simphony.demos.sugarscape2.space.rules.pollution_diffusion;

import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.valueLayer.GridValueLayer;

public class NoPollutionDiffusion implements PollutionDiffusionAbility {

	@Override
	public GridValueLayer diffuse(SugarSpace_ch2 s) {
		GridValueLayer pollution = (GridValueLayer) s.getValueLayer("pollution");
		
		return pollution;
	}


}
