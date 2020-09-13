package repast.simphony.demos.sugarscape2.builders;

import javax.management.RuntimeErrorException;

import repast.simphony.context.space.graph.NetworkBuilder;
import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.space.SugarSpace_ch3;
import repast.simphony.demos.sugarscape2.space.rules.growback.DefaultGrowback;
import repast.simphony.demos.sugarscape2.space.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.space.rules.pollution_diffusion.DefaultPollutionDiffusion;
import repast.simphony.demos.sugarscape2.space.rules.pollution_diffusion.NoPollutionDiffusion;
import repast.simphony.demos.sugarscape2.space.rules.pollution_diffusion.PollutionDiffusionAbility;
import repast.simphony.demos.sugarscape2.space.rules.replacement.DefaultReplacement;
import repast.simphony.demos.sugarscape2.space.rules.replacement.NoReplacement;
import repast.simphony.demos.sugarscape2.space.rules.replacement.ReplacementAbility;
import repast.simphony.demos.sugarscape2.utilities.ConfigurableFromRepastEnvironment;
import repast.simphony.valueLayer.GridValueLayer;

public class SugarSpaceFactory {


	// Holds the singleton instance of the class. It is initialized to null.
	private static SugarSpace_ch2 single_instance = null;


	/**
	 * <p>We need to enforce that only one SugarSpace object will be created. We are using the Singleton design pattern.
	 * This method is the only valid method to access this single object. Since it is static, one has to call 
	 * <i>SugarSpace_ch2.getInstance()</i> to get this object.</p>
	 * 
	 * <p>Before calling this method, you have to create the singleton object by calling 
	 * {@link #createInstance(String, GrowbackAbility, ReplacementAbility, PollutionDiffusionAbility) #createInstance} 
	 * . If this has not been done, a {@link RuntimeException} is thrown. This is</p>
	 * 
	 * @return the instantiated Sugarspace object. If it has not been created yet, it throws a RuntimeException
	 * @see SugarSpace_ch2#createInstance(String, GrowbackAbility, ReplacementAbility, PollutionDiffusionAbility) createInstance
	 */
	public static SugarSpace_ch2 getSugarspace()  {

		if(!(SugarSpaceFactory.single_instance==null)) {
			return SugarSpaceFactory.single_instance;
		} else {
			throw new RuntimeException("SugarSpace has not been instantiated yet.");
		}


	}



	public static SugarSpace_ch2 createChapter2SugarSpace(String variant,String pgm_file) {


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
		SugarSpace_ch2 s = new SugarSpace_ch2(pgm_file, growbackRule, replacementRule, diffusionRule);


		//create other things, if necessary
		switch(variant) {
		case "p41":
			//add the Network object
			NetworkBuilder<Object> netb = new NetworkBuilder<Object>("neigbours", s, true);
			netb.buildNetwork();
			break;

		}

		SugarSpaceFactory.single_instance = s;
		return s;

	}



	public static SugarSpace_ch3 createChapter3SugarSpace(String variant,String pgm_file) {


		GrowbackAbility growbackRule;
		ReplacementAbility replacementRule ;
		PollutionDiffusionAbility diffusionRule;


		//Growback rule
		switch(variant) {
		case "p58":
		case "p68":
		case "p79":
		case "p89":
			growbackRule = new DefaultGrowback(); 
			break;

		default:
			throw new RuntimeErrorException(null, "For Chapter 3 and Variant " + variant + ", there is no relevant Growback rule" );
		}


		//Replacement rule
		switch(variant) {
		case "p58":
		case "p68":
		case "p79":
		case "p89":
			replacementRule = new NoReplacement();
			break;

		default:
			throw new RuntimeErrorException(null, "For Chapter 3 and Variant " + variant + ", there is no relevant Replacement rule" );
		}


		//Pollution diffusion rule
		switch(variant) {
		case "p58":
		case "p68":
		case "p79":
		case "p89":
			diffusionRule = new NoPollutionDiffusion();
			break;

		default:
			throw new RuntimeErrorException(null, "For Chapter 2 and Variant " + variant + ", there is no relevant Pollution diffusion rule" );
		}


		//If they follow the ConfigurableFromRepastEnvironment interface, configure rules from environment
		if(growbackRule instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) growbackRule).configureFromEnvironment();}

		if(replacementRule instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) replacementRule).configureFromEnvironment();}

		if(diffusionRule instanceof ConfigurableFromRepastEnvironment) {((ConfigurableFromRepastEnvironment) diffusionRule).configureFromEnvironment();}



		//create the Sugarspace agent
		SugarSpace_ch3 s = new SugarSpace_ch3(pgm_file, growbackRule, replacementRule, diffusionRule);


		//create other things, if necessary
		switch(variant) {
		case "p58":
			//TODO children NET
			break;
		case "p89":
			GridValueLayer combat_frequency = new  GridValueLayer("combat frequency", true, s.gridGetWidth(),s.gridGetHeight());
			s.addValueLayer(combat_frequency);
			break;
		}

		SugarSpaceFactory.single_instance = s;
		return s;

	}




}
