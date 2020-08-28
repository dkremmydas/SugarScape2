package repast.simphony.demos.sugarscape2.builders;

import javax.management.RuntimeErrorException;

import repast.simphony.context.Context;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.death.DefaultDeath;
import repast.simphony.demos.sugarscape2.agents.rules.death.DieAbility;
import repast.simphony.demos.sugarscape2.agents.rules.death.FiniteLifeDeath;
import repast.simphony.demos.sugarscape2.agents.rules.gather.DefaultGather;
import repast.simphony.demos.sugarscape2.agents.rules.gather.GatherAbility;
import repast.simphony.demos.sugarscape2.agents.rules.growback.DefaultGrowback;
import repast.simphony.demos.sugarscape2.agents.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.DefaultMovement;
import repast.simphony.demos.sugarscape2.agents.rules.movement.KeepNetworkMovement;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.PollutionMovement;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.DefaultPollution;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.NoPollution;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.PollutionAbility;
import repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion.DefaultPollutionDiffusion;
import repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion.NoPollutionDiffusion;
import repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion.PollutionDiffusionAbility;
import repast.simphony.demos.sugarscape2.agents.rules.replacement.DefaultReplacement;
import repast.simphony.demos.sugarscape2.agents.rules.replacement.NoReplacement;
import repast.simphony.demos.sugarscape2.agents.rules.replacement.ReplacementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.DefaultVision;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.graph.Network;

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
public class DefaultSugarscapeBuilder_chapter2 implements ContextBuilder<Object>{


	private int chapter;
	private String variant; 



	@Override
	public Context<Object> build(Context<Object> context) {


		this.chapter = RunEnvironment.getInstance().getParameters().getInteger("Chapter");
		this.variant = RunEnvironment.getInstance().getParameters().getString("Variant");

		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		RandomHelper.createUniform();

		SugarSpace_ch2 agentsContext = DefaultSugarscapeBuilder_chapter2.createSugarSpace(chapter, variant, "./data/sugarspace.pgm");

		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");

		//2.2 create the agents and add them to the context and to the Grid projection
		for(int i=0;i<n;i++) {

			SugarAgent_ch2 agent = DefaultSugarscapeBuilder_chapter2.createAgent(this.chapter, this.variant, agentsContext);

			agentsContext.add(agent);			
			schedule.schedule(agent); //TODO why do we have to add the annotated methods to the schedule manually?

		}

		return agentsContext;

	}







	public static SugarAgent_ch2 createAgent(int chapter, String variant, SugarSpace_ch2 agentsContext ) {

		if(chapter==2) {

			int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
			int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
			int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision");
			
	

			if (variant.equalsIgnoreCase("p30")) {

				DieAbility da = new DefaultDeath(); da.configureFromEnvironment();
				GatherAbility ga = new DefaultGather("sugar level"); ga.configureFromEnvironment();
				MovementAbility ma = new DefaultMovement("sugar level"); ma.configureFromEnvironment();
				VisionAbility va = new DefaultVision(); va.configureFromEnvironment();
				PollutionAbility pa = new NoPollution(); pa.configureFromEnvironment();

				SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10),agentsContext)
						.withVision(RandomHelper.nextIntFromTo(1, maxVision))
						.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
						.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
						.withDieRule(da)
						.withGatherRule(ga)
						.withMovementRule(ma)
						.withVisionRule(va)
						.withPollutionRule(pa)
						.build();

				return agent;

			}
			else if (variant.equalsIgnoreCase("p37")) {

				DieAbility da = new FiniteLifeDeath(); da.configureFromEnvironment();
				GatherAbility ga = new DefaultGather("sugar level"); ga.configureFromEnvironment();
				MovementAbility ma = new DefaultMovement("sugar level"); ma.configureFromEnvironment();
				VisionAbility va = new DefaultVision(); va.configureFromEnvironment();
				PollutionAbility pa = new NoPollution(); pa.configureFromEnvironment();

				SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10),agentsContext)
						.withVision(RandomHelper.nextIntFromTo(1, maxVision))
						.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
						.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
						.withDieRule(da)
						.withGatherRule(ga)
						.withMovementRule(ma)
						.withVisionRule(va)
						.withPollutionRule(pa)
						.build();

				return agent;

			}
			else if (variant.equalsIgnoreCase("p41")) {

				DieAbility da = new DefaultDeath(); da.configureFromEnvironment();
				GatherAbility ga = new DefaultGather("sugar level"); ga.configureFromEnvironment();
				MovementAbility ma = new KeepNetworkMovement("sugar level"); ma.configureFromEnvironment();
				VisionAbility va = new DefaultVision(); va.configureFromEnvironment();
				PollutionAbility pa = new NoPollution(); pa.configureFromEnvironment();

				SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10),agentsContext)
						.withVision(RandomHelper.nextIntFromTo(1, maxVision))
						.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
						.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
						.withDieRule(da)
						.withGatherRule(ga)
						.withMovementRule(ma)
						.withVisionRule(va)
						.withPollutionRule(pa)
						.build();

				return agent;

			}
			else if (variant.equalsIgnoreCase("p50")) {

				DieAbility da = new DefaultDeath(); da.configureFromEnvironment();
				GatherAbility ga = new DefaultGather("sugar level"); ga.configureFromEnvironment();
				MovementAbility ma = new PollutionMovement("sugar level"); ma.configureFromEnvironment();
				VisionAbility va = new DefaultVision(); va.configureFromEnvironment();
				PollutionAbility pa = new DefaultPollution(); pa.configureFromEnvironment();

				SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10),agentsContext)
						.withVision(RandomHelper.nextIntFromTo(1, maxVision))
						.withSugarInitial(RandomHelper.nextIntFromTo(1, maxInitial))
						.withSugarMetabolism(RandomHelper.nextIntFromTo(1, maxMetabolism))
						.withDieRule(da)
						.withGatherRule(ga)
						.withMovementRule(ma)
						.withVisionRule(va)
						.withPollutionRule(pa)
						.build();

				return agent;

			}
		}

		throw new RuntimeErrorException(null, "The Chapter and Variant parameters provided, are not yet implemented" );
	}


	public static SugarSpace_ch2 createSugarSpace(int chapter, String variant,String pgm_file) {

		if(chapter==2) {
			if (variant.equalsIgnoreCase("p30")) {

				GrowbackAbility growbackRule = new DefaultGrowback(); growbackRule.configureFromEnvironment();

				ReplacementAbility replacementRule = new NoReplacement();

				PollutionDiffusionAbility diffusionRule = new NoPollutionDiffusion();

				return new SugarSpace_ch2(pgm_file, growbackRule , replacementRule, diffusionRule);

			}
			else if (variant.equalsIgnoreCase("p37")) {

				GrowbackAbility growbackRule = new DefaultGrowback(); growbackRule.configureFromEnvironment();

				ReplacementAbility replacementRule = new DefaultReplacement(); replacementRule.configureFromEnvironment();

				PollutionDiffusionAbility diffusionRule = new NoPollutionDiffusion();

				return new SugarSpace_ch2(pgm_file, growbackRule , replacementRule, diffusionRule);

			}
			else if (variant.equalsIgnoreCase("p41")) {

				GrowbackAbility growbackRule = new DefaultGrowback(); growbackRule.configureFromEnvironment();

				ReplacementAbility replacementRule = new NoReplacement();		

				PollutionDiffusionAbility diffusionRule = new NoPollutionDiffusion();

				SugarSpace_ch2 agentscontext = new SugarSpace_ch2(pgm_file, growbackRule , replacementRule, diffusionRule);

				NetworkBuilder<Object> netb = new NetworkBuilder<Object>("neigbours", agentscontext, true);
				Network<Object> net = netb.buildNetwork();

				return agentscontext;

			}

			else if (variant.equalsIgnoreCase("p50")) {

				GrowbackAbility growbackRule = new DefaultGrowback(); growbackRule.configureFromEnvironment();

				ReplacementAbility replacementRule = new NoReplacement();		

				PollutionDiffusionAbility diffusionRule = new DefaultPollutionDiffusion(); diffusionRule.configureFromEnvironment();

				SugarSpace_ch2 agentscontext = new SugarSpace_ch2(pgm_file, growbackRule , replacementRule, diffusionRule);

				return agentscontext;

			}

		}


		throw new RuntimeErrorException(null, "The Chapter and Variant parameters provided, are not yet implemented" );
	}

}
