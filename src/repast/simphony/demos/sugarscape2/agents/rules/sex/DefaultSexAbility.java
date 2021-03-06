package repast.simphony.demos.sugarscape2.agents.rules.sex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.demos.sugarscape2.builders.SugarAgentFactory;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.GridPoint;

/**
 * This is the Rule S, as described in the book
 * TODO[give exact description]
 * 
 * @author Dimitris Kremmydas
 *
 */
public class DefaultSexAbility implements SexAbility {
	
	//Keeps for each children the tick they were born
	Map<SugarAgent_ch3,Integer> children = new HashMap<SugarAgent_ch3, Integer>();
	
	
	@Override
	public Iterable<SugarAgent_ch3> selectPotentialMates(SugarAgent_ch3 a) {

		ArrayList<SugarAgent_ch3> neighbors = new ArrayList<SugarAgent_ch3>();
		
		a.getNeighboringSugarAgents()
		.forEach(new Consumer<SugarAgent_ch2>() {

			@Override
			public void accept(SugarAgent_ch2 t) {
				
				SugarAgent_ch3 t_ch3;
				try {
					
					t_ch3 = (SugarAgent_ch3) t;
					
					if(! a.getSex().equals(t_ch3.getSex())) {
						if(t_ch3.isInFertileAge()) {
							neighbors.add((SugarAgent_ch3) t_ch3);
						}
					}
					
				} catch(ClassCastException e) {}
				
			}
		});

		return neighbors;
	}

	@Override
	public Pair<SugarAgent_ch3, GridPoint> giveBirth(SugarAgent_ch3 a1, SugarAgent_ch3 a2) {
		
		//check of resources are available from parents
		if(a1.resourceGetHolding("sugar")>a1.resourceGetInitialEndownment("sugar") & 
				a2.resourceGetHolding("sugar")>a2.resourceGetInitialEndownment("sugar")) {

			//check that there are neighboring points
			ArrayList<GridPoint> loc_possible = new ArrayList<GridPoint>();

			loc_possible.addAll(Lists.newArrayList(a1.getVisionRule().seeAll(a1)));
			loc_possible.addAll(Lists.newArrayList(a2.getVisionRule().seeAll(a2)));

			if(loc_possible.isEmpty()) {
				return ImmutablePair.of(null,null);
			} else {
				//create the child
				int child_metabolism = (RandomHelper.nextDoubleFromTo(0d, 1d)>=0.5)?a1.resourceGetMetabolism("sugar"):a2.resourceGetMetabolism("sugar");
				int child_vision = (RandomHelper.nextDoubleFromTo(0d, 1d)>=0.5)?a1.getVision():a2.getVision();
				int child_endownment = (int)Math.ceil(a1.resourceGetInitialEndownment("sugar")/2) + (int)Math.ceil(a2.resourceGetInitialEndownment("sugar")/2);
				
				
				a1.resourceUse("sugar", (int)Math.ceil(a1.resourceGetInitialEndownment("sugar")/2));
				a2.resourceUse("sugar",  (int)Math.ceil(a2.resourceGetInitialEndownment("sugar")/2));
				

				SugarAgent_ch2 child_ch2 = SugarAgentFactory.createChapter2SpecificAgent(
						RunEnvironment.getInstance().getParameters().getString("Variant"), 
						child_metabolism, 
						child_endownment, 
						child_vision
						);

				SugarAgent_ch3 child_ch3 = SugarAgentFactory.createChapter3RandomAgent(
						RunEnvironment.getInstance().getParameters().getString("Variant"), 
						child_ch2);
				
				//select the location
				GridPoint birth_loc = loc_possible.get(RandomHelper.nextIntFromTo(0, loc_possible.size()-1));
				
				//return
				return new ImmutablePair<SugarAgent_ch3, GridPoint>(child_ch3,birth_loc);


			}



		} else {
			return ImmutablePair.of(null,null);
		}


	}

	@Override
	public Set<SugarAgent_ch3> getChildren(SugarAgent_ch3 a) {
		return children.keySet();
	}




}
