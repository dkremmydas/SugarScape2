package repast.simphony.demos.sugarscape2.agents.rules.trade;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public class TradeTransaction {
	
	private SugarAgent_ch4 agentFrom;
	
	private SugarAgent_ch4 agentTo;
	
	private String resourceName;
	
	private int quantity;

	public TradeTransaction(SugarAgent_ch4 agentFrom, SugarAgent_ch4 agentTo, String resourceName, int quantity) {
		super();
		this.agentFrom = agentFrom;
		this.agentTo = agentTo;
		this.resourceName = resourceName;
		this.quantity = quantity;
	}

	public SugarAgent_ch4 getAgentFrom() {
		return agentFrom;
	}

	public SugarAgent_ch4 getAgentTo() {
		return agentTo;
	}

	public String getResourceName() {
		return resourceName;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
	

}
