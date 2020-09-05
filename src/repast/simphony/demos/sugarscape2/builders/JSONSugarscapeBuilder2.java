package repast.simphony.demos.sugarscape2.builders;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;

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

	@Override
	public Context build(Context<Object> context) {
		// TODO Auto-generated method stub
		return null;
	}

	/*

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
	
	*/
	
	

}
