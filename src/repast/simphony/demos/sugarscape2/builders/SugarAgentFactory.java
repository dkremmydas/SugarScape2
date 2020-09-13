package repast.simphony.demos.sugarscape2.builders;

import javax.management.RuntimeErrorException;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.agents.rules.combat.CombatAbility;
import repast.simphony.demos.sugarscape2.agents.rules.combat.DefaultCombat;
import repast.simphony.demos.sugarscape2.agents.rules.combat.NoCombat;
import repast.simphony.demos.sugarscape2.agents.rules.culture.CulturalAbility;
import repast.simphony.demos.sugarscape2.agents.rules.culture.DefaultCulture;
import repast.simphony.demos.sugarscape2.agents.rules.culture.NoCulture;
import repast.simphony.demos.sugarscape2.agents.rules.death.DefaultDeath;
import repast.simphony.demos.sugarscape2.agents.rules.death.DieAbility;
import repast.simphony.demos.sugarscape2.agents.rules.death.FiniteLifeDeath;
import repast.simphony.demos.sugarscape2.agents.rules.gather.DefaultGather;
import repast.simphony.demos.sugarscape2.agents.rules.gather.GatherAbility;
import repast.simphony.demos.sugarscape2.agents.rules.inheritance.DefaultInheritance;
import repast.simphony.demos.sugarscape2.agents.rules.inheritance.InheritanceAbility;
import repast.simphony.demos.sugarscape2.agents.rules.inheritance.NoInheritance;
import repast.simphony.demos.sugarscape2.agents.rules.movement.DefaultMovement;
import repast.simphony.demos.sugarscape2.agents.rules.movement.KeepNetworkMovement;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.PollutionMovement;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.DefaultPollution;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.NoPollution;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.PollutionAbility;
import repast.simphony.demos.sugarscape2.agents.rules.sex.CulturalSexAbility;
import repast.simphony.demos.sugarscape2.agents.rules.sex.DefaultSexAbility;
import repast.simphony.demos.sugarscape2.agents.rules.sex.SexAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.DefaultVision;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.utilities.ConfigurableFromRepastEnvironment;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;

public class SugarAgentFactory {






	public static SugarAgent_ch3 createChapter3RandomAgent(String variant ) {

		SugarAgent_ch2 agent_ch2 = SugarAgentFactory.createChapter2RandomAgent(variant);

		return SugarAgentFactory.createChapter3RandomAgent(variant, agent_ch2);
	}


	public static SugarAgent_ch3 createChapter3RandomAgent(String variant,SugarAgent_ch2 agent_ch2) {


		//get the childbreeding age range
		SugarAgent_ch3.Sex sex = SugarAgent_ch3.Sex.values()[RandomHelper.nextIntFromTo(0, SugarAgent_ch3.Sex.values().length-1)];

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

		int tagString_length = RunEnvironment.getInstance().getParameters().getInteger("tagString_length");




		//Create rules
		SexAbility sa;
		InheritanceAbility ia;
		CulturalAbility ca;
		CombatAbility cmba;


		//SexAbility
		switch(variant) {
		case "p58":
		case "p68":
			sa = new DefaultSexAbility(); 
			break;

		case "p79":
		case "p89":
			sa = new CulturalSexAbility(); 
			break;

		default:
			throw new RuntimeErrorException(null, "For Chapter 3 and Variant " + variant + ", there is no relevant SexAbility rule" );

		}


		//InheritanceAbility
		switch(variant) {
		case "p58":
		case "p79":
		case "p89":
			ia = new NoInheritance(); 
			break;

		case "p68":
			ia = new DefaultInheritance(); 
			break;

		default:
			throw new RuntimeErrorException(null, "For Chapter 3 and Variant " + variant + ", there is no relevant InheritanceAbility rule" );

		}


		//CulturalAbility
		switch(variant) {
		case "p58":
		case "p68":
			ca = new NoCulture(); 
			break;

		case "p79":
		case "p89":
			ca = new DefaultCulture(); 
			break;

		default:
			throw new RuntimeErrorException(null, "For Chapter 3 and Variant " + variant + ", there is no relevant CulturalAbility rule" );

		}


		//CombatAbility
		switch(variant) {
		case "p58":
		case "p68":
		case "p79":
			cmba = new NoCombat(); 
			break;

		
		case "p89":
			cmba = new DefaultCombat(); 
			break;

		default:
			throw new RuntimeErrorException(null, "For Chapter 3 and Variant " + variant + ", there is no relevant CulturalAbility rule" );

		}


		//Configure environmental rules
		if(sa instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) sa).configureFromEnvironment();}

		if(ia instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) ia).configureFromEnvironment();}

		if(ca instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) ca).configureFromEnvironment();}
		
		if(cmba instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) cmba).configureFromEnvironment();}



		//create random Tag
		Boolean[] tagString = new Boolean[tagString_length];
		for(int i=0; i<tagString_length; i++) {
			tagString[i]=(RandomHelper.nextIntFromTo(0, 1)==0);
		}


		//create agent
		SugarAgent_ch3 agent = new SugarAgent_ch3.Builder(agent_ch2)
				.withSex(sex)
				.withChildBearingAge(childbearing_start,childbearing_end)
				.withTag(tagString)
				.withSexAbility(sa)
				.withInheritanceAbility(ia)
				.withCulturalAbility(ca)
				.withCombatAbility(cmba)
				.build();

		return agent;


	}




	public static SugarAgent_ch2 createChapter2SpecificAgent(String variant,int sugar_metabolism,int sugar_endownment,  int vision ) {


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
		case "p58":
		case "p68":
		case "p79":
		case "p89":
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
		case "p58":
		case "p68":
		case "p79":
		case "p89":
			ga = new DefaultGather("sugar level");
			break;
		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant GatherAbility rule" );
		}


		//MovementAbility
		switch(variant) {
		case "p30":
		case "p37":
		case "p58":
		case "p68":
		case "p79":
		case "p89":
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
		case "p58":
		case "p68":
		case "p79":
		case "p89":
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
		case "p58":
		case "p68":
		case "p79":
		case "p89":
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

		SugarAgent_ch2 agent = new SugarAgent_ch2.Builder(Utility.getRandomString(20))
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


	public static SugarAgent_ch2 createChapter2RandomAgent(String variant) {

		int Vision_min = RunEnvironment.getInstance().getParameters().getInteger("Vision_min");
		int Vision_max = RunEnvironment.getInstance().getParameters().getInteger("Vision_max");

		int Metabolism_min = RunEnvironment.getInstance().getParameters().getInteger("Metabolism_min");
		int Metabolism_max = RunEnvironment.getInstance().getParameters().getInteger("Metabolism_max");

		int InitEndownment_min = RunEnvironment.getInstance().getParameters().getInteger("InitEndownment_max");
		int InitEndownment_max = RunEnvironment.getInstance().getParameters().getInteger("InitEndownment_max");




		return SugarAgentFactory.createChapter2SpecificAgent(
				variant, 
				RandomHelper.nextIntFromTo(Metabolism_min, Metabolism_max), 
				RandomHelper.nextIntFromTo(InitEndownment_min, InitEndownment_max),
				RandomHelper.nextIntFromTo(Vision_min, Vision_max)
				);		

	}






}
