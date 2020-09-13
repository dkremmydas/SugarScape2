package repast.simphony.demos.sugarscape2.agents;

import java.util.function.BiConsumer;

import org.apache.commons.lang3.tuple.Pair;

import repast.simphony.demos.sugarscape2.agents.rules.combat.CombatAbility;
import repast.simphony.demos.sugarscape2.agents.rules.culture.CulturalAbility;
import repast.simphony.demos.sugarscape2.agents.rules.inheritance.InheritanceAbility;
import repast.simphony.demos.sugarscape2.agents.rules.sex.SexAbility;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.demos.sugarscape2.space.SugarSpace_ch3;
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

	
	//properties
	
	private SugarAgent_ch3.Sex sex;

	private int childbearing_age_start;

	private int childbearing_age_end;
	
	private Boolean[] tagString;
	
	
	
	//rules

	private SexAbility sexAbility;	
	
	private InheritanceAbility inheritanceAbility;
	
	private CulturalAbility culturalAbility;
	
	private CombatAbility combatAbility;





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
	
	
	/**
	 * 
	 * @param position int Starts from 1.
	 * @return
	 */
	public Boolean tagGetAtPosition(int position) {
		boolean r;
		
		try {
			r = this.tagString[position-1];
		} 
		catch (Exception e) {
			throw new RuntimeException("Could not access the tagString at position: " + (position-1) + ". Error: " + e.getMessage());
		}
		
		return r;
	}
	
	
	/**
	 * 
	 * @param position int Starts from 1.
	 * @param value
	 * @return
	 */
	public void tagSetAtPosition(int position, Boolean value) {
				
		try {
			this.tagString[position-1]=value;
		} 
		catch (Exception e) {
			throw new RuntimeException("Could not access the tagString at position: " + (position-1) + ". Error: " + e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @param position int Starts from 1.
	 * @param value int 0 or 1
	 * @return
	 */
	public void tagSetAtPosition(int position, int value) {
				
		try {
			this.tagString[position-1]=(value==1);
		} 
		catch (Exception e) {
			throw new RuntimeException("Could not access the tagString at position: " + (position-1) + ". Error: " + e.getMessage());
		}
	}
	
	public int tagGetSize() {
		return tagString.length;
	}
	
	
	/**
	 * 
	 * @param position int Starts at 1
	 */
	public void tagFlipAtPosition(int position) {
		
		try {
			this.tagString[position-1] = !this.tagString[position-1];
		} 
		catch (Exception e) {
			throw new RuntimeException("Could not access the tagString at position: " + (position-1) + ". Error: " + e.getMessage());
		}
		
	}
	
	

	public SexAbility getSexAbility() {
		return sexAbility;
	}
	

	public InheritanceAbility getInheritanceAbility() {
		return inheritanceAbility;
	}

	public CulturalAbility getCulturalAbility() {
		return culturalAbility;
	}	
	
	public CombatAbility getCombatAbility() {
		return combatAbility;
	}
	
	
	
	
	
	//Rules

	@Override
	protected void die() {
		
		//pass holding to children
		inheritanceAbility.will(this).forEach(new BiConsumer<SugarAgent_ch3, Integer>() {

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

				Iterable<SugarAgent_ch3> ags = this.sexAbility.selectPotentialMates(this);

				for(SugarAgent_ch3 a: ags) {

					Pair<SugarAgent_ch3,GridPoint> m = this.sexAbility.giveBirth(this,a);

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
	
	//	@ScheduledMethod(start=5d,interval=10d)
	public void applyRuleK() {
		
		if(this.isAlive) {
			

			this.culturalAbility.culturalTransmission(this).forEach(new BiConsumer<SugarAgent_ch3, Integer>() {

				@Override
				public void accept(SugarAgent_ch3 t, Integer u) {

					t.tagFlipAtPosition(u);
					
				}
			});
			
		}		
		
	}
	
	
//	@ScheduledMethod(start=5d,interval=10d)
	public void applyRuleC() {
		
		if(this.isAlive) {
			
			Pair<SugarAgent_ch3,Integer> victim = this.combatAbility.getVictim(this);
			if(!(victim.getLeft()==null)) {
							
				GridPoint gp = victim.getLeft().getCurrentPosition();
				
				victim.getLeft().die();
				
				this.resourceStore("sugar", victim.getRight());
				
				SugarSpaceFactory.getSugarspace().gridMoveAgentTo(this, gp.getX(),gp.getY());
				
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
		private Boolean[] tagString;

		private SexAbility sexRule;
		private InheritanceAbility inheritanceRule;
		private CulturalAbility culturalRule;
		private CombatAbility combatRule;


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
			ag.tagString = tagString;

			//own rules
			ag.sexAbility = this.sexRule;
			ag.inheritanceAbility = this.inheritanceRule;
			ag.culturalAbility = this.culturalRule;
			ag.combatAbility = this.combatRule;
			

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
		
		public Builder withTag(Boolean[] tagString) {
			this.tagString = tagString;
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
		
		public Builder withCulturalRule(CulturalAbility culturalRule) {
			this.culturalRule=culturalRule;
			return this;
		}

		public Builder withCombatRule(CombatAbility cmba) {
			this.combatRule = cmba;
			return this;
		} 

	}	





}
