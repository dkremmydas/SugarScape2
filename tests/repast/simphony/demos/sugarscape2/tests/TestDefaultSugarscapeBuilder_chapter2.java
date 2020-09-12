package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.builders.DefaultSugarscapeBuilder_chapter2;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;
import repast.simphony.space.grid.GridPoint;

public class TestDefaultSugarscapeBuilder_chapter2{
	
	@Before
	public void setUp () throws Exception {
		Schedule schedule = new Schedule ();
		
		DefaultParameters parms_ch2 = new DefaultParameters();
		parms_ch2.addParameter("Variant", "Variant", String.class, "p30", true);

		parms_ch2.addParameter("Vision_min", "",  Integer.class, 1, true);
		parms_ch2.addParameter("Vision_max", "",  Integer.class, 5, true);
		
		parms_ch2.addParameter("VisionType", "",  String.class, "Moore", true);
		
		parms_ch2.addParameter("Metabolism_min", "", Integer.class,4, true);
		parms_ch2.addParameter("Metabolism_max", "", Integer.class,4, true);
		
		parms_ch2.addParameter("InitEndownment_max", "", Integer.class, 10, true);
		parms_ch2.addParameter("InitEndownment_max", "", Integer.class, 10, true);
		
		
		parms_ch2.addParameter("numberOfAgents", "numberOfAgents", Integer.class, 400, true);		
		parms_ch2.addParameter("regenerationRate", "regenerationRate", Integer.class, 1, true);
		
		RunEnvironment . init ( schedule , null , parms_ch2 , true );

	}


	@Test
	public void builder_creation() {
		DefaultSugarscapeBuilder_chapter2 builder = new DefaultSugarscapeBuilder_chapter2();
		assertNotNull(builder);
	}
	
	
	@Test
	public void builder_build() {
		DefaultSugarscapeBuilder_chapter2 builder = new DefaultSugarscapeBuilder_chapter2();
		
		SugarSpace_ch2 returned_context =  (SugarSpace_ch2) builder.build(new DefaultContext<Object>());		
					
		assertNotNull("'sugar capacity' ValueLayer has been added",returned_context.getValueLayer("sugar capacity"));
		
		assertEquals("Number of Agents", 400, returned_context.getObjects(SugarAgent_ch2.class).size());
		
		assertEquals("Number of Behaviors", 400, RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	}
	
	@Test
	public void AllAgentsPositionedInGrid() {
		
		DefaultSugarscapeBuilder_chapter2 builder = new DefaultSugarscapeBuilder_chapter2();
		SugarSpace_ch2 returned_context =  (SugarSpace_ch2) builder.build(new DefaultContext<Object>());
		
		returned_context
		.getObjects(SugarAgent_ch2.class)
		.forEach(new Consumer<Object>() {

			@Override
			public void accept(Object t) {

				GridPoint loc = ((SugarAgent_ch2)t).getCurrentPosition();
				assertNotNull(loc);
				
			}
		});
		
	}
	
	

}
