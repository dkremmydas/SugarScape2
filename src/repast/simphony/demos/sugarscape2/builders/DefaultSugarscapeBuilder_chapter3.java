package repast.simphony.demos.sugarscape2.builders;

import javax.management.RuntimeErrorException;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.sex.DefaultSexAbility;
import repast.simphony.demos.sugarscape2.agents.rules.sex.SexAbility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.random.RandomHelper;

/**
 * The concern of this class is to create the Sugarscape {@link}Context
 * 
 * Two parameters are very important:
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


	private String variant_ch2; 
	private String variant_ch3;


	@Override
	public Context<Object> build(Context<Object> context) {


		this.variant_ch2 = RunEnvironment.getInstance().getParameters().getString("Variant_chapter2");
		this.variant_ch3 = RunEnvironment.getInstance().getParameters().getString("Variant_chapter3");

		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		RandomHelper.createUniform();

		SugarSpace_ch2 agentsContext = DefaultSugarscapeBuilder_chapter2.createSugarSpace(variant_ch2, "./data/sugarspace.pgm");

		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");

		//2.2 create the agents and add them to the context and to the Grid projection
		for(int i=0;i<n;i++) {

			SugarAgent_ch3 agent = DefaultSugarscapeBuilder_chapter3.createRandomAgent(this.variant_ch2, this.variant_ch3);

			agentsContext.add(agent);			
			schedule.schedule(agent); //TODO why do we have to add the annotated methods to the schedule manually?

		}

		return agentsContext;

	}




	public static SugarAgent_ch3 createRandomAgentFromSugarAgent_ch2(String variant_ch2, String variant_ch3,SugarAgent_ch2 agent_ch2) {
		
		if (variant_ch3.equalsIgnoreCase("p58")) {

			SugarAgent_ch3.Sex sex = SugarAgent_ch3.Sex.values()[RandomHelper.nextIntFromTo(0, SugarAgent_ch3.Sex.values().length-1)];

			SexAbility sexRule = new DefaultSexAbility(); 
			
			//get the childbreeding age range
			int childbearing_start,childbearing_end;
			
			if(sex.equals(SugarAgent_ch3.Sex.MALE)) {
				childbearing_start = RandomHelper.nextIntFromTo(
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_start_min_men"), 
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_start_max_men")
						);
				childbearing_end = RandomHelper.nextIntFromTo(
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_end_min_men"), 
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_end_max_men")
						);
			} else {
				childbearing_start = RandomHelper.nextIntFromTo(
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_start_min_women"), 
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_start_max_women")
						);
				childbearing_end = RandomHelper.nextIntFromTo(
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_end_min_women"), 
						RunEnvironment.getInstance().getParameters().getInteger("childbearing_age_end_max_women")
						);
			}


			SugarAgent_ch3 agent = new SugarAgent_ch3.Builder(agent_ch2)
					.withSex(sex)
					.withChildBearingAge(childbearing_start,childbearing_end)
					.withSexRule(sexRule)
					.build();

			return agent;

		}


		throw new RuntimeErrorException(null, "The Variant parameters provided, are not yet implemented" );
	}



	public static SugarAgent_ch3 createRandomAgent(String variant_ch2, String variant_ch3 ) {

		SugarAgent_ch2 agent_ch2 = DefaultSugarscapeBuilder_chapter2.createRandomAgent(variant_ch2);

		return DefaultSugarscapeBuilder_chapter3.createRandomAgentFromSugarAgent_ch2(variant_ch2, variant_ch3, agent_ch2);
	}




}
