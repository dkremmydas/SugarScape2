package gr.kremmydas.sugarscape.agents.rules.vision;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2_p30;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.GridPointTranslator;

/**
 * The default vision rule (implied in the movement rule)
 * @author Dimitris Kremmydas
 *
 */
public class VisionRule_p30 implements VisionAbility {
	

	public VisionRule_p30() {
		super();
	}

	@Override
	public Set<GridPoint> getVisionedPoints(Agent owner) {
		AgentChapter2_p30 o = (AgentChapter2_p30) owner;
		
		DefaultGrid<Agent> dg = o.getMyLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		//System.out.println("Owner Location: " + gp);
		
		return this.getVonNeumanPoints(gp,o.getVisionLevel());
		
		
	}
	
	private Set<GridPoint> getVonNeumanPoints(GridPoint gp, int visR) {
		Set<GridPoint> r = new HashSet<>();
		int[] up = {1,0};
		int[] down = {-1,0};
		int[] left = {0,-1};
		int[] right = {0,1};
		
		int[] origin = new int[2];
		gp.toIntArray(origin);
		
		for(int i=1;i<=visR; i++) {
			int[] gp_up = origin.clone();int[] gp_down = origin.clone();
			int[] gp_left = origin.clone();int[] gp_right = origin.clone();
			
			GridPointTranslator gt = SimulationContext.getInstance().getLandscape().getGrid().getGridPointTranslator();
			gt.translate(gp_up, getEnhancedIntArray(up,i)); 
			gt.translate(gp_down, getEnhancedIntArray(down,i)); 
			gt.translate(gp_left, getEnhancedIntArray(left,i)); 
			gt.translate(gp_right, getEnhancedIntArray(right,i));
			
			r.add(new GridPoint(gp_up)); r.add(new GridPoint(gp_down));
			r.add(new GridPoint(gp_left)); r.add(new GridPoint(gp_right));
		}		
		
		//System.out.println("Up: " + Arrays.toString(gp_up) + ", down: " + Arrays.toString(gp_down) + ", left: " + Arrays.toString(gp_left) + ", right: " + Arrays.toString(gp_right));
		
		return r;
	}
	
	private int[] getEnhancedIntArray(int[] original,int n) {
		int[] r = original.clone();
		for(int i=0;i<r.length;i++) {
			r[i] = r[i]*n;
		}
		return r;
	}

}
