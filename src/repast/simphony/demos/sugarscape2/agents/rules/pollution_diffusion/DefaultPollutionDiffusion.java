package repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion;

import java.util.Set;

import org.apache.commons.math3.stat.StatUtils;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public class DefaultPollutionDiffusion implements PollutionDiffusionAbility {

	/**
	 * TODO description of the notion
	 */
	int pollutionDiffusionPeriod;

	int tick=0;

	protected Utility.TypeOfVision typeOfVision;




	@Override
	public GridValueLayer diffuse(SugarSpace_ch2 s) {

		tick++;
		
		GridValueLayer pollution = (GridValueLayer) s.getValueLayer("pollution");

		if((tick%pollutionDiffusionPeriod)==0) {
			
			Grid grid =  s.getGrid();


			GridValueLayer newP = new GridValueLayer("temp", true, grid.getDimensions().getWidth(), grid.getDimensions().getHeight());			
			for(int x=0;x<grid.getDimensions().getWidth();x++) {
				for(int y=0;y<grid.getDimensions().getHeight();y++) {
					double nv = computeFlux(x, y, pollution, grid);
					newP.set(nv, x,y);
				}
			}
			
			return newP;

		}

		return pollution;
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
	
	

	@Override
	public void configureFromEnvironment() {

		pollutionDiffusionPeriod = RunEnvironment.getInstance().getParameters().getInteger("pollutionDiffusionPeriod");

		String typeOfVision_str = RunEnvironment.getInstance().getParameters().getString("VisionType");
		typeOfVision = (typeOfVision_str.equalsIgnoreCase("Moore"))?Utility.TypeOfVision.MOORE:Utility.TypeOfVision.NEUMMAN;

	}

}
