package gr.kremmydas.sugarscape.landscape.rules.pollution;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.LandscapeChapter2_p50;
import gr.kremmydas.sugarscape.utilities.NeighbourhoodFunctions;

import java.util.Set;

import org.apache.commons.math3.stat.StatUtils;

import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public class PollutionDiffusionRule_p50 implements PollutionDiffusionAbility {

	public PollutionDiffusionRule_p50() {}

	@Override
	public GridValueLayer diffuse(LandscapeChapter2_p50 ls) {
		Grid<Agent> grid = ls.getGrid();
		GridValueLayer oldP = ls.getPollution();
		GridValueLayer newP = new GridValueLayer("temp", true, ls.getDimensions().getWidth(),ls.getDimensions().getHeight());
		
		for(int x=0;x<grid.getDimensions().getWidth();x++) {
			for(int y=0;y<grid.getDimensions().getHeight();y++) {
				double nv = computeFlux(x, y, oldP, grid);
				newP.set(nv, x,y);
			}
		}
		
		return newP;
	}
	
	private double computeFlux(int x, int y, GridValueLayer gvl, Grid<?> grid) {
		
		Set<GridPoint> neigh = NeighbourhoodFunctions.getVonNeumanPoints(new GridPoint(x,y), grid, 1);
		double[] values = new double[neigh.size()];
		
		int i=0;
		for(GridPoint gp: neigh) {
			values[i++] = gvl.get(gp.getX(),gp.getY());
		}
		
		return StatUtils.mean(values);
	}

}
