package repast.simphony.demos.sugarscape2.agents;

import java.util.Map;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.rules.death.DieAbility;
import repast.simphony.demos.sugarscape2.agents.rules.gather.GatherAbility;
import repast.simphony.demos.sugarscape2.agents.rules.movement.MovementAbility;
import repast.simphony.demos.sugarscape2.agents.rules.pollution.PollutionAbility;
import repast.simphony.demos.sugarscape2.agents.rules.vision.VisionAbility;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.GridValueLayer;


/**
 * The Agent of the Sugarscape
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
	protected AgentResource sugar;



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

	/**
	 * Reference to the context
	 */
	protected SugarSpace_ch2 context;

	
	
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
	public SugarSpace_ch2 getContext() {
		return context;
	}

	/**
	 * 
	 * @return
	 */
	public GridPoint getCurrentPosition() {
		return this.context.grid.getLocation(this);
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



	//public AgentResource getSugar() {
	//	return sugar;
	//}



	//public int getSugarWealth() {
	//	return this.sugar.getHolding();
	//}


	public int getResourceHolding(String resource) {

		if(resource.equalsIgnoreCase("sugar")) {
			return this.sugar.getHolding();
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}
	
	
	public int getResourceMetabolism(String resource) {

		if(resource.equalsIgnoreCase("sugar")) {
			return this.sugar.getMetabolism();
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	public void useResource(String resource, int quantity) {

		if(resource.equalsIgnoreCase("sugar")) {
			this.sugar.use(quantity);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	public void storeResource(String resource, int quantity) {

		if(resource.equalsIgnoreCase("sugar")) {
			this.sugar.store(quantity);
		} else {
			throw new RuntimeException("Resource with name '" + resource + "' does not exist");
		}

	}


	/**
	 * 
	 */
	@Override
	public String toString() {

		int x = this.getCurrentPosition().getX(); 
		int y = this.getCurrentPosition().getY();

		String r = "{Id:"+this.id+", Sugar Vision: "+this.getVision() +
				", Sugar.metab: " + this.sugar.metabolism + 
				", Sugar.hold: " + this.sugar.holding + 
				", Position: [X:"+x+", Y:"+y+", Sugar:"+this.context.getSugar().availableAtXY(x,y)+"]"+
				"}";

		return r;
	}




	/* Scheduled actions of the agent */


	@ScheduledMethod(start=1d,interval=5d)
	public void step() {

		if(isAlive) {
			Set<GridPoint> points_seen = this.visionRule.see(this);

			GridPoint new_pos = this.movementRule.move(this, points_seen);

			context.getGrid().moveTo(this, new_pos.getX(),new_pos.getY());

			int sugar_to_gather = this.gatherRule.gather(this, new_pos);

			int sugar_gathered = this.context.getSugar().gatherFromXY(new_pos.getX(), new_pos.getY(), sugar_to_gather);

			this.sugar.store(sugar_gathered);

			this.sugar.use(this.sugar.getMetabolism());

			this.pollute();

			//die if sugar holding<0
			if(this.dieRule.shallDie(this)) {
				this.die();		
			} else {
				this.incrementAge();
			}
		}

	}

	protected void pollute() {

		Map<GridPoint,Integer> pollution = this.pollutionRule.pollute(this);

		GridValueLayer pollution_gvl = (GridValueLayer) this.context.getValueLayer("pollution");

		for (GridPoint gp: pollution.keySet()) {

			int pollution_cur = (int) pollution_gvl.get(gp.getX(),gp.getY());

			int pollution_new = pollution.get(gp);

			pollution_gvl.set(pollution_cur+pollution_new, gp.getX(),gp.getY());

		}
	}


	/**
	 * 
	 */
	protected void die() {
		this.isAlive=false;
		this.context.remove(this);	
		//System.out.println("DIED: " + this);
	}







	/**
	 * The set of agent's properties (fixed or variables) that are related to a product
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	public class AgentResource {

		/**
		 * Initial endowment
		 */
		int initial;


		/**
		 * Product stored (p. 24)
		 */
		int holding;

		/**
		 * Amount of product consumed per time step (p. 24)
		 */
		int metabolism;	



		public AgentResource(int initialEndownment, int metabolism) {
			this.initial = initialEndownment;
			this.holding = initialEndownment;
			this.metabolism = metabolism;
		}


		/**
		 * Use (metabolize) some resource
		 * 
		 * @param int quantity
		 */
		public void use (int quantity) {

			if(quantity>0) {
				holding -= quantity;
			}	

		}

		public void store(int quantity) {	

			if(quantity>0) {
				holding += quantity;
			}		

		}


		public int getMetabolism() {
			return metabolism;
		}


		public int getHolding() {
			return holding;
		}


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
		SugarSpace_ch2 context;



		public Builder(String id,SugarSpace_ch2 context) {
			this.id=id;
			this.context=context;
		}

		public SugarAgent_ch2 build() {
			SugarAgent_ch2 ag = new SugarAgent_ch2();

			//TODO check that all required fields have been defined

			ag.context=this.context;
			ag.id=this.id;
			ag.vision = this.vision;
			ag.age = this.age;

			ag.sugar = ag.new AgentResource(this.sugarInitial, this.sugarMetabolism);

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
