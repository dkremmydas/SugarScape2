package repast.simphony.demos.sugarscape2.agents.rules.pollution_diffusion;

import java.util.Set;

import org.apache.commons.math3.stat.StatUtils;

import repast.simphony.demos.sugarscape2.agents.SugarSpace_ch2;
import repast.simphony.demos.sugarscape2.agents.rules.ConfigurableFromRepastEnvironment;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;

public class DefaultPollutionDiffusion implements PollutionDiffusionAbility, ConfigurableFromRepastEnvironment {

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
			
			//Grid grid =  s.getGrid();


			GridValueLayer newP = new GridValueLayer("temp", true, s.gridGetWidth(), s.gridGetHeight());			
			for(int x=0;x<s.gridGetWidth();x++) {
				for(int y=0;y<s.gridGetHeight();y++) {
					double nv = computeFlux(x, y, pollution, s);
					newP.set(nv, x,y);
				}
			}
			
			return newP;

		}

		return pollution;
	}
	
	
	
	private double computeFlux(int x, int y, GridValueLayer gvl,SugarSpace_ch2 s) {

		Set<GridPoint> neigh = (Set<GridPoint>) s.gridGetNeighboringPoints(new GridPoint(x,y), 1, Utility.TypeOfVision.NEUMMAN);
				//NeighbourhoodFunctions.getVonNeumanPoints(new GridPoint(x,y), grid, 1);
		double[] values = new double[neigh.size()];

		int i=0;
		for(GridPoint gp: neigh) {
			int pol = (int) gvl.get(gp.getX(),gp.getY());
			values[i++] = (pol>0)?(pol-1):0;
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
