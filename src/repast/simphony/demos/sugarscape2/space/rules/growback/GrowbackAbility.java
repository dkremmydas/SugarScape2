package repast.simphony.demos.sugarscape2.space.rules.growback;

import repast.simphony.demos.sugarscape2.space.SugarSpace_ch2;
import repast.simphony.valueLayer.GridValueLayer;

/**
 * Computes the new {@link GridValueLayer}, after the growback rule has been applied.
 * 
 * The name of the {@link GridValueLayer} is variant. That means that the {@link GrowbackAbility}
 * does not explicitly refer to a specific resource (e.g. sugar, spice, etc.).
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface GrowbackAbility {

	/**
	 * Compute the new {@link GridValueLayer} of the resource, after the growback
	 * has taken place
	 * 
	 * @param s the SugarSpace agent
	 * @return
	 */
	public GridValueLayer growback(SugarSpace_ch2 s);
	
	

}
