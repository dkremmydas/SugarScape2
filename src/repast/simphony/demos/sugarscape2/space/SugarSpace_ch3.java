package repast.simphony.demos.sugarscape2.space;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.space.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.space.rules.pollution_diffusion.PollutionDiffusionAbility;
import repast.simphony.demos.sugarscape2.space.rules.replacement.ReplacementAbility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedulableAction;
import repast.simphony.engine.schedule.ScheduleParameters;

public class SugarSpace_ch3 extends SugarSpace_ch2 {



	public SugarSpace_ch3(String pgm_file,GrowbackAbility growbackRule,ReplacementAbility replacementRule,PollutionDiffusionAbility diffusionRule) {
		super(pgm_file, growbackRule, replacementRule, diffusionRule);
	}

	



	// Behavior
	//****************************************************************************************************************************************************


	public void addSugarAgent(SugarAgent_ch3 a) {

		super.addSugarAgent((SugarAgent_ch2)a);

		double cur_tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		
		double next_period_start = cur_tick + (10-(cur_tick%10));
		

		ISchedulableAction ac_S = RunEnvironment.getInstance().getCurrentSchedule().schedule(
				ScheduleParameters.createRepeating(next_period_start+3, 10d), 
				a, 
				"applyRuleS"
				);

		actions.put(a.getId(), ac_S);
		
		
		ISchedulableAction ac_K = RunEnvironment.getInstance().getCurrentSchedule().schedule(
				ScheduleParameters.createRepeating(next_period_start+4, 10d), 
				a, 
				"applyRuleK"
				);

		actions.put(a.getId(), ac_K);
		
		
		ISchedulableAction ac_C = RunEnvironment.getInstance().getCurrentSchedule().schedule(
				ScheduleParameters.createRepeating(next_period_start+5, 10d), 
				a, 
				"applyRuleC"
				);

		actions.put(a.getId(), ac_C);
		
		
		
	}



	public void removeSugarAgent(SugarAgent_ch3 a) {		
		super.removeSugarAgent(a);
	}

	
	




}
