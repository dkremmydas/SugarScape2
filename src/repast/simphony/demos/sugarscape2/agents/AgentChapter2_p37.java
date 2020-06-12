package repast.simphony.demos.sugarscape2.agents;

public class AgentChapter2_p37 extends AgentChapter2_p30 {
	
	private int maxAge;
	private int currentAge = 0;

	private AgentChapter2_p37() {
	}
	
	public static class Builder {
		
		private AgentChapter2_p30 ag30;
		private int maxAge;
		
		 public Builder agentCh30(AgentChapter2_p30 ag30) {
	        	this.ag30 = ag30;
	        	return this;
	        }
		 
		 public Builder maxAge(int x) {
	        	this.maxAge=x;
	        	return this;
	        }
		 
		 public AgentChapter2_p37 build() {
	        	AgentChapter2_p37 ag = new AgentChapter2_p37();
	        	ag.id=this.ag30.id;
	        	ag.ini_x = this.ag30.ini_x;
	        	ag.ini_y = this.ag30.ini_y;
	        	ag.visionLevel = this.ag30.visionLevel;
	        	ag.sugarProperties = this.ag30.sugarProperties;
	        	ag.myLandscape = this.ag30.myLandscape;
	        	ag.metabolismRule = this.ag30.metabolismRule;
	        	ag.gatheringRule = this.ag30.gatheringRule;
	        	ag.movementRule = this.ag30.movementRule;
	        	ag.visionRule = this.ag30.visionRule;
	        	ag.deathRule = this.ag30.deathRule;
	        	ag.maxAge = this.maxAge;
	        return ag;	 

	}
	}


	public int getCurrentAge() {
		return currentAge;
	}

	public void setCurrentAge(int currentAge) {
		this.currentAge = currentAge;
	}
	
	

	
}
