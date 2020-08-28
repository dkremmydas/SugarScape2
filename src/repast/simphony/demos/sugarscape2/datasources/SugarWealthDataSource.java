package repast.simphony.demos.sugarscape2.datasources;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.data2.NonAggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;

public class SugarWealthDataSource implements NonAggregateDataSource,AggregateDataSource {

	@Override
	public String getId() {
		return "SugarWealthDataSource";
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
		
		return a.getResourceHolding("sugar");
		
	}

	@Override
	public Object get(Iterable<?> objs, int size) {
		
		
		double sum = 0;
		
		for(Object o: objs) {
			SugarAgent_ch2 a = (SugarAgent_ch2) o;
			sum = sum + a.getResourceHolding("sugar");			
		}
		
	    return (sum );
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	
}
