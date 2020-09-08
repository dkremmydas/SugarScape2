package repast.simphony.demos.sugarscape2.agents;

import java.util.function.BiConsumer;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.rules.inheritance.InheritanceAbility;
import repast.simphony.demos.sugarscape2.agents.rules.sex.SexAbility;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.space.grid.GridPoint;

/**
 * 
 * The implementation of the Agent of the Sugarscape for chapter 3.
 * 
 * @author Dimitris Kremmydas
 *
 */
public class SugarAgent_ch3 extends SugarAgent_ch2 {

	public enum Sex {MALE, FEMALE}

	private SugarAgent_ch3.Sex sex;

	private int childbearing_age_start;

	private int childbearing_age_end;

	private SexAbility sexRule;	
	
	private InheritanceAbility inheritanceRule;





	// Getters of properties

	public SugarAgent_ch3.Sex getSex() {
		return sex;
	}

	public int getChildbearing_age_start() {
		return childbearing_age_start;
	}

	public int getChildbearing_age_end() {
		return childbearing_age_end;
	}

	public boolean isInFertileAge() {

		if( (age>=childbearing_age_start) & (age<childbearing_age_end)) {
			return true;
		} else {
			return false;
		}
	}

	public SexAbility getSexRule() {
		return sexRule;
	}
	
	

	@Override
	protected void die() {
		
		//pass holding to children
		inheritanceRule.will(this).forEach(new BiConsumer<SugarAgent_ch3, Integer>() {

			@Override
			public void accept(SugarAgent_ch3 t, Integer u) {
					t.resourceStore("sugar", u);			
			}
		});
		
		//die as a SugarAgent_ch2
		super.die();
	}

	//	@ScheduledMethod(start=3d,interval=10d)
	public void applyRuleS() {

		if(isAlive) {

			if(isInFertileAge()) {

				Iterable<SugarAgent_ch3> ags = this.sexRule.selectPotentialMates(this);

				for(SugarAgent_ch3 a: ags) {

					Pair<SugarAgent_ch3,GridPoint> m = this.sexRule.giveBirth(this,a);

					if(!(m.getLeft()==null)) {
						SugarAgent_ch3 child = m.getLeft();
						GridPoint loc_to_put = m.getRight();

						((SugarSpace_ch3)SugarSpaceFactory.getSugarspace()).addSugarAgent(child);
						SugarSpaceFactory.getSugarspace().gridMoveAgentTo(child,loc_to_put.getX(),loc_to_put.getY());

					}
				}
			}	

		}
	}




	/**
	 * Builder Design pattern
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	public static class Builder {

		//properties
		private SugarAgent_ch2 a;
		private SugarAgent_ch3.Sex sex;
		private int childbearing_age_start;		
		private int childbearing_age_end;

		private SexAbility sexRule;
		private InheritanceAbility inheritanceRule;


		public Builder(SugarAgent_ch2 a) {
			this.a=a;
		}

		public SugarAgent_ch3 build() {
			SugarAgent_ch3 ag = new SugarAgent_ch3();

			//inherited properties from SugarAgent_ch2
			ag.id = this.a.id;
			ag.age = this.a.age;
			ag.vision = this.a.vision;
			ag.sugar = this.a.sugar;
			ag.isAlive = this.a.isAlive;

			//inherited rules from SugarAgent_ch2
			ag.dieRule = this.a.dieRule;
			ag.gatherRule = this.a.gatherRule;
			ag.visionRule = this.a.visionRule;
			ag.movementRule = this.a.movementRule;
			ag.pollutionRule = this.a.pollutionRule;

			//own properties
			ag.childbearing_age_start = this.childbearing_age_start;
			ag.childbearing_age_end = this.childbearing_age_end;
			ag.sex = this.sex;

			//own rules
			ag.sexRule = this.sexRule;
			ag.inheritanceRule = this.inheritanceRule;

			//TODO check that all required fields have been defined

			return ag;
		}

		public Builder withSex(SugarAgent_ch3.Sex sex) {
			this.sex=sex;
			return this;
		} 

		public Builder withChildBearingAge(int start, int end) {
			this.childbearing_age_start = start;
			this.childbearing_age_end = end;
			return this;
		}


		public Builder withSexRule(SexAbility sexRule) {
			this.sexRule=sexRule;
			return this;
		} 
		
		public Builder withInheritanceRule(InheritanceAbility inheritanceRule) {
			this.inheritanceRule=inheritanceRule;
			return this;
		} 

	}	





}
