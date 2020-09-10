package repast.simphony.demos.sugarscape2.agents.rules.inheritance;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;

public class DefaultInheritance implements InheritanceAbility {

	@Override
	public Map<SugarAgent_ch3, Integer> will(SugarAgent_ch3 a) {
		
		Map<SugarAgent_ch3, Integer> the_will = new HashMap<SugarAgent_ch3, Integer>();
		

		int holding_sugar  = a.resourceGetHolding("sugar");
		
		Set<SugarAgent_ch3> children = a.getSexAbility().getChildren(a);
		
		int num_of_children = children.size();
		
		if(num_of_children>0) {
			
			int will_amount = (int)Math.floor(holding_sugar/num_of_children);
			
			children.forEach(new Consumer<SugarAgent_ch3>() {

				@Override
				public void accept(SugarAgent_ch3 t) {
					
					the_will.put(t, will_amount);			
				}
			});
		}
		
		return the_will;
	}

}
