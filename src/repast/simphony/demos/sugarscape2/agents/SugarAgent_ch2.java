package repast.simphony.demos.sugarscape2.agents;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import com.google.common.collect.ImmutableSet;

import repast.simphony.demos.sugarscape2.agents.rules.death.DieAbility;
import repast.simphony.demos.sugarscape2.agents.rules.gather.GatherAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.PollutionAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;


/**
 * The implementation of the Agent of the Sugarscape for chapter 2.
 * 
 * @author Dimitris Kremmydas
 *
 */
public class SugarAgent_ch2 {


	//State variables

	/**
	 * A 10-character string id
	 */
	protected String id;

	protected int age = 1;

	protected int vision;

	/**
	 * Properties related to sugar
	 * We have abstracted the properties and the operations related to a resource into a Resource class.
	 * TODO[explain the flexibility of this approach: We can use many resources in the future]
	 */
		
	protected Map<String,AgentResource> resources = new CaseInsensitiveMap<String, AgentResource>();



	/**
	 * Is the agent alive ?
	 */
	protected boolean isAlive = true;



	//Abilities	

	protected DieAbility dieRule;


	protected GatherAbility gatherRule;


	protected VisionAbility visionRule;


	protected MovementAbility movementRule;


	protected PollutionAbility pollutionRule;




	//Other utility variables



	//Constructors, Initialization

	/**
	 * Set a private constructor, so that creating agents is forced through the Builder design pattern 
	 */
	protected SugarAgent_ch2() {};




	//Methods


	public String getId() {
		return id;
	}


	public int getVision() {
		return vision;
	}


	public boolean isAlive() {
		return isAlive;
	}


	public int getAge() {
		return age;
	}


	public void incrementAge() {
		age++;
	};



	/**
	 * 
	 * @return
	 */
	public GridPoint getCurrentPosition() {
		return SugarSpaceFactory.getSugarspace().gridGetAgentLocation(this);
	}



	public DieAbility getDieRule() {
		return dieRule;
	}



	public GatherAbility getGatherRule() {
		return gatherRule;
	}



	public VisionAbility getVisionRule() {
		return visionRule;
	}



	public MovementAbility getMovementRule() {
		return movementRule;
	}




	public Set<String> resourceAvailableResources() {
		return ImmutableSet.copyOf(resources.keySet());
	}

	public int resourceGetHolding(String resource) {
		
		if(! resources.containsKey(resource)) {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}
		
		return this.resources.get(resource).holding;

	}


	public int resourceGetInitialEndownment(String resource) {
		
		if(! resources.containsKey(resource)) {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

		return this.resources.get(resource).initial;
	}


	public int resourceGetMetabolism(String resource) {
		
		if(! resources.containsKey(resource)) {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

		return this.resources.get(resource).metabolism;

	}


	public void resourceUse(String resource, int quantity) {
		
		if(! resources.containsKey(resource)) {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

		this.resources.get(resource).use(quantity);

	}


	public void resourceStore(String resource, int quantity) {
		
		if(! resources.containsKey(resource)) {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

		this.resources.get(resource).store(quantity);

	}


	/**
	 * Gets the neighboring {@link SugarAgent_ch2}s of the {@link SugarAgent_ch2}. We assume that 'neighboring agents' 
	 * are those that occupy a {@link GridPoint} seen from the agent. The latter dependes on the {@link VisionAbility}
	 * of the agent.
	 * @param a The {@link SugarAgent_ch2} for whom to find the neighbor
	 * @return an {@link Iterable} of {@link SugarAgent_ch2}s. If none, returns an empty one.
	 */
	public Iterable<SugarAgent_ch2> getNeighboringSugarAgents() {

		HashSet<SugarAgent_ch2> neighbors = new HashSet<SugarAgent_ch2>();

		this.visionRule.seeAll(this).forEach(new Consumer<GridPoint>() {

			@Override
			public void accept(GridPoint t) {

				SugarAgent_ch2 aa =  SugarSpaceFactory.getSugarspace().gridGetSugarAgentAt(t.getX(),t.getY());
				if(!(aa==null)) {
					if(aa.isAlive()) { //TODO: Do we need this?
						neighbors.add(aa);
					}	
				}
			}

		});


		return neighbors;
	}



	/**
	 * 
	 */
	@Override
	public String toString() {

		//		int x = this.getCurrentPosition().getX(); 
		//		int y = this.getCurrentPosition().getY();
		//
		//		String r = "{Id:"+this.id+", Sugar Vision: "+this.getVision() +
		//				", Sugar.metab: " + this.sugar.metabolism + 
		//				", Sugar.hold: " + this.sugar.holding + 
		//				", Position: [X:"+x+", Y:"+y+", Sugar:"+ SugarSpaceFactory.getSugarspace().resourceGetHoldingAtXY("sugar",x,y)+"]"+
		//				"}";

		String r = "Id:" + this.id + " S.m: " + this.resourceGetMetabolism("sugar") + ", S.h: " + this.resourceGetHolding("sugar") ;

		return r;
	}




	/* Scheduled actions of the agent */


	public void applyRuleM() {

		if(isAlive) {
			
			//see
			Set<GridPoint> points_seen = this.visionRule.seeEmpty(this);

			//gather
			if(! points_seen.isEmpty()) {

				GridPoint new_pos = this.movementRule.move(this, points_seen);

				SugarSpaceFactory.getSugarspace().gridMoveAgentTo(this, new_pos.getX(),new_pos.getY());
				
				CaseInsensitiveMap<String, Integer>  resources_gathered = this.gatherRule.gather(this, new_pos);
				
				resources_gathered.forEach(new BiConsumer<String, Integer>() {

					@Override
					public void accept(String resource, Integer gathered) {

						int actual_sugar_gathered =  SugarSpaceFactory.getSugarspace().resourceGatherFromXY(resource,new_pos.getX(), new_pos.getY(), gathered);
						
						resourceStore(resource,actual_sugar_gathered);
						
					}
				});

			}
			
			//metabolize
			resources.forEach(new BiConsumer<String, AgentResource>() {

				@Override
				public void accept(String resource_name, AgentResource resource) {

					resourceUse(resource_name, resourceGetMetabolism(resource_name));
				}
			});

			
			//die if sugar holding<0
			if(this.dieRule.shallDie(this)) {
				this.die();		
			} else {
				this.incrementAge();
			}
		}

	}

	public void applyRuleP() {
		
		if(isAlive) {
			
			Map<GridPoint,Integer> pollution = this.pollutionRule.pollute(this);

			GridValueLayer pollution_gvl = (GridValueLayer)  SugarSpaceFactory.getSugarspace().getValueLayer("pollution");

			for (GridPoint gp: pollution.keySet()) {

				int pollution_cur = (int) pollution_gvl.get(gp.getX(),gp.getY());

				int pollution_new = pollution.get(gp);

				pollution_gvl.set(pollution_cur+pollution_new, gp.getX(),gp.getY());

			}
		}

	}


	/**
	 * 
	 */
	protected void die() {
		this.isAlive=false;
		SugarSpaceFactory.getSugarspace().removeSugarAgent(this);	
	}





	/**
	 * Builder design pattern
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	public static class Builder {

		//properties
		private String id;
		private int sugarInitial;
		private int sugarMetabolism;
		private int vision;
		private int age;

		private DieAbility dieRule;
		private VisionAbility visionRule;
		private MovementAbility movementRule;
		private GatherAbility gatherRule;
		private PollutionAbility pollutionRule;


		public Builder(String id) {
			this.id=id;
		}

		public SugarAgent_ch2 build() {
			SugarAgent_ch2 ag = new SugarAgent_ch2();

			//TODO check that all required fields have been defined

			ag.id=this.id;
			ag.vision = this.vision;
			ag.age = this.age;

			ag.resources.put("sugar",new AgentResource(this.sugarInitial, this.sugarMetabolism));

			ag.dieRule = dieRule;
			ag.visionRule = visionRule;
			ag.gatherRule = gatherRule;
			ag.movementRule = movementRule;
			ag.pollutionRule = pollutionRule;

			return ag;
		}


		public Builder withVision(int vision) {
			this.vision=vision;
			return this;
		}

		public Builder withAge(int age) {
			this.age=age;
			return this;
		}

		public Builder withSugarInitial(int sugar) {
			this.sugarInitial=sugar;
			return this;
		}

		public Builder withSugarMetabolism(int metabolism) {
			this.sugarMetabolism=metabolism;
			return this;
		}

		public Builder withDieRule(DieAbility dieRule) {
			this.dieRule=dieRule;
			return this;
		}

		public Builder withVisionRule(VisionAbility visionRule) {
			this.visionRule=visionRule;
			return this;
		}       

		public Builder withMovementRule(MovementAbility movementRule) {
			this.movementRule=movementRule;
			return this;
		} 

		public Builder withGatherRule(GatherAbility gatherRule) {
			this.gatherRule=gatherRule;
			return this;
		} 

		public Builder withPollutionRule(PollutionAbility pollutionRule) {
			this.pollutionRule=pollutionRule;
			return this;
		} 

	}

}
