package repast.simphony.demos.sugarscape2.datasources;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.data2.NonAggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;

public class SpiceWealthDataSource implements NonAggregateDataSource,AggregateDataSource {

	@Override
	public String getId() {
		return "SpiceWealthDataSource";
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
		
		return a.resourceGetHolding("spice");
		
	}

	@Override
	public Object get(Iterable<?> objs, int size) {
		
		
		double sum = 0;
		
		for(Object o: objs) {
			SugarAgent_ch2 a = (SugarAgent_ch2) o;
			if(a.isAlive() ) {
				sum = sum + a.resourceGetHolding("spice");		
			}
				
		}
		
	    return (sum );
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
