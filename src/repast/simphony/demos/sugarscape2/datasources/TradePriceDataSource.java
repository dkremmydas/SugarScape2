package repast.simphony.demos.sugarscape2.datasources;

import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.log4j.Level;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.rules.trade.TradeTransaction;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;

public class TradePriceDataSource implements AggregateDataSource {

	@Override
	public String getId() {
		return "TradePriceDataSource";
	}

	@Override
	public Class<?> getDataType() {
		return Double.class;
	}

	@Override
	public Class<?> getSourceType() {
		return MultiValuedMap.class;
	}


	@Override
	public Object get(Iterable<?> objs, int size) {


		double mean_price = 0;
		double sum_price = 0;
		int trans_number = 0;
		double cur_tick = RunEnvironment.getInstance().getCurrentSchedule().getTickCount();

		for(Object o: objs) {

			@SuppressWarnings("unchecked")
			MultiValuedMap<Double, TradeTransaction> registry = (MultiValuedMap<Double, TradeTransaction>)o;
			
			Set<Double> ticks = registry.keySet();
			
			for(Double t: ticks) {
				
				if(t.compareTo(cur_tick)==0) {
					
					Set<TradeTransaction> transactions = (Set<TradeTransaction>) registry.get(t);

					for(TradeTransaction tr: transactions) {
						if(!(tr.getPrice()== Double.NaN) 
								& !(tr.getPrice()==Double.POSITIVE_INFINITY)
								& !(tr.getPrice()==Double.NEGATIVE_INFINITY)) {
							sum_price = sum_price+tr.getPrice();
							trans_number++;
						}
						
					}
				}
			}

		}
		
		mean_price=sum_price/trans_number;
		
//		Utility.logMessage(
//				Level.DEBUG, 
//				"Tick: " + cur_tick + " Sum of prices:" + sum_price + " / Num of trans:" + trans_number + " / Mean price:" + mean_price
//				);
		
		

		return (mean_price);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
