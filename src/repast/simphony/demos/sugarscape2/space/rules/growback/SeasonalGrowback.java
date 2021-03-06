package repast.simphony.demos.sugarscape2.space.rules.growback;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * The grid is separated in two parts, one upper and one lower. Additionally, there are two seasons: summer and winter
 * 
 * There are different growback rate changes in seasons. When there is winter in the upper part, there is summer in the lower, and vice versa.
 * 
 * 
 * 
 * @author Dimitrios Kremmydas
 *
 */
public class SeasonalGrowback extends DefaultGrowback {

	/**
	 * The seasons
	 */
	enum Season {
		Summer, Winter
	}

	/**
	 * The season of the upper part of the Sugarscape
	 */
	private Season season = Season.Summer;

	private int summerRegenerationRate;

	private int winterRegenerationRate;



	@Override
	public CaseInsensitiveMap<String, GridValueLayer> growback(SugarSpace_ch2 s) {

		int width = s.gridGetWidth();
		int height = s.gridGetHeight();

		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				if(j>(height/2)) {
					if(season==Season.Summer) {
						s.resourceAddToXY("sugar",i, j, summerRegenerationRate);
					} 
					else {
						s.resourceAddToXY("sugar",i, j, winterRegenerationRate);
					}
				}
				else {
					if(season==Season.Summer) {
						s.resourceAddToXY("sugar",i, j, winterRegenerationRate);
					}
					else {
						s.resourceAddToXY("sugar",i, j, summerRegenerationRate);
					}
				}

			}
		}
		
		CaseInsensitiveMap<String, GridValueLayer> r = new CaseInsensitiveMap<String, GridValueLayer>();
		r.put("string", s.resourceGetHolding("sugar"));

		return r;

	}




	@Override
	public void configureFromEnvironment() {
		
		super.configureFromEnvironment();

		summerRegenerationRate = RunEnvironment.getInstance().getParameters().getInteger("SummerRegenerationRate");
		winterRegenerationRate = RunEnvironment.getInstance().getParameters().getInteger("WinterRegenerationRate");
	}




}
