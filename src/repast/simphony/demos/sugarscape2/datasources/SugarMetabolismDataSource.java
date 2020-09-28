package repast.simphony.demos.sugarscape2.datasources;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.data2.NonAggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;

public class SugarMetabolismDataSource implements NonAggregateDataSource,AggregateDataSource {

	@Override
	public String getId() {
		return "SugarMetabolismDataSource";
	}

	@Override
	public Class<?> getDataType() {
		return int.class;
	}

	@Override
	public Class<?> getSourceType() {
		return SugarAgent_ch2.class;
	}

	@Override
	public Object get(Object obj) {
		
		SugarAgent_ch2 a = (SugarAgent_ch2) obj;
		
		return a.resourceGetMetabolism("sugar");
		
	}

	@Override
	public Object get(Iterable<?> objs, int size) {
		
		
		double sum = 0; int n_agents=0;
		
		for(Object o: objs) {
			SugarAgent_ch2 a = (SugarAgent_ch2) o;
			if(a.isAlive()) {
				sum = sum + a.resourceGetMetabolism("sugar");	
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
