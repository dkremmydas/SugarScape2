package repast.simphony.demos.sugarscape2.builders;

import javax.management.RuntimeErrorException;

import repast.simphony.context.Context;
import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.ConfigurableFromRepastEnvironment;
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


	private String variant; 


	@Override
	public Context<Object> build(Context<Object> context) {


		this.variant = RunEnvironment.getInstance().getParameters().getString("Variant");

		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();
		RandomHelper.createUniform();

		SugarSpace_ch2 agentsContext = DefaultSugarscapeBuilder_chapter2.createSugarSpace(variant, "./data/sugarspace.pgm");

		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");

		//2.2 create the agents and add them to the context and to the Grid projection
		for(int i=0;i<n;i++) {

			SugarAgent_ch2 agent = DefaultSugarscapeBuilder_chapter2.createRandomAgent(this.variant);

			agentsContext.add(agent);			
			schedule.schedule(agent); //TODO why do we have to add the annotated methods to the schedule manually?

		}

		return agentsContext;

	}



    public static SugarAgent_ch2 createRandomAgent(String variant) {

		int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision");


		return DefaultSugarscapeBuilder_chapter2.createSpecificAgent(
				variant, 
				RandomHelper.nextIntFromTo(1, maxMetabolism), 
				RandomHelper.nextIntFromTo(1, maxInitial),
				RandomHelper.nextIntFromTo(1, maxVision)
				);		

	}


	public static SugarAgent_ch2 createSpecificAgent(String variant,int sugar_metabolism,int sugar_endownment,  int vision ) {


		//Configure Rules
		DieAbility da;
		GatherAbility ga;
		MovementAbility ma;
		VisionAbility va;
		PollutionAbility pa;


		//DieAbility
		switch(variant) {
		case "p30":
		case "p41":
		case "p50":
			da = new DefaultDeath();
			break;
		case "p37":
			da = new FiniteLifeDeath();
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant DieAbility rule" );
		}


		//GatherAbility
		switch(variant) {
		case "p30":
		case "p37":
		case "p41":
		case "p50":
			ga = new DefaultGather("sugar level");
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant GatherAbility rule" );
		}


		//MovementAbility
		switch(variant) {
		case "p30":
		case "p37":
			ma = new DefaultMovement("sugar level");
			break;
		case "p41":
			ma = new KeepNetworkMovement("sugar level");
			break;
		case "p50":
			ma = new PollutionMovement("sugar level");
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant MovementAbility rule" );
		}


		//VisionAbility
		switch(variant) {
		case "p30":
		case "p37":
		case "p41":
		case "p50":
			va = new DefaultVision();
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant VisionAbility rule" );
		}


		//PollutionAbility
		switch(variant) {
		case "p30":
		case "p37":
		case "p41":
			pa = new NoPollution();
			break;
		case "p50":
			pa = new DefaultPollution();
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant Pollution Ability rule" );
		}


		//If they follow the ConfigurableFromRepastEnvironment interface, configure rules from environment

		if(da instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) da).configureFromEnvironment();}

		if(ga instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) ga).configureFromEnvironment();}

		if(ma instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) ma).configureFromEnvironment();}

		if(va instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) va).configureFromEnvironment();}

		if(pa instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) pa).configureFromEnvironment();}



		//create the agent and return it

		SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(10))
				.withVision(vision)
				.withSugarInitial(sugar_endownment)
				.withSugarMetabolism(sugar_metabolism)
				.withDieRule(da)
				.withGatherRule(ga)
				.withMovementRule(ma)
				.withVisionRule(va)
				.withPollutionRule(pa)
				.build();

		return agent;

	}


	public static SugarSpace_ch2 createSugarSpace(String variant,String pgm_file) {


		GrowbackAbility growbackRule;
		ReplacementAbility replacementRule ;
		PollutionDiffusionAbility diffusionRule;


		//Growback rule
		switch(variant) {
		case "p30":
		case "p37":
		case "p41":
		case "p50":
			growbackRule = new DefaultGrowback(); 
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant Growback rule" );
		}


		//Replacement rule
		switch(variant) {
		case "p30":
		case "p41":
		case "p50":
			replacementRule = new NoReplacement();
			break;
		case "p37":
			replacementRule = new DefaultReplacement();	
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant Replacement rule" );
		}


		//Pollution diffusion rule
		switch(variant) {
		case "p30":
		case "p41":
		case "p37":
			diffusionRule = new NoPollutionDiffusion();
			break;
		case "p50":
			diffusionRule = new DefaultPollutionDiffusion(); 
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant Pollution diffusion rule" );
		}


		//If they follow the ConfigurableFromRepastEnvironment interface, configure rules from environment
		if(growbackRule instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) growbackRule).configureFromEnvironment();}

		if(replacementRule instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) replacementRule).configureFromEnvironment();}

		if(diffusionRule instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) diffusionRule).configureFromEnvironment();}



		//create the Sugarspace agent
		SugarSpace_ch2 s = SugarSpace_ch2.createInstance(pgm_file, growbackRule, replacementRule, diffusionRule);


		//create other things, if necessary
		switch(variant) {
		case "p41":
			//add the Network object
			NetworkBuilder<Object> netb = new NetworkBuilder<Object>("neigbours", s, true);
			netb.buildNetwork();
			break;

		}


		return s;

	}

}
