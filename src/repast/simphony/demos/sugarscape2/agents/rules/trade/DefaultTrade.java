package repast.simphony.demos.sugarscape2.agents.rules.trade;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;

public class DefaultTrade implements TradeAbility {

	//we assume w1 = sugar, w2 = spice

	@Override
	public Set<TradeTransaction> doTrade(SugarAgent_ch4 a1, SugarAgent_ch4 a2) {

		HashSet<TradeTransaction> r = new HashSet<TradeTransaction>();

		SugarAgent_ch4 sugar_contributor, spice_contributor, sugar_recipient, spice_recipient;

		Double sugar_given;
		Double spice_given;

		Double price;

		//compute MRS, if equal stop, else find who gives spice/take sugar and who gives sugar/takes spice
		if(computeMRS(a1).equals(computeMRS(a2))) {
			return r;
		} 
		else if ( computeMRS(a1).compareTo(computeMRS(a2))<0) {
			sugar_contributor = a1; spice_recipient = a1;
			spice_contributor = a2; sugar_recipient = a2;
		} else {
			sugar_contributor = a2; spice_recipient = a2;
			spice_contributor = a1; sugar_recipient = a1;
		}

		//find price
		price = Math.sqrt(computeMRS(a1)*computeMRS(a2));

		//quantities to exchange
		if(price.compareTo(1.0d)>0) {
			sugar_given=1d; spice_given=price;
		} 
		else if (price.compareTo(1.0d)<0) {
			sugar_given=1/price; spice_given=1d;
		}
		else {
			return r;
		}

		//check if this increases agents' welfare
		double sug_con_welf_bef =  (int) (sugar_contributor.getWelfareAbility().estimateWelfare(sugar_contributor, 
				sugar_contributor.resourceGetHolding("sugar"), 
				sugar_contributor.resourceGetHolding("spice")));
		double sug_con_welf_aft =  (int) (sugar_contributor.getWelfareAbility().estimateWelfare(sugar_contributor, 
				(int) (sugar_contributor.resourceGetHolding("sugar")-sugar_given), 
				(int) (sugar_contributor.resourceGetHolding("spice")+spice_given)));

		double sug_con_welf_change = sug_con_welf_aft - sug_con_welf_bef;
		
		
		double spic_con_welf_bef =  (int) (spice_contributor.getWelfareAbility().estimateWelfare(spice_contributor, 
				spice_contributor.resourceGetHolding("sugar"), 
				spice_contributor.resourceGetHolding("spice")));
		double spic_con_welf_aft =  (int) (spice_contributor.getWelfareAbility().estimateWelfare(spice_contributor, 
				(int) (spice_contributor.resourceGetHolding("sugar")+sugar_given), 
				(int) (spice_contributor.resourceGetHolding("spice")-spice_given)));

		double spice_con_welf_change = spic_con_welf_aft - spic_con_welf_bef;
		
		if( (spice_con_welf_change>0) & (sug_con_welf_change>0)) {
			
			//Find trading quantity
			
		}

		return r;

	}



	private Double computeMRS(SugarAgent_ch4 a) {

		double t2 = a.resourceGetHolding("spice")/a.resourceGetMetabolism("spice");

		double t1 = a.resourceGetHolding("sugar")/a.resourceGetMetabolism("sugar");

		return t2/t1;

	}

}
