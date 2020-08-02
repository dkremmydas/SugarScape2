package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.builders.DefaultSugarscapeBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class TestSugarscapeBuilder_ch2 {
	
	@BeforeClass
	public static void setUp () throws Exception {
		Schedule schedule = new Schedule ();
		
		DefaultParameters parms_ch2_p30 = new DefaultParameters();
		parms_ch2_p30.addParameter("Chapter", "Chapter", Integer.class, 2, true);
		parms_ch2_p30.addParameter("Variant", "Variant", String.class, "p30", true);
		parms_ch2_p30.addParameter("numberOfAgents", "numberOfAgents", Integer.class, 400, true);
		parms_ch2_p30.addParameter("maxVision", "maxVision",  Integer.class, 5, true);
		parms_ch2_p30.addParameter("maxMetabolism", "maxMetabolism", Integer.class,5, true);
		parms_ch2_p30.addParameter("maxInitEndownment", "maxInitEndownment", Integer.class, 10, true);
		parms_ch2_p30.addParameter("MetabolismRule","MetabolismRule",String.class,"repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2",true);
		parms_ch2_p30.addParameter("AgentBehavior","AgentBehavior",String.class,"repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2",true);
		parms_ch2_p30.addParameter("SpaceBehavior","SpaceBehavior",String.class,"repast.simphony.demos.sugarscape2.agents.behaviors.SpaceBehavior_ch2",true);
		parms_ch2_p30.addParameter("regenerationRate", "regenerationRate", Integer.class, 3, true);
		
		RunEnvironment . init ( schedule , null , parms_ch2_p30 , true );
		Context<Object> context = new DefaultContext<Object>();
		RunState.init().setMasterContext (context);
	}


	@Test
	public void builder_creation() {
		DefaultSugarscapeBuilder builder = new DefaultSugarscapeBuilder();
		assertNotNull(builder);
	}
	
	
	@Test
	public void builder_build() {
		DefaultSugarscapeBuilder builder = new DefaultSugarscapeBuilder();
		
		SugarSpace_ch2 returned_context =  (SugarSpace_ch2) builder.build(new DefaultContext<Object>());
		
					
		assertNotNull("'sugar capacity' ValueLayer has been added",returned_context.getValueLayer("sugar capacity"));
		
		assertEquals("Number of Agents", 400, returned_context.getObjects(SugarAgent_ch2.class).size());
		
		assertEquals("Number of Behaviors", 400, RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	}
	
	

}
