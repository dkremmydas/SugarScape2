package gr.kremmydas.sugarscape.agents.rules.vision;

import gr.kremmydas.sugarscape.SimulationContext;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.agents.AgentChapter2;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.space.grid.DefaultGrid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.space.grid.GridPointTranslator;

/**
 * The vision of the agent is the Moore neighborhood 
 * (as opposed to von Neumann neighborhood that is used in the book).
 * 
 * @author Dimitris Kremmydas
 *
 */
public class MooreVisionRule implements VisionAbility {

	public MooreVisionRule() {
		super();
	}

	@Override
	public Set<GridPoint> getVisionedPoints(AgentChapter2 owner) {
		DefaultGrid<Agent> dg = owner.getMyLandscape().getGrid();
		GridPoint gp = dg.getLocation(owner);
		//System.out.println("Owner Location: " + gp);
		
		return this.getMoorePoints(gp,owner.getVisionLevel());
		
		
	}
	
	private Set<GridPoint> getMoorePoints(GridPoint gp, int visR) {
		Set<GridPoint> r = new HashSet<>();
		int[] up = {1,0};
		int[] down = {-1,0};
		int[] left = {0,-1};
		int[] right = {0,1};
		int[] upright = {1,1};
		int[] downright = {-1,1};
		int[] upleft = {1,-1};
		int[] downleft = {-1,-1};
		
		int[] origin = new int[2];
		gp.toIntArray(origin);
		
		for(int i=1;i<=visR; i++) {
			int[] gp_up = origin.clone();int[] gp_down = origin.clone();
			int[] gp_left = origin.clone();int[] gp_right = origin.clone();
			int[] gp_upright=origin.clone();int[] gp_downright=origin.clone();
			int[] gp_upleft=origin.clone(); int[] gp_downleft=origin.clone();
			
			GridPointTranslator gt = SimulationContext.getInstance().getLandscape().getGrid().getGridPointTranslator();
			gt.translate(gp_up, getEnhancedIntArray(up,i)); 
			gt.translate(gp_down, getEnhancedIntArray(down,i)); 
			gt.translate(gp_left, getEnhancedIntArray(left,i)); 
			gt.translate(gp_right, getEnhancedIntArray(right,i));			
			gt.translate(gp_upright, getEnhancedIntArray(upright,i));
			gt.translate(gp_downright, getEnhancedIntArray(downright,i));
			gt.translate(gp_upleft, getEnhancedIntArray(upleft,i));
			gt.translate(gp_downleft, getEnhancedIntArray(downleft,i));
			
			r.add(new GridPoint(gp_up)); r.add(new GridPoint(gp_down));
			r.add(new GridPoint(gp_left)); r.add(new GridPoint(gp_right));
			r.add(new GridPoint(gp_upright)); r.add(new GridPoint(gp_downright));
			r.add(new GridPoint(gp_upleft)); r.add(new GridPoint(gp_downleft));
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
