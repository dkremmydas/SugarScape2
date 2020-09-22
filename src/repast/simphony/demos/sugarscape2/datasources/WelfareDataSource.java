package repast.simphony.demos.sugarscape2.datasources;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.data2.NonAggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public class WelfareDataSource implements NonAggregateDataSource,AggregateDataSource {

	@Override
	public String getId() {
		return "WelfareDataSource";
	}

	@Override
	public Class<?> getDataType() {
		return Double.class;
	}

	@Override
	public Class<?> getSourceType() {
		return SugarAgent_ch4.class;
	}

	@Override
	public Object get(Object obj) {

		SugarAgent_ch4 a = (SugarAgent_ch4) obj;

		double welfare = a.getWelfareAbility().estimateWelfare(
				a, 
				a.resourceGetHolding("sugar"), 
				a.resourceGetHolding("spice")
				);

		return welfare;

	}

	@Override
	public Object get(Iterable<?> objs, int size) {


		double sum = 0;

		for(Object o: objs) {
			SugarAgent_ch4 a = (SugarAgent_ch4) o;

			double welfare = a.getWelfareAbility().estimateWelfare(
					a, 
					a.resourceGetHolding("sugar"), 
					a.resourceGetHolding("spice")
					);	
			
			sum=sum+welfare;
		}

		return (sum );
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
