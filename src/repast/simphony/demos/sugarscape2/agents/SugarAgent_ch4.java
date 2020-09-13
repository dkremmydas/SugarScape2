package repast.simphony.demos.sugarscape2.agents;

public class SugarAgent_ch4 extends SugarAgent_ch3 {
	
	
	private AgentResource spice;
	
	
	
	public SugarAgent_ch4() {
		super();
	}







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
		

		//private SexAbility sexRule;
		


		public Builder(SugarAgent_ch3 a) {
			this.a=a;
		}

		public SugarAgent_ch4 build() {
			SugarAgent_ch4 ag = new SugarAgent_ch4();

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
			ag.spice = new AgentResource(this.spiceInitial, this.spiceMetabolism);
			

			//own rules
			
			

			//TODO check that all required fields have been defined

			return ag;
		}

		public Builder withSpice(int initial, int metabolism) {
			this.spiceInitial = initial;
			this.spiceMetabolism = metabolism;
			return this;
		} 


	}	

}
