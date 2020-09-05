package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Iterables;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.builders.DefaultSugarscapeBuilder_chapter3;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;
import repast.simphony.query.space.grid.GridWithin;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

public class TestDefaultSexAbility {

	SugarSpace_ch2 context;

	static DefaultParameters parms_ch3_p58 = new DefaultParameters();

	@BeforeClass
	public static void setUpParameters() {

		parms_ch3_p58.addParameter("Chapter", "Chapter", Integer.class, 3, true);
		parms_ch3_p58.addParameter("Variant", "Variant", String.class, "p58", true);

		parms_ch3_p58.addParameter("maxVision", "",  Integer.class, 5, true);
		parms_ch3_p58.addParameter("VisionType", "",  String.class, "Moore", true);
		parms_ch3_p58.addParameter("maxMetabolism", "", Integer.class,4, true);
		parms_ch3_p58.addParameter("maxInitEndownment", "", Integer.class, 10, true);
		parms_ch3_p58.addParameter("maxAge", "", Integer.class, 100, true);

		parms_ch3_p58.addParameter("childbearing_age_start_min_men", "childbearing_age_start_min_men", Integer.class, 12, true);
		parms_ch3_p58.addParameter("childbearing_age_start_max_men", "", Integer.class, 15, true);
		parms_ch3_p58.addParameter("childbearing_age_start_min_women", "", Integer.class, 12, true);
		parms_ch3_p58.addParameter("childbearing_age_start_max_women", "", Integer.class, 15, true);

		parms_ch3_p58.addParameter("childbearing_age_end_min_men", "", Integer.class, 40, true);
		parms_ch3_p58.addParameter("childbearing_age_end_max_men", "", Integer.class, 50, true);
		parms_ch3_p58.addParameter("childbearing_age_end_min_women", "", Integer.class, 40, true);
		parms_ch3_p58.addParameter("childbearing_age_end_max_women", "", Integer.class, 50, true);


		parms_ch3_p58.addParameter("numberOfAgents", "numberOfAgents", Integer.class, 800, true);		
		parms_ch3_p58.addParameter("regenerationRate", "regenerationRate", Integer.class, 1, true);
	}

	@Before
	public void setUpContext() throws Exception {
		Schedule schedule = new Schedule ();

		RunEnvironment . init ( schedule , null , parms_ch3_p58 , true );
		Context<Object> context = new DefaultContext<Object>();
		RunState.init().setMasterContext (context);

		DefaultSugarscapeBuilder_chapter3 builder = new DefaultSugarscapeBuilder_chapter3();
		this.context =  (SugarSpace_ch2) builder.build(new DefaultContext<Object>());
	}


	@Test
	public void testSelectPotentialMates() {
		assertNotNull(this.context);
		assertEquals("800 agents created", context.getObjects(SugarAgent_ch3.class).size(),800);

		SugarAgent_ch3 a = (SugarAgent_ch3) context.getObjects(SugarAgent_ch3.class).get(0);
		System.out.println(a);

		Iterable<GridPoint> seen = a.getVisionRule().seeEmpty(a);
		System.out.println("DEBUG: " + a.getId() + ", seen="+seen);
		
		@SuppressWarnings("unchecked")
		Grid<Object> grid = (Grid<Object>) SugarSpace_ch2.getInstance().getProjection("sugarscape");

		
		a.getVisionRule().seeAll(a).forEach(new Consumer<GridPoint>() {

			@Override
			public void accept(GridPoint t) {
				
				System.out.println("Checking point " + t);
				
				grid.getObjectsAt(t.getX(),t.getY()).forEach(new Consumer<Object>() {

					@Override
					public void accept(Object o) {

						try {
							SugarAgent_ch3 aa = (SugarAgent_ch3) o;
							System.out.println("Neigbor: " + o);
						}
						catch (ClassCastException e) {}
						
					}
				});
				
			}
		});
		
		new GridWithin<Object>(grid, a, 2).query().forEach(new Consumer<Object>() {

			@Override
			public void accept(Object t) {
				System.out.println("Neighbor: " + t);
				
			}
		});




	}

	@Test
	public void testGiveBirth() {
		fail("Not yet implemented");
	}

}
