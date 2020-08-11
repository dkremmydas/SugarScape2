package repast.simphony.demos.sugarscape2.builders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.demos.sugarscape2.agents.SpaceResource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2;
import repast.simphony.demos.sugarscape2.agents.behaviors.SpaceBehavior_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.growback.GrowbackAbility;
import repast.simphony.demos.sugarscape2.utilities.PGMReader;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedule;
import repast.simphony.random.RandomHelper;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The concern of this class is to create the Sugarscape {@link}Context based on a JSON definition
 * 
 * The JSON file to read is define in <PARAMETER>
 * 
 * The JSON template is as follows:
 * 
 * 
 * 
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class JSONSugarscapeBuilder2 implements ContextBuilder<Object>{


	JSONParser jsonParser = new JSONParser();


	JSONObject json_sugarcape;
	JSONArray json_agents;
	
	String class_prefix = "repast.simphony.demos.sugarscape2";



	public JSONSugarscapeBuilder2() {


		try (FileReader reader = new FileReader( RunEnvironment.getInstance().getParameters().getString("JSON file") )) {
			// Read JSON file
			JSONObject obj = (JSONObject) jsonParser.parse(reader);

			json_sugarcape = (JSONObject) obj.get("sugarascape");
			json_agents = (JSONArray) obj.get("agents");


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}



	@Override
	public Context<Object> build(Context<Object> context) {

		ISchedule schedule = RunEnvironment.getInstance().getCurrentSchedule();


		//1. create the SugarSpace
		SugarSpace_ch2 agentsContext = SugarSpace_ch2.fromJSON(json_sugarcape.toJSONString());



		//2. create the agents

		//2.1 read user parameters on the properties of the simulation (e.g. number of agents, maximum vision, etc.)
		int n=RunEnvironment.getInstance().getParameters().getInteger("numberOfAgents");
		RandomHelper.createUniform();

		int maxVision = RunEnvironment.getInstance().getParameters().getInteger("maxVision"); 
		int maxMetabolism = RunEnvironment.getInstance().getParameters().getInteger("maxMetabolism");
		int maxInitial = RunEnvironment.getInstance().getParameters().getInteger("maxInitEndownment");
		

		//2.2 create the agents and add them to the context and to the Grid projection
		for(int i=0;i<n;i++) {
			
			AgentBehavior_ch2 b = new AgentBehavior_ch2("String", RandomHelper.nextIntFromTo(1, maxVision));

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
