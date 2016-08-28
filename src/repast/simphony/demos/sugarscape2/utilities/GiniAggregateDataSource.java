package repast.simphony.demos.sugarscape2.utilities;

import java.util.Arrays;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.AgentChapter2_p30;

public class GiniAggregateDataSource implements AggregateDataSource {
	
	private String id = "GiniCoefficient";

	public GiniAggregateDataSource() {
		
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Class<?> getDataType() {
		return Double.class;
	}

	@Override
	public Class<?> getSourceType() {
		return AgentChapter2_p30.class;
	}

	@Override
	public Object get(Iterable<?> objs, int size) {
		double[] values = new double[size];
		double r = 0d;
		
		//collect sugar wealth for every agent
		int i=0;
		for(Object o: objs) {
			AgentChapter2_p30 a = (AgentChapter2_p30) o;
			values[i++] = a.getSugarWealth();			
		}
		
		//sort values
		Arrays.sort(values);
		
		r = calcGini(values, size);			
		
		return r;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Adapted from http://www.ict.swin.edu.au/personal/mlumpe/jCT/API/jct/util/Gini.html
	 * @param vs
	 * @param count
	 * @return
	 */
	private double calcGini(double[] vs, int count) {
		double ginisum = 0;
		double sum = 0;
		long i = 1;
		
		// auxiliaries
		long skip = 0;
		
		for ( int y=0;y<count;y++ )
		{
			double lElem = vs[y];
			
			long off = 2 * i - count - skip - 1;
			ginisum = ginisum + off * lElem;
			sum += lElem;		
			i++;
		}
		
		if ( sum != 0 )
			return ginisum / (double)(count - skip) / sum;
		else
			return Double.NaN; // no occurrence recorded
	}

}
