package repast.simphony.demos.sugarscape2.landscape.old;

import repast.simphony.demos.sugarscape2.SimulationContext;
import repast.simphony.demos.sugarscape2.landscape.old.rules.pollution.PollutionDiffusionAbility;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.valueLayer.GridValueLayer;
import repast.simphony.valueLayer.ValueLayer;

public class LandscapeChapter2_p50 extends LandscapeChapter2_p30 {
	
	protected GridValueLayer pollution;
	
	protected PollutionDiffusionAbility pollutionDiffusionRule;
	
	protected int pollutionDiffusionPeriod;

	public LandscapeChapter2_p50() {
		super();		
	}

	@Override
	public void init(int x, int y) {
		super.init(x, y);
		this.pollution = new GridValueLayer("pollution", true, x,y);
		SimulationContext.getInstance().addValueLayer(this.pollution);
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

	public void setPollutionDiffusionRule(PollutionDiffusionAbility pollutionDiffusionRule) {
		this.pollutionDiffusionRule = pollutionDiffusionRule;
	}

	public int getPollutionDiffusionPeriod() {
		return pollutionDiffusionPeriod;
	}

	public void setPollutionDiffusionPeriod(int pollutionDiffusionPeriod) {
		this.pollutionDiffusionPeriod = pollutionDiffusionPeriod;
	}

	/**
	 * The sugarscape pollution diffusion (D) rule on p. 48 
	 */
	@ScheduledMethod(start=4.5d,interval=5d)
	public void diffusePollution() {
		if((SimulationContext.getInstance().getRealTick()%this.pollutionDiffusionPeriod)==0) {
			ValueLayer newvl = this.pollutionDiffusionRule.diffuse(this);
			for(int i=0;i<this.pollution.getDimensions().getWidth();i++) {
				for(int j=0;j<this.pollution.getDimensions().getHeight();j++) {
					this.pollution.set(newvl.get(i,j), i,j);
				}
			}
		}
	}

}
