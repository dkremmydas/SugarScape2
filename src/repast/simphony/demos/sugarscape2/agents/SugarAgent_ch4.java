package repast.simphony.demos.sugarscape2.agents;

import repast.simphony.demos.sugarscape2.agents.rules.trade.TradeAbility;
import repast.simphony.demos.sugarscape2.agents.rules.welfare.WelfareAbility;

public class SugarAgent_ch4 extends SugarAgent_ch3 {
	
	
	// properties
	
	
	
	
	// rules
	
	protected WelfareAbility welfareAbility;
	
	protected TradeAbility tradeAbility;
	
	
	



	/**
	 * Builder Design pattern
	 * 
	 * @author Dimitris Kremmydas
	 *
	 */
	public static class Builder {

		//properties
		private SugarAgent_ch3 a;
		private int spiceInitial;
		private int spiceMetabolism;
		

		// rules
		private WelfareAbility wa;
		private TradeAbility ta;
		


		public Builder(SugarAgent_ch3 a) {
			this.a=a;
		}

		public SugarAgent_ch4 build() {
			SugarAgent_ch4 ag = new SugarAgent_ch4();

			//inherited properties from SugarAgent_ch2
			ag.id = this.a.id;
			ag.age = this.a.age;
			ag.vision = this.a.vision;
			ag.resources = this.a.resources;
			ag.isAlive = this.a.isAlive;

			//inherited rules from SugarAgent_ch2
			ag.dieRule = this.a.dieRule;
			ag.gatherRule = this.a.gatherRule;
			ag.visionRule = this.a.visionRule;
			ag.movementRule = this.a.movementRule;
			ag.pollutionRule = this.a.pollutionRule;
			
			
			//inherited properties from SugarAgent_ch3
			ag.childbearing_age_start = this.a.childbearing_age_start;
			ag.childbearing_age_end = this.a.childbearing_age_end;
			ag.sex = this.a.sex;
			ag.tagString = this.a.tagString;

			//inherited rules from SugarAgent_ch3
			ag.sexAbility = this.a.sexAbility;
			ag.inheritanceAbility = this.a.inheritanceAbility;
			ag.culturalAbility = this.a.culturalAbility;
			ag.combatAbility = this.a.combatAbility;
			

			//own properties
			ag.resources.put("spice", new AgentResource(this.spiceInitial, this.spiceMetabolism));
			

			//own rules
			ag.welfareAbility = this.wa;
			ag.tradeAbility = this.ta;
			

			//TODO check that all required fields have been defined

			return ag;
		}

		public Builder withSpice(int initial, int metabolism) {
			this.spiceInitial = initial;
			this.spiceMetabolism = metabolism;
			return this;
		} 
		
		public Builder withWelfareAbility(WelfareAbility wa) {
			this.wa = wa;
			return this;
		} 
		
		public Builder withTradeAbility(TradeAbility ta) {
			this.ta = ta;
			return this;
		} 


	}	

}
