package repast.simphony.demos.sugarscape2.tests;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;
import repast.simphony.demos.sugarscape2.builders.DefaultSugarscapeBuilder_chapter4;
import repast.simphony.demos.sugarscape2.builders.SugarAgentFactory;
import repast.simphony.demos.sugarscape2.space.SugarSpace_ch4;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;

public class TestSugarSpace_ch4 {

	//static SugarSpace_ch2 context_ch2;

	@Before
	public void setUp () throws Exception {
		Schedule schedule = new Schedule ();

		DefaultParameters parms_ch4 = new DefaultParameters();
		parms_ch4.addParameter("Variant", "", Integer.class, "4", true);
		parms_ch4.addParameter("Variant", "", String.class, "p100", true);

		parms_ch4.addParameter("Vision_min", "",  Integer.class, 1, true);
		parms_ch4.addParameter("Vision_max", "",  Integer.class, 5, true);

		parms_ch4.addParameter("VisionType", "",  String.class, "Moore", true);

		parms_ch4.addParameter("Metabolism_min", "", Integer.class,2, true);
		parms_ch4.addParameter("Metabolism_max", "", Integer.class,6, true);

		parms_ch4.addParameter("Spice_Metabolism_min", "", Integer.class,2, true);
		parms_ch4.addParameter("Spice_Metabolism_max", "", Integer.class,6, true);

		parms_ch4.addParameter("InitEndownment_min", "", Integer.class, 5, true);
		parms_ch4.addParameter("InitEndownment_max", "", Integer.class, 10, true);

		parms_ch4.addParameter("Spice_InitEndownment_min", "", Integer.class, 5, true);
		parms_ch4.addParameter("Spice_InitEndownment_max", "", Integer.class, 10, true);

		parms_ch4.addParameter("maxAge_min", "", Integer.class, 60, true);
		parms_ch4.addParameter("maxAge_max", "", Integer.class, 100, true);

		parms_ch4.addParameter("tagString_length", "", Integer.class, 100, true);

		parms_ch4.addParameter("combat_reward", "", Integer.class, 9999, true);

		parms_ch4.addParameter("childbearing_age_start_min_men", "", Integer.class, 12, true);
		parms_ch4.addParameter("childbearing_age_start_max_men", "", Integer.class, 15, true);
		parms_ch4.addParameter("childbearing_age_start_min_women", "", Integer.class, 12, true);
		parms_ch4.addParameter("childbearing_age_start_max_women", "", Integer.class, 15, true);

		parms_ch4.addParameter("childbearing_age_end_min_men", "", Integer.class, 40, true);
		parms_ch4.addParameter("childbearing_age_end_max_men", "", Integer.class, 50, true);
		parms_ch4.addParameter("childbearing_age_end_min_women", "", Integer.class, 50, true);
		parms_ch4.addParameter("childbearing_age_end_max_women", "", Integer.class, 60, true);


		parms_ch4.addParameter("numberOfAgents", "", Integer.class, 1, true);		
		parms_ch4.addParameter("regenerationRate", "", Integer.class, 1, true);
		parms_ch4.addParameter("spice_regenerationRate", "", Integer.class, 1, true);

		RunEnvironment . init ( schedule , null , parms_ch4 , true );

	}


//	@Test
//	public void SettingMaxAge() {
//
//		SugarAgent_ch4 a = SugarAgentFactory.createChapter4RandomAgent("p100");
//
//		SugarSpace_ch4 sp = SugarSpaceFactory.createChapter4SugarSpace("p100", "./data/sugarspace.pgm","./data/spicespace.pgm");
//
//		RunEnvironment.getInstance().getCurrentSchedule().execute();
//
//		sp.addSugarAgent(a);
//
//		assertEquals("Agent of Age is",0,a.getAge());
//
//		IntStream.rangeClosed(1, 10).forEach(i -> RunEnvironment.getInstance().getCurrentSchedule().execute());
//
//		assertEquals("Agent of Age is",1,a.getAge());
//
//
//	}

	@Test
	public void manualSchedulinOfActions() {
		
		double cur_tick, next_period_start;
		
		DefaultSugarscapeBuilder_chapter4 builder = new DefaultSugarscapeBuilder_chapter4();
		SugarSpace_ch4 sp =  (SugarSpace_ch4) builder.build(new DefaultContext<Object>());	
		
		cur_tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		next_period_start = cur_tick + (10-(cur_tick%10));
		System.out.println("Current tick: " + cur_tick +"  / next_period_start: " + next_period_start);
		
		
			
		
		SugarAgent_ch4 a = SugarAgentFactory.createChapter4RandomAgent("p100");
		
//		SugarSpace_ch4 sp = SugarSpaceFactory.createChapter4SugarSpace("p100", "./data/sugarspace.pgm","./data/spicespace.pgm");
		sp.addSugarAgent(a);
		
		RunEnvironment.getInstance().getCurrentSchedule().execute();
		
		cur_tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();
		next_period_start = cur_tick + (10-(cur_tick%10));
		System.out.println("Current tick: " + cur_tick +"  / next_period_start: " + next_period_start);


		
	}

	//
	//
	//	@Test
	//	public void SchedulingAgentActions() {
	//
	//		SugarAgent_ch4 a = SugarAgentFactory.createChapter4RandomAgent("p100");
	//
	//		SugarSpace_ch4 sp = SugarSpaceFactory.createChapter4SugarSpace("p100", "./data/sugarspace.pgm","./data/spicespace.pgm");
	//
	//		assertEquals("Number of Behaviors before adding an Agent",0,RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	//
	//		sp.addSugarAgent(a);
	//
	//		assertEquals("Number of Behaviors after adding ONE Agent",5,RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	//
	//		sp.addSugarAgent(a);
	//
	//		assertEquals("Number of Behaviors after adding ANOTHER ONE Agent",10,RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	//
	//		RunEnvironment.getInstance().getCurrentSchedule().execute();
	//
	//		sp.removeSugarAgent(a);
	//
	//		IntStream.rangeClosed(1, 2)
	//		.forEach(i -> RunEnvironment.getInstance().getCurrentSchedule().execute());
	//
	//
	//
	//		assertEquals("Number of Behaviors after removing THE Agent",0,RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	//
	//	}

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
