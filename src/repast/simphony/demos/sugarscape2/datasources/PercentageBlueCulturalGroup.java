package repast.simphony.demos.sugarscape2.datasources;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public class PercentageBlueCulturalGroup implements AggregateDataSource {
	
	private String id = "CountCulturalGroups";

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
		return SugarAgent_ch3.class;
	}

	@Override
	public Object get(Iterable<?> objs, int size) {

		AtomicInteger  count_blue = new AtomicInteger(0) ; 
		AtomicInteger  count_nonBlue = new AtomicInteger(0) ; 
		
		
		objs.forEach(new Consumer<Object>() {

			@Override
			public void accept(Object t) {

				SugarAgent_ch3 a = (SugarAgent_ch3) t;
				
				if(a.isAlive()) {

					if(a.getCulturalAbility().cultureGroup(a).equalsIgnoreCase("Blue")) {count_blue.incrementAndGet();}
					else {count_nonBlue.incrementAndGet();}
				}
				
				
			}
		});
		
		
		int agents_count = (count_blue.get()+count_nonBlue.get());
		
		double perc = new Double(count_blue.intValue()) / new Double(agents_count) ;
		
		if(count_blue.get()>0) { return perc; }		
		else {return 0;}
	}

	@Override
	public void reset() {
			
	}


}
