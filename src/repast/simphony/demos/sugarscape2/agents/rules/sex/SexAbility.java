package repast.simphony.demos.sugarscape2.agents.rules.sex;

import java.util.Map;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.space.grid.GridPoint;

public interface SexAbility {
	
	/**
	 * Selection of the {@link SugarAgent_ch2}s to mate with. The mates returned should be the fertile neighbors.
	 * In this method we do not care whether the potential mates have the resources to have children or not.
	 * 
	 * @param a The {@link SugarAgent_ch2} that will perform the selection
	 * @return An {@link Iterable} of {@link SugarAgent_ch2} that have been selected for mating
	 */
	Iterable<SugarAgent_ch3> selectPotentialMates(SugarAgent_ch3 a);
	
	
	
	/**
	 * The mating procedure. The responsibilities of this method are:
	 * <ul> 
	 * <li>Check that the resources for having a child is enough for both parents/li>
	 * <li>Create the {@link SugarAgent_ch3} child</i>
	 * <li>Find the {@link GridPoint} location where it should be born</li>
	 * <li>Remove any resources from the parents and transfer them to the child</li>
	 * </ul>
	 * 
	 * @param a1 the {@link SugarAgent_ch2} that is the calling object of the mating
	 * @param a2 the {@link SugarAgent_ch2} that is the second part of the mating
	 * @return A map that contains the {@link SugarAgent_ch2} child and the position it should be located. In case no child can be born, return null.
	 */
	Map<SugarAgent_ch3,GridPoint> giveBirth(SugarAgent_ch3 a1, SugarAgent_ch3 a2);
	
	
	
	/**
	 * In this method, the Repast Simphony environmental variables should be used
	 * in order to give values to the object 
	 */
	public void configureFromEnvironment();

}
