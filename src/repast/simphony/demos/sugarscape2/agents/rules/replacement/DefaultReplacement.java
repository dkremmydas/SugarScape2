package repast.simphony.demos.sugarscape2.agents.rules.replacement;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.ConfigurableFromRepastEnvironment;
import repast.simphony.demos.sugarscape2.builders.SugarAgentFactory;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;

/**
 * Replace the agent with a new random agent, randomly located on the grid
 * 
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class DefaultReplacement implements ReplacementAbility, ConfigurableFromRepastEnvironment {
	
	private String simulationVariant;
	

	@Override
	public void replace(SugarSpace_ch2 space, SugarAgent_ch2 old_agent) {
		
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();

		SugarAgent_ch2 new_agent = SugarAgentFactory.createChapter2RandomAgent(this.simulationVariant);
		
		SugarSpace_ch2.getInstance().add(new_agent);	
		//new_agent.getContext().getGrid().moveTo(new_agent, old_agent.getCurrentPosition().getX(),old_agent.getCurrentPosition().getY());
		schedule.schedule(new_agent); 
		
	}

	@Override
	public void configureFromEnvironment() {
		simulationVariant = RunEnvironment.getInstance().getParameters().getString("Variant"); 		
	}

	




}
