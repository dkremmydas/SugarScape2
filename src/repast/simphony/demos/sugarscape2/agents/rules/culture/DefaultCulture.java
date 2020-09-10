package repast.simphony.demos.sugarscape2.agents.rules.culture;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.random.RandomHelper;

public class DefaultCulture implements CulturalAbility {

	@Override
	public Map<SugarAgent_ch3, Integer> culturalTransmission(SugarAgent_ch3 a) {
		
		Map<SugarAgent_ch3, Integer> r = new HashMap<SugarAgent_ch3, Integer>();

		a.getNeighboringSugarAgents().forEach(new Consumer<SugarAgent_ch2>() {

			@Override
			public void accept(SugarAgent_ch2 t) {
						
				SugarAgent_ch3 t_ch3 = (SugarAgent_ch3) t;
				
				int position = RandomHelper.nextIntFromTo(1, t_ch3.tagGetSize());
				
				if(! t_ch3.tagGetAtPosition(position).equals(a.tagGetAtPosition(position))) {
					r.put(t_ch3,position);
				}
				
				
			}
		});
		
		return r;
	}

	@Override
	public String cultureGroup(SugarAgent_ch3 a) {
		
		int count_0=0, count_1=0;
		
		for (int i = 1; i <= a.tagGetSize(); i++) {
			
			if(a.tagGetAtPosition(i)) {count_1++;} else {count_0++;}
		}
		
		return (count_0>count_1)?"Red":"Blue";
		
	}

}
