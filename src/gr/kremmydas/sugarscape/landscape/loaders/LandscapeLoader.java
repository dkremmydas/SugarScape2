package gr.kremmydas.sugarscape.landscape.loaders;

import gr.kremmydas.sugarscape.landscape.Landscape;

/**
 * A class that loads a {@link Landscape} should implement this interface
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface LandscapeLoader {
	
	/**
	 * The load function should cater for the following things: <br />
	 * <ul>
	 * <li>Set any properties of the individual landscape cells</li>
	 * <li>Set any rules for the landscape (e.g. growback on chapter II)</li>
	 * </ul>
	 * @return the loaded  {@link Landscape}
	 */
	public Landscape load();
	
}
