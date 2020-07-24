package repast.simphony.demos.sugarscape2.agents.old;

import repast.simphony.demos.sugarscape2.agents.abilities.PollutionAbility;
import repast.simphony.demos.sugarscape2.landscape.old.LandscapeChapter2_p50;
import repast.simphony.engine.schedule.ScheduledMethod;

public class SugarAgent_ch2p50 extends SugarAgent_ch2p30 {
	
	protected PollutionAbility pollutionRule;

	/**
	 * The amount of sugar gathered in the current tick
	 */
	protected int amount_gathered = 0;
	
	/**
	 * The amount of sugar consumed in the current tick
	 */
	protected int amount_consumed = 0;
	
	public SugarAgent_ch2p50() {}
	
	@ScheduledMethod(start=4.4d,interval=5d)
	public void pollute() {
		if(isAlive){
			int cx = this.myLandscape.getGrid().getLocation(this).getX();
			int cy = this.myLandscape.getGrid().getLocation(this).getY();
			LandscapeChapter2_p50 ls = (LandscapeChapter2_p50)this.myLandscape;
			int new_pollution = this.pollutionRule.pollute(this);
			int old_pollution = (int) ls.getPollution().get(cx,cy);
			
			ls.getPollution().set((old_pollution+new_pollution), cx,cy);
		}
	}

	public int getAmountGathered() {
		return amount_gathered;
	}

	public void setAmountGathered(int amount_gathered) {
		this.amount_gathered = amount_gathered;
	}

	public int getAmountMetabolized() {
		return amount_consumed;
	}

	public void setAmountMetbolized(int amount_consumed) {
		this.amount_consumed = amount_consumed;
	}

	public PollutionAbility getPollutionRule() {
		return pollutionRule;
	}

	public void setPollutionRule(PollutionAbility pollutionRule) {
		this.pollutionRule = pollutionRule;
	}
	
}
