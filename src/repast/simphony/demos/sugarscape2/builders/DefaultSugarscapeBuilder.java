package repast.simphony.demos.sugarscape2.builders;

import javax.management.RuntimeErrorException;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.random.RandomHelper;

/**
 * The concern of this class is to create the Sugarscape {@link}Context
 * 
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class DefaultSugarscapeBuilder implements ContextBuilder<Object>{


	private int chapter;
	private String variant; 



	@Override
	public Context<Object> build(Context<Object> context) {

		
		this.chapter = RunEnvironment.getInstance().getParameters().getInteger("Chapter");
		this.variant = RunEnvironment.getInstance().getParameters().getString("Variant");

		if(this.chapter==2) {
			if (this.variant.equalsIgnoreCase("p30")) {
				return(constructChapter2_p30());				
			}
		}


		throw new RuntimeErrorException(null, "The Chapter and Variant parameters provided, are not yet implemented" );
	}



	private SugarSpace_ch2 constructChapter2_p30() {
		
		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		RandomHelper.createUniform();

				
		//1.1 create the sugarspace
		SugarSpace_ch2 agentsContext = SugarSpace_ch2.fromRunenvParameters();


		
		//2. create the agents
		
		//2.1 read user parameters on the properties of the simulation (e.g. number of agents, maximum vision, etc.)
		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");
		int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
		

		//2.2 create the agents and add them to the context and to the Grid projection
		for(int i=0;i<n;i++) {
			
			AgentBehavior_ch2 b = AgentBehavior_ch2.fromRunenvParameters("sugar level");

			SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10),agentsContext)
					.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
					.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
					.withDieRule(b)
					.withGatherRule(b)
					.withMovementRule(b)
					.withVisionRule(b)
					.build();

			agentsContext.add(agent);			
			schedule.schedule(agent); //TODO why do we have to add the annotated methods to the schedule manually?

		}

		

		//3. if everything is ok, return true
		return agentsContext;

	}


}
