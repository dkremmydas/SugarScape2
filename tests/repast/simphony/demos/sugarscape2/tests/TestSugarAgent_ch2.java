/**
 * 
 */
package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.builders.DefaultSugarscapeBuilder_chapter2;
import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.environment.RunState;
import repast.simphony.engine.schedule.ISchedulableAction;
import repast.simphony.engine.schedule.Schedule;
import repast.simphony.parameter.DefaultParameters;
import repast.simphony.space.grid.GridPoint;

/**
 * @author jkr
 *
 */
public class TestSugarAgent_ch2 {
	

	SugarSpace_ch2 context;
	SugarAgent_ch2 a;

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
		parms_ch2_p30.addParameter("regenerationRate", "regenerationRate", Integer.class, 3, true);
		
		
		RunEnvironment . init ( schedule , null , parms_ch2_p30 , true );
		Context<Object> context = new DefaultContext<Object>();
		RunState.init().setMasterContext (context);
		
		DefaultSugarscapeBuilder_chapter2 builder = new DefaultSugarscapeBuilder_chapter2();
		this.context =  (SugarSpace_ch2) builder.build(new DefaultContext<Object>());
		this.a = (SugarAgent_ch2) this.context.getObjects(SugarAgent_ch2.class).get(0);
	}


	

	/**
	 * Test method for {@link repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2#SugarAgent_ch2()}.
	 */
	@Test
	public void testSugarAgent_ch2() {
		assertNotNull(a);
	}

	/**
	 * Test to verify the number of scheduled actions.
	 */
	@Test
	public void HasScheduledActions() {
		List<ISchedulableAction> sa = RunEnvironment.getInstance().getCurrentSchedule().schedule(a);
		assertNotNull(sa);
		System.out.println(sa);
	}
	
	/**
	 * Test method for {@link repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2#applyRuleM()}.
	 */
	@Test
	public void testapplyRuleM() {
		GridPoint old_position = this.context.gridGetAgentLocation(this.a);
		System.out.println("Agent before Move:\n"+a.toString()+"\n");
		this.a.step();
		System.out.println("Agent after Move:\n"+a.toString()+"\n");
		
		if(this.a.isAlive()) {
			GridPoint new_position = this.context.gridGetAgentLocation(this.a);	
			
			assertNotEquals(old_position, new_position);
			System.out.println("Old position: ("+old_position.getX()+
					","+old_position.getY()+
					") / New positions: ("+
					new_position.getX()+","
					+new_position.getY()+")"
					);
		} else {
			System.out.println("The agent died");
		}
		
	}
	
	

}
