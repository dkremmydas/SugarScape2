package gr.kremmydas.sugarscape.landscape;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.valueLayer.ValueLayer;
import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.rules.growback.GrowbackAbility;
import gr.kremmydas.sugarscape.products.ProductGridProperties;

public class LandscapeChapter2_p30 extends Landscape {
	
	/**
	 * The properties of sugar
	 */
	ProductGridProperties sugarGridProperties;
	
	/**
	 * The growback rule
	 */
	GrowbackAbility growbackRule;
	

	public LandscapeChapter2_p30() {
		super();		
	}

	@Override
	public void init(int x, int y) {
		super.init(x, y);
		this.sugarGridProperties = new ProductGridProperties(x, y, "sugar");
	}



	public ProductGridProperties getSugarGridProperties() {
		return sugarGridProperties;
	}
	

	public GrowbackAbility getGrowbackRule() {
		return growbackRule;
	}


	public void setGrowbackRule(GrowbackAbility growbackRule) {
		this.growbackRule = growbackRule;
	}
	
	/**
	 * The sugarscape growsback <br/>
	 * Before I just set sugarGridProperties.getCurrentQuantity() = {new value layer} but it did not work.
	 * I had to copy value-by-value the contents of the second valuelayer to the first
	 */
	@ScheduledMethod(start=4d,interval=5d)
	public void growback() {
		//System.out.println("Before Growback: " + this.sugarGridProperties.getQuantityDescriptiveStats());
		
		ValueLayer newvl = this.growbackRule.growback(this);
		for(int i=0;i<sugarGridProperties.getCurrentQuantity().getDimensions().getWidth();i++) {
			for(int j=0;j<sugarGridProperties.getCurrentQuantity().getDimensions().getHeight();j++) {
				sugarGridProperties.getCurrentQuantity().set(newvl.get(i,j), i,j);
			}
		}
		
		//this.sugarGridProperties.setCurrentQuantity();
		//System.out.println("After Growback: " + this.sugarGridProperties.getQuantityDescriptiveStats());
	}
	
	public void removeSugar(Agent a, int q) {
		GridPoint gp = this.grid.getLocation(a);
		int nq = (int) this.sugarGridProperties.getCurrentQuantity().get(gp.getX(),gp.getY())-q;
		this.sugarGridProperties.getCurrentQuantity().set(nq, gp.getX(),gp.getY());
	}
	
	public double getTotalSugar() {
		double r = this.sugarGridProperties.getQuantityDescriptiveStats().getMean();
		System.out.println("S:" + r);
		return r;
	}


}
