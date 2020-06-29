package repast.simphony.demos.sugarscape2.landscape;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import repast.simphony.valueLayer.GridValueLayer;

/**
 * Various properties of a Resource that exist in the Landscape
 * 
 * @author Dimitris Kremmydas
 *
 */
public class LandscapeResource {

	private String name;

	private GridValueLayer level;

	private GridValueLayer capacity;

	private GridValueLayer pollution;



	public LandscapeResource(int x, int y, String name) {

		this.name = name;

		this.level = new GridValueLayer(this.name + "_quantity", true, x,y);

		this.capacity = new GridValueLayer(name + "_capacity", true, x,y);

		this.pollution = new GridValueLayer(name + "_pollution", true, x,y);

	}



	// LEVEL
	public double getLevelXY(int x,int y) {
		return level.get(x,y);
	}


	public void setLevelXY(int x,int y, double level) {
		this.level.set(level, x, y);
	}

	//TODO[a method to provide an array of points and get a vector of quantities]



	//CAPACITY	
	public double getCapacityXY(int x,int y) {
		return capacity.get(x,y);
	}

	public void setCapacityXY(int x,int y, double level) {
		this.capacity.set(level, x, y);
	}

	//TODO[a method to provide an array of points and get a vector of quantities]



	//POLLUTION	
	public double getPollutionXY(int x,int y) {
		return pollution.get(x,y);
	}

	public void setPollutionXY(int x,int y, double level) {
		this.pollution.set(level, x, y);
	}

	//TODO[a method to provide an array of points and get a vector of quantities]



	public DescriptiveStatistics getQuantityDescriptiveStats() {
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for(int i=0;i<level.getDimensions().getWidth();i++) {
			for(int j=0;j<level.getDimensions().getHeight();j++) {
				stats.addValue(level.get(i,j));
			}
		}
		return stats;
	}

}
