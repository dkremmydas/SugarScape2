package gr.kremmydas.sugarscape.agents;

import repast.simphony.engine.schedule.ScheduledMethod;
import gr.kremmydas.sugarscape.agents.rules.pollution.PollutionAbility;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p50;

public class AgentChapter2_p50 extends AgentChapter2_p30 {
	
	protected PollutionAbility pollutionRule;

	/**
	 * The amount of sugar gathered in the current tick
	 */
	protected int amount_gathered = 0;
	
	/**
	 * The amount of sugar consumed in the current tick
	 */
	protected int amount_consumed = 0;
	
	public AgentChapter2_p50() {}
	
	@ScheduledMethod(start=4.4d,interval=5d)
	public void pollute() {
		int cx = this.myLandscape.getGrid().getLocation(this).getX();
		int cy = this.myLandscape.getGrid().getLocation(this).getY();
		LandscapeChapter2_p50 ls = (LandscapeChapter2_p50)this.myLandscape;
		int new_pollution = this.pollutionRule.pollute(this);
		int old_pollution = (int) ls.getPollution().get(cx,cy);
		
		ls.getPollution().set((old_pollution+new_pollution), cx,cy);
	}

	public int getAmount_gathered() {
		return amount_gathered;
	}

	public void setAmount_gathered(int amount_gathered) {
		this.amount_gathered = amount_gathered;
	}

	public int getAmount_consumed() {
		return amount_consumed;
	}

	public void setAmount_consumed(int amount_consumed) {
		this.amount_consumed = amount_consumed;
	}

	public PollutionAbility getPollutionRule() {
		return pollutionRule;
	}

	public void setPollutionRule(PollutionAbility pollutionRule) {
		this.pollutionRule = pollutionRule;
	}
	
}
