package repast.simphony.demos.sugarscape2.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import repast.simphony.demos.sugarscape2.agents.SpaceResource;
import repast.simphony.valueLayer.GridValueLayer;

public class TestSpaceResource {
	
	static SpaceResource s;

	@Before
	public void setUp() throws Exception {
		
		GridValueLayer cap = new GridValueLayer("sugar capacity", 10, true, 10,10);
		GridValueLayer lvl = new GridValueLayer("sugar level", 10, true, 10,10);
		
		s = new SpaceResource(cap, lvl);
				
				
	}

	@Test
	public void testGatherFromXY() {
		
		s.gatherFromXY(0, 0, 1);
		assertEquals("Gather within capacity",9, (int)s.getHolding().get(0,0));
		
		
		s.gatherFromXY(0, 1, 100);
		assertEquals("Gather more then capacity",0, (int)s.getHolding().get(0,1));
		
		s.gatherFromXY(0, 2, -1);
		assertEquals("Gather negative quantity",10, (int)s.getHolding().get(0,2));
		
		s.gatherFromXY(0, 3, 10);
		assertEquals("Gather exactly the capacity",0, (int)s.getHolding().get(0,3));		
		
	}

	@Test
	public void testAvailableAtXY() {
		assertEquals("Available at a point ",10, s.availableAtXY(1, 1));
		
		int total=0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				total+=s.availableAtXY(i, j);
			}
		}
		assertEquals("Available at the lattice ",10*10*10, total);
		
	}

	@Test
	public void testAddToXY() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddToXYGridPoint() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEverywhere() {
		
		s.gatherFromXY(0, 0, 10);
		s.addEverywhere(100);
		
		assertEquals("Result of adding more than the capacity",10, (int)s.getHolding().get(0,0));
		
	}

	@Test
	public void testUpdateHolding() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCapacity() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHolding() {
		fail("Not yet implemented");
	}

}
