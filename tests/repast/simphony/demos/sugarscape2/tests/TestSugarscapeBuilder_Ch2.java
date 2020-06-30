package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.builders.SugarscapeBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class TestSugarscapeBuilder_Ch2 {
	
	@Before
	public void setUp () throws Exception {
		Schedule schedule = new Schedule ();
		
		DefaultParameters parms = new DefaultParameters();
		parms.addParameter("Chapter", "Chapter", Integer.class, 2, true);
		parms.addParameter("Variant", "Variant", String.class, "p30", true);
		parms.addParameter("numberOfAgents", "numberOfAgents", Integer.class, 400, true);
		parms.addParameter("maxVision", "maxVision",  Integer.class, 5, true);
		parms.addParameter("maxMetabolism", "maxMetabolism", Integer.class,5, true);
		parms.addParameter("maxInitEndownment", "maxInitEndownment", Integer.class, 10, true);
		
		
		RunEnvironment . init ( schedule , null , parms , true );
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
	}
	
	

}
