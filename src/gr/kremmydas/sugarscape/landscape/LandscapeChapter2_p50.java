package gr.kremmydas.sugarscape.landscape;

import gr.kremmydas.sugarscape.landscape.rules.pollution.PollutionAbility;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;

public class LandscapeChapter2_p50 extends LandscapeChapter2_p30 {
	
	protected GridValueLayer pollution;
	
	protected PollutionAbility pollutionRule;

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

	public PollutionAbility getPollutionRule() {
		return pollutionRule;
	}

	public void setPollutionRule(PollutionAbility pollutionRule) {
		this.pollutionRule = pollutionRule;
	}
	
	/**
	 * The sugarscape pollution rule on p. 47 <br/
	 */
	@ScheduledMethod(start=4.2d,interval=5d)
	public void pollute() {
		
		ValueLayer newvl = this.pollutionRule.pollute(this);
		for(int i=0;i<this.pollution.getDimensions().getWidth();i++) {
			for(int j=0;j<this.pollution.getDimensions().getHeight();j++) {
				this.pollution.set(newvl.get(i,j), i,j);
			}
		}
	}

}
