package repast.simphony.demos.sugarscape2.datasources;

import java.util.Set;

import org.apache.commons.collections4.MultiValuedMap;

import repast.simphony.data2.AggregateDataSource;
import repast.simphony.demos.sugarscape2.agents.rules.trade.TradeTransaction;
import repast.simphony.engine.environment.RunEnvironment;

public class TradePriceMinDataSource implements AggregateDataSource {

	@Override
	public String getId() {
		return "TradePriceMinDataSource";
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


		double min_price = -1;

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
							
							if(min_price==-1) {min_price=tr.getPrice();}
							else if (tr.getPrice()<min_price) {min_price=tr.getPrice();};
						}
					};
				}

			}
		}

		return (min_price);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
