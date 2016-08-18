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
	 * The sugarscape growsback <br/>
	 * Before I just set sugarGridProperties.getCurrentQuantity() = {new value layer} but it did not work.
	 * I had to copy value-by-value the contents of the second valuelayer to the first
	 */
	@ScheduledMethod(start=4.2d,interval=5d)
	public void pollute() {
		//System.out.println("Before Growback: " + this.sugarGridProperties.getQuantityDescriptiveStats());
		
		ValueLayer newvl = this.pollutionRule.pollute(this);
		for(int i=0;i<this.pollution.getDimensions().getWidth();i++) {
			for(int j=0;j<this.pollution.getDimensions().getHeight();j++) {
				this.pollution.set(newvl.get(i,j), i,j);
			}
		}
	}

}
