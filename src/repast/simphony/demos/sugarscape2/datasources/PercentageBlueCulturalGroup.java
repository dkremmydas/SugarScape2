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

		AtomicInteger  count = new AtomicInteger(0) ; 
		
		
		objs.forEach(new Consumer<Object>() {

			@Override
			public void accept(Object t) {

				SugarAgent_ch3 a = (SugarAgent_ch3) t;
				
				if(a.getCulturalAbility().cultureGroup(a).equalsIgnoreCase("Blue")) {count.incrementAndGet();}
				
			}
		});
		
		return new Double(100*(count.intValue()/size));
	}

	@Override
	public void reset() {
			
	}


}
