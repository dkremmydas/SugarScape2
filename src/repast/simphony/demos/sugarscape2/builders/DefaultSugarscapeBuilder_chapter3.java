package repast.simphony.demos.sugarscape2.builders;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.space.SugarSpace_ch3;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

/**
 * The concern of this class is to create the Sugarscape {@link}Context
 * 
 * Two parameters are very important:
 * 
 * 
 * The idea of variant: What page we are simulating. It changes the ability implementations
 * 
 * <ul>
 * <li>Chapter: </li>
 * <li>Variant: </li>
 * </ul>
 * 
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class DefaultSugarscapeBuilder_chapter3 implements ContextBuilder<Object>{


	private String variant; 


	@Override
	public Context<Object> build(Context<Object> context) {


		this.variant = RunEnvironment.getInstance().getParameters().getString("Variant");
		RandomHelper.createUniform();

		SugarSpace_ch3 agentsContext = SugarSpaceFactory.createChapter3SugarSpace(variant, "./data/sugarspace.pgm");

		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");

		//2.2 create the agents and add them to the context and to the Grid projection
		for(int i=0;i<n;i++) {

			SugarAgent_ch3 agent = SugarAgentFactory.createChapter3RandomAgent(this.variant);

			agentsContext.addSugarAgent(agent);	

		}

		return agentsContext;

	}




	



}
