package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.builders.SugarAgentFactory;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class TestSugarSpace_ch2 {

	//static SugarSpace_ch2 context_ch2;
	
	@BeforeClass
	public static void setUp () throws Exception {
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
		
//		DefaultSugarscapeBuilder_chapter2 builder = new DefaultSugarscapeBuilder_chapter2();
//		context_ch2 =  (SugarSpace_ch2) builder.build(new DefaultContext<Object>());		

	}

	
	@Test
	public void SchedulingAgentActions() {
		
		SugarAgent_ch2 a = SugarAgentFactory.createChapter2RandomAgent("p30");
		
		SugarSpace_ch2 sp = SugarSpaceFactory.createChapter2SugarSpace("p30", "./data/sugarspace.pgm");
		
		assertEquals("Number of Behaviors before adding an Agent",0,RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
		sp.addSugarAgent(a);
		
		assertEquals("Number of Behaviors after adding ONE Agent",1,RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		
		sp.removeSugarAgent(a);
		
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		
		assertEquals("Number of Behaviors after removing THE Agent",0,RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
	}
	
//	
//	@Test
//	public void removeSugarAgent() {
//		
//		//remove 1 agent. Actions are 399
//		context_ch2.removeSugarAgent(
//				(SugarAgent_ch2) context_ch2.getObjects(SugarAgent_ch2.class).get(0)
//				);
//		assertEquals("Number of Behaviors", 399, RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
//		
//	}


}
