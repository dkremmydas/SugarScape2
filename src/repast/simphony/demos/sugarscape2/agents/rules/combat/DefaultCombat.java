package repast.simphony.demos.sugarscape2.agents.rules.combat;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.agents.rules.ConfigurableFromRepastEnvironment;
import repast.simphony.engine.environment.RunEnvironment;

public class DefaultCombat implements CombatAbility,ConfigurableFromRepastEnvironment {

	int combat_reward;

	@Override
	public Pair<SugarAgent_ch3, Integer> getVictim(SugarAgent_ch3 a) {

		//we put rewards as a key, so as to be sorted.
		//  in case two victims have the same 
		HashMap<SugarAgent_ch3,Integer> reward_map = new HashMap<SugarAgent_ch3, Integer>();

		//calculate the reward for each possible victim
		a.getNeighboringSugarAgents().forEach(
				new Consumer<SugarAgent_ch2>() {

					@Override
					public void accept(SugarAgent_ch2 t) {

						SugarAgent_ch3 t_ch3 = (SugarAgent_ch3) t;

						//check they are in different group
						if(!t_ch3.getCulturalAbility().cultureGroup(t_ch3).equalsIgnoreCase(a.getCulturalAbility().cultureGroup(a))) {

							//check he has more 
							if(a.resourceGetHolding("sugar")>t_ch3.resourceGetHolding("sugar")) {

								//calculate and save the reward
								reward_map.put(t_ch3, Math.min(combat_reward, t_ch3.resourceGetHolding("sugar") ));

							}

						}

					}
				});	


		//On the ordered map by the rewards, look if you can evade retaliation.
		//	If yes, this agent will be the victim
		final SugarAgent_ch3[] victim = {null};
		final int[] reward = {0};

		DefaultCombat.sortByValue(reward_map).forEach(new BiConsumer<SugarAgent_ch3, Integer>() {

			Boolean victim_found = false;

			@Override
			public void accept(SugarAgent_ch3 victim_potential, Integer u) {

				int new_wealth = a.resourceGetHolding("sugar")+u;

				victim_potential.getNeighboringSugarAgents().forEach(new Consumer<SugarAgent_ch2>() {

					@Override
					public void accept(SugarAgent_ch2 retaliator) {

						SugarAgent_ch3 retaliator_ch3 = (SugarAgent_ch3) retaliator;

						if(!retaliator_ch3.getCulturalAbility().cultureGroup(retaliator_ch3).equalsIgnoreCase(a.getCulturalAbility().cultureGroup(a))) {

							if(retaliator_ch3.resourceGetHolding("sugar")<new_wealth) {
								victim[0] = retaliator_ch3;
								reward[0] = u;
								victim_found  = true;
								return;
							}

						}

					}
				});
				
				if(victim_found) {return;}
			}
		});


		return Pair.of(victim[0], reward[0]);	

	}


	@Override
	public void configureFromEnvironment() {

		combat_reward = RunEnvironment.getInstance().getParameters().getInteger("combat_reward");

	}


	private static HashMap<SugarAgent_ch3, Integer> sortByValue(HashMap<SugarAgent_ch3, Integer> hm) 
	{ 
		// Create a list from elements of HashMap 
		List<Map.Entry<SugarAgent_ch3, Integer> > list = 
				new LinkedList<Map.Entry<SugarAgent_ch3, Integer> >(hm.entrySet()); 

		// Sort the list 
		Collections.sort(list, new Comparator<Map.Entry<SugarAgent_ch3, Integer> >() { 
			public int compare(Map.Entry<SugarAgent_ch3, Integer> o1,  
					Map.Entry<SugarAgent_ch3, Integer> o2) 
			{ 
				return (o1.getValue()).compareTo(o2.getValue()); 
			} 
		}); 

		// put data from sorted list to hashmap  
		HashMap<SugarAgent_ch3, Integer> temp = new LinkedHashMap<SugarAgent_ch3, Integer>(); 
		for (Map.Entry<SugarAgent_ch3, Integer> aa : list) { 
			temp.put(aa.getKey(), aa.getValue()); 
		} 
		return temp; 
	} 

}
