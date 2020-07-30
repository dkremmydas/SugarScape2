package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2;
import repast.simphony.demos.sugarscape2.builders.DefaultSugarscapeBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;
import repast.simphony.space.grid.GridPoint;

public class TestAgentBehavior_ch2 {

	SugarSpace_ch2 context;
	SugarAgent_ch2 a;
	AgentBehavior_ch2 b;

	/**
	 * @throws java.lang.Exception
	 */
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
		parms_ch2_p30.addParameter("MetabolismRule","MetabolismRule",String.class,"repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2",true);
		parms_ch2_p30.addParameter("AgentBehavior","AgentBehavior",String.class,"repast.simphony.demos.sugarscape2.agents.behaviors.AgentBehavior_ch2",true);
		parms_ch2_p30.addParameter("SpaceBehavior","SpaceBehavior",String.class,"repast.simphony.demos.sugarscape2.agents.behaviors.SpaceBehavior_ch2",true);
		
		
		RunEnvironment . init ( schedule , null , parms_ch2_p30 , true );
		Context<Object> context = new DefaultContext<Object>();
		RunState.init().setMasterContext (context);
		
		DefaultSugarscapeBuilder builder = new DefaultSugarscapeBuilder();
		this.context =  (SugarSpace_ch2) builder.build(new DefaultContext<Object>());
		this.a = (SugarAgent_ch2) this.context.getObjects(SugarAgent_ch2.class).get(0);
		this.b = new AgentBehavior_ch2("sugar level");
	}



	@Test
	public void testSetReferences() {
		Object s = a.getContext();
		assertEquals( "class repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2",s.getClass().toString() );
		System.out.println(s.getClass().toString());
	}
	
	
	@Test
	public void testSee() {
		Set<GridPoint> seen = this.b.see(a);
		assertNotNull(seen);
		
		System.out.println("Points seen:");
		
		for(GridPoint s: seen) {
			System.out.printf(" (%d,%d,s=%d) ",s.getX(),s.getY(),a.getContext().getSugar().availableAtXY(s.getX(), s.getY()));
		}
		
	}

	@Test
	public void testMove() {
		
		GridPoint old_pos = a.getContext().getGrid().getLocation(a);		
		GridPoint new_pos = b.move(a, b.see(a));
		
		assertNotEquals(old_pos, new_pos);		
				
	}

	@Test
	public void testGather() {
		
		GridPoint old_pos = a.getContext().getGrid().getLocation(a);		
		GridPoint new_pos = b.move(a, b.see(a));
		
		int old_pos_sugar = a.getContext().getSugar().availableAtXY(old_pos.getX(), old_pos.getY());
		int new_pos_sugar = a.getContext().getSugar().availableAtXY(new_pos.getX(), new_pos.getY());
		
		int old_agent_sugar = a.getSugarWealth();
		
		System.out.println(a);
		System.out.printf("Sugar before moving: %n Point from (%d,%d)=%d / Point to (%d,%d)=%d %n ",
				old_pos.getX(),old_pos.getY(),old_pos_sugar,new_pos.getX(),new_pos.getY(),new_pos_sugar);
		
		context.getGrid().moveTo(a, new_pos.getX(),new_pos.getY());
		
		assertEquals("Gather all sugar", new_pos_sugar, b.gather(a, b.move(a, b.see(a))));
		
		//apply ruleM
		a.applyRuleM();
		
		if(a.isAlive()) {
			int new_agent_sugar = a.getSugarWealth();
			
			System.out.println("Rule M has been applied ...");
			System.out.println(a);
			System.out.printf("Sugar after moving: %n Point from (%d,%d)=%d / Point to (%d,%d)=%d / ",
					old_pos.getX(),old_pos.getY(),old_pos_sugar,new_pos.getX(),new_pos.getY(),new_pos_sugar);
			
			
			assertEquals("Expected change of agent's sugar wealth",old_agent_sugar-a.getMetabolism()+new_pos_sugar, new_agent_sugar);
		}
		else {
			System.out.println("Agent died. Rerun the test.");
		}
	
		
		
		
	}

}
