package repast.simphony.demos.sugarscape2.agents;

import repast.simphony.demos.sugarscape2.agents.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion.PollutionDiffusionAbility;
import repast.simphony.demos.sugarscape2.agents.rules.replacement.ReplacementAbility;
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

		ISchedulableAction ac_S = RunEnvironment.getInstance().getCurrentSchedule().schedule(
				ScheduleParameters.createRepeating(cur_tick+1+3, 10d), 
				a, 
				"applyRuleS"
				);

		actions.put(a.getId(), ac_S);
		
		
		ISchedulableAction ac_K = RunEnvironment.getInstance().getCurrentSchedule().schedule(
				ScheduleParameters.createRepeating(cur_tick+1+5, 10d), 
				a, 
				"applyRuleK"
				);

		actions.put(a.getId(), ac_K);
		
		
		ISchedulableAction ac_C = RunEnvironment.getInstance().getCurrentSchedule().schedule(
				ScheduleParameters.createRepeating(cur_tick+1+6, 10d), 
				a, 
				"applyRuleC"
				);

		actions.put(a.getId(), ac_C);
		
		
		
	}



	public void removeSugarAgent(SugarAgent_ch3 a) {		
		super.removeSugarAgent(a);
	}

	
	




}
