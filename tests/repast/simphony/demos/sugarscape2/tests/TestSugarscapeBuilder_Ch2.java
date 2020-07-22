package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.builders.SugarscapeBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class TestSugarscapeBuilder_Ch2 {
	
	@Before
	public void setUp () throws Exception {
		Schedule schedule = new Schedule ();
		
		DefaultParameters parms_ch2_p30 = new DefaultParameters();
		parms_ch2_p30.addParameter("Chapter", "Chapter", Integer.class, 2, true);
		parms_ch2_p30.addParameter("Variant", "Variant", String.class, "p30", true);
		parms_ch2_p30.addParameter("numberOfAgents", "numberOfAgents", Integer.class, 400, true);
		parms_ch2_p30.addParameter("maxVision", "maxVision",  Integer.class, 5, true);
		parms_ch2_p30.addParameter("maxMetabolism", "maxMetabolism", Integer.class,5, true);
		parms_ch2_p30.addParameter("maxInitEndownment", "maxInitEndownment", Integer.class, 10, true);
		
		
		RunEnvironment . init ( schedule , null , parms_ch2_p30 , true );
		Context<Object> context = new DefaultContext<Object>();
		RunState.init().setMasterContext (context);
	}


	@Test
	public void builder_creation() {
		SugarscapeBuilder builder = new SugarscapeBuilder();
		assertNotNull(builder);
	}
	
	
	@Test
	public void builder_build() {
		SugarscapeBuilder builder = new SugarscapeBuilder();
		
		DefaultContext<Object> returned_context =  (DefaultContext<Object>) builder.build(new DefaultContext<Object>());
		
		assertNotNull("'agents' subContext has been added",returned_context.getSubContext("agents"));
		
		assertNotNull("'sugar capacity' ValueLayer has been added",returned_context.getValueLayer("sugar capacity"));
		
		assertEquals(400, returned_context.getSubContext("agents").getObjects(SugarAgent_ch2.class).size());
	}
	
	

}
