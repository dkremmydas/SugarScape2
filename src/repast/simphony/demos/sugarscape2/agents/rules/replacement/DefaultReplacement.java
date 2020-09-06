package repast.simphony.demos.sugarscape2.agents.rules.replacement;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.ConfigurableFromRepastEnvironment;
import repast.simphony.demos.sugarscape2.builders.SugarAgentFactory;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.engine.environment.RunEnvironment;

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
		
		SugarAgent_ch2 new_agent = SugarAgentFactory.createChapter2RandomAgent(this.simulationVariant);
		
		 SugarSpaceFactory.getSugarspace().addSugarAgent(new_agent);	
		
	}

	@Override
	public void configureFromEnvironment() {
		simulationVariant = RunEnvironment.getInstance().getParameters().getString("Variant"); 		
	}

	




}
