package repast.simphony.demos.sugarscape2.agents;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Level;

import repast.simphony.demos.sugarscape2.agents.rules.sex.SexAbility;
import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.demos.sugarscape2.utilities.Utility;
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
	
//	@ScheduledMethod(start=3d,interval=10d)
	public void applyRuleS() {
		
		if(isAlive) {
			
			if(isInFertileAge()) {
	
				Iterable<SugarAgent_ch3> ags = this.sexRule.selectPotentialMates(this);
				
//				System.out.println("Agent [" + this.getId() + "]" + 
//									"\n\tLooking at points: " + visionRule.seeEmpty(this) + 
//									"\n\tChecking for having children ... found "+Iterables.size(ags)+" potential candidates");
//				
				for(SugarAgent_ch3 a: ags) {
					
					Utility.logMessage(Level.DEBUG, "\nAgents " + this.id + " and " + a.getId() + 
							"are about to have child. Sugar holding before: " +
							this.id + "=" + this.sugar.getHolding() + ", " + 
							a.getId() + "=" + a.sugar.getHolding());
					
					Pair<SugarAgent_ch3,GridPoint> m = this.sexRule.giveBirth(this,a);
					
					
					
					if(!(m.getLeft()==null)) {
						SugarAgent_ch3 child = m.getLeft();
						GridPoint loc_to_put = m.getRight();
						
						((SugarSpace_ch3)SugarSpaceFactory.getSugarspace()).addSugarAgent(child);
						 SugarSpaceFactory.getSugarspace().gridMoveAgentTo(child,loc_to_put.getX(),loc_to_put.getY());
						
						 Utility.logMessage(Level.DEBUG, "Agents " + this.id + " and " + a.getId() + " just had the child " + m.getLeft().getId());
						 Utility.logMessage(Level.DEBUG, "Agents " + this.id + " and " + a.getId() + 
									"Sugar holding after: " +
									this.id + "=" + this.sugar.getHolding() + ", " + 
									a.getId() + "=" + a.sugar.getHolding());
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

	}	
	
	
	
	

}
