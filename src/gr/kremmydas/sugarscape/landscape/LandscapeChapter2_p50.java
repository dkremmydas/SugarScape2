package gr.kremmydas.sugarscape.landscape;

import gr.kremmydas.sugarscape.landscape.rules.pollution.PollutionDiffusionAbility;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;

public class LandscapeChapter2_p50 extends LandscapeChapter2_p30 {
	
	protected GridValueLayer pollution;
	
	protected PollutionDiffusionAbility pollutionDiffusionRule;

	public LandscapeChapter2_p50(int x, int y) {
		super(x, y);
		this.pollution = new GridValueLayer("pollution", true, x,y);
	}

	public GridValueLayer getPollution() {
		return pollution;
	}

	public void setPollution(GridValueLayer pollution) {
		this.pollution = pollution;
	}

	public PollutionDiffusionAbility getPollutionDiffusionRule() {
		return pollutionDiffusionRule;
	}

	public void setPollutionRule(PollutionDiffusionAbility pollutionDiffusionRule) {
		this.pollutionDiffusionRule = pollutionDiffusionRule;
	}
	
	/**
	 * The sugarscape pollution diffusion (D) rule on p. 48 
	 */
	@ScheduledMethod(start=4.5d,interval=5d)
	public void diffusePollution() {
		
		ValueLayer newvl = this.pollutionDiffusionRule.diffuse(this);
		for(int i=0;i<this.pollution.getDimensions().getWidth();i++) {
			for(int j=0;j<this.pollution.getDimensions().getHeight();j++) {
				this.pollution.set(newvl.get(i,j), i,j);
			}
		}
	}

}
