package repast.simphony.demos.sugarscape2.agents.rules.trade;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;
import repast.simphony.space.grid.GridPoint;

public class TradeTransaction {
	
	private SugarAgent_ch4 sugar_contributor;
	
	private int sugar_quantity;
	
	private SugarAgent_ch4 spice_constibutor;
	
	private int spice_quantity;
	
	private double price;
	
	private GridPoint location_sugar_contributor;
	
	private GridPoint location_spice_contributor;
	
	
	public TradeTransaction(SugarAgent_ch4 sugar_contributor, int sugar_quantity, SugarAgent_ch4 spice_constibutor, int spice_quantity, double price) {
		this.sugar_contributor = sugar_contributor;
		this.sugar_quantity = sugar_quantity;
		this.spice_constibutor = spice_constibutor;
		this.spice_quantity = spice_quantity;
		this.price = price;
		
		this.location_sugar_contributor = sugar_contributor.getCurrentPosition();
		this.location_spice_contributor = spice_constibutor.getCurrentPosition();
	}


	public SugarAgent_ch4 getSugarContributor() {
		return sugar_contributor;
	}


	public int getSugar_quantity() {
		return sugar_quantity;
	}


	public SugarAgent_ch4 getSpiceContributor() {
		return spice_constibutor;
	}


	public int getSpice_quantity() {
		return spice_quantity;
	}


	public double getPrice() {
		return price;
	}


	public GridPoint getLocationOfSugarContributor() {
		return location_sugar_contributor;
	}


	public GridPoint getLocationOfSpicerContributor() {
		return location_spice_contributor;
	}


	@Override
	public String toString() {
		return "TradeTransaction [sugar_contributor=" + sugar_contributor + ", sugar_quantity=" + sugar_quantity
				+ ", spice_constibutor=" + spice_constibutor + ", spice_quantity=" + spice_quantity + ", price=" + price
				+ "]";
	}
	
	
	

}
