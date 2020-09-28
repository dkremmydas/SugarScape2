package repast.simphony.demos.sugarscape2.datasources;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.data2.NonAggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public class SpiceMetabolismDataSource implements NonAggregateDataSource,AggregateDataSource {

	@Override
	public String getId() {
		return "SpiceMetabolismDataSource";
	}

	@Override
	public Class<?> getDataType() {
		return int.class;
	}

	@Override
	public Class<?> getSourceType() {
		return SugarAgent_ch4.class;
	}

	@Override
	public Object get(Object obj) {
		
		SugarAgent_ch4 a = (SugarAgent_ch4) obj;
		
		return a.resourceGetMetabolism("spice");
		
	}

	@Override
	public Object get(Iterable<?> objs, int size) {
		
		
		double sum = 0; int n_agents=0;
		
		for(Object o: objs) {
			SugarAgent_ch4 a = (SugarAgent_ch4) o;
			if(a.isAlive()) {
				sum = sum + a.resourceGetMetabolism("spice");	
				n_agents++;
			}
			
		}
		
	    return (sum/n_agents );
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
