package repast.simphony.demos.sugarscape2.agents.rules.trade;

import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch4;
import repast.simphony.random.RandomHelper;

public class DefaultTrade implements TradeAbility {




	@Override
	public SugarAgent_ch4 selectPartner(SugarAgent_ch4 a) {
		
		Set<SugarAgent_ch2> n = a.getNeighboringSugarAgents();
		
		int select_index = RandomHelper.nextIntFromTo(1, n.size());
		
		int i = 1;
		for(SugarAgent_ch2 obj : n)
		{
		    if (i == select_index)
		        return (SugarAgent_ch4) obj;
		    i++;
		}

		return null;
		
	}

	
	

	@Override
	public TradeTransaction doTrade(SugarAgent_ch4 a1, SugarAgent_ch4 a2) {

		SugarAgent_ch4 sugar_contributor, spice_contributor;
		TradeTransaction r = null;


		Double unitary_price;

		//compute MRS, if equal stop, else find who gives spice/take sugar and who gives sugar/takes spice
		if(computeMRS(a1).equals(computeMRS(a2))) {
			return r;
		} 
		else if ( computeMRS(a1).compareTo(computeMRS(a2))<0) {
			sugar_contributor = a1; 
			spice_contributor = a2; 
		} else {
			sugar_contributor = a2; 
			spice_contributor = a1; 
		}

		//find unitary price
		unitary_price = Math.sqrt(computeMRS(a1)*computeMRS(a2));

		//
		if(unitary_price.compareTo(1.0d)==0) {
			return r;
		}



		//determine actual quantities
		double sug_con_welf_change,spice_con_welf_change;
		int q=1;



		for(q=1; q<=sugar_contributor.resourceGetHolding("sugar"); q++) {


			int sugar_given, spice_given;
			
			if(unitary_price.compareTo(1.0d)>0) { 
				sugar_given = q;
				spice_given = (int)Math.floor(q*unitary_price);	
				
			} else {
				
				sugar_given = q;
				spice_given = (int)Math.floor(q*(1/unitary_price));	
				
			}
			
			
			sug_con_welf_change = welfareChange(sugar_contributor,-1*sugar_given,spice_given);
			spice_con_welf_change = welfareChange(spice_contributor,sugar_given,-1*spice_given);

			if( ! ((spice_con_welf_change>0) & (sug_con_welf_change>0))) {break;} 
			
			//check MRS crossing
			if(computeMRS(sugar_contributor)>computeMRS(spice_contributor)) {	
				if ( (computeMRS(sugar_contributor)+sug_con_welf_change) < (computeMRS(spice_contributor)+spice_con_welf_change) ) {break;}
			}
			else {
				if ( (computeMRS(sugar_contributor)+sug_con_welf_change) > (computeMRS(spice_contributor)+spice_con_welf_change) ) {break;}
			}
				
			//check that trade quantities actually exist for agents			
			if(spice_given > spice_contributor.resourceGetHolding("spice")) {break;}
			
			
		}

		if(q>1) {
			r = new TradeTransaction(sugar_contributor, q-1, spice_contributor, (int) Math.floor((q-1)*unitary_price), unitary_price);
		}



		return r;


	}





	private double welfareChange(SugarAgent_ch4 a, int sugar_given, int spice_given) {

		double welf_bef =  (int) (a.getWelfareAbility().estimateWelfare(a, 
				a.resourceGetHolding("sugar"), 
				a.resourceGetHolding("spice")));
		double welf_aft =  (int) (a.getWelfareAbility().estimateWelfare(a, 
				(int) (a.resourceGetHolding("sugar")+sugar_given), 
				(int) (a.resourceGetHolding("spice")+spice_given)));

		return (welf_aft-welf_bef);
	}



	private Double computeMRS(SugarAgent_ch4 a) {

		double t2 = a.resourceGetHolding("spice")/a.resourceGetMetabolism("spice");

		double t1 = a.resourceGetHolding("sugar")/a.resourceGetMetabolism("sugar");

		return t2/t1;

	}



}
