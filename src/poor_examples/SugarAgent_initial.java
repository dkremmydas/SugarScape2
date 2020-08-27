package poor_examples;

public class SugarAgent_initial {


	int metabolism;           // how much sugar eaten per turn
	int sugar_initial;	      // the initial endowment of sugar
	int sugar;                // amount of sugar owned
	int vision;               // how far can see


	public void ruleM() {
		//implementation of the algorithm, as described in the book
		
		//Step 1: Look out as far as vision permits and identify the 
		//			unoccupied sites
		
		//Step 2: Select the site with the most sugar. If two or more
		//          sites have the maximum value of sugar, select the
		//			nearest site
		
		//Step 3: Move to this site
		
		//Step 4: Collect all the sugar at this new position
	}
	
	
	
	public void survive() {
		
		//Step 1: Increment the accumulated sugar by the amount collected
		
		//Step 2: Decrement the accumulated sugar by the amount eaten
		
		//Step 3: If the accumulated sugar falls below zero, the agent dies
		
	}
	
	
	
	
	
} //end of class



