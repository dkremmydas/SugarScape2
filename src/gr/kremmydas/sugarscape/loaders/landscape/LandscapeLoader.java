package gr.kremmydas.sugarscape.loaders.landscape;

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
	 * <li></li>
	 * </ul>
	 * @return the loaded  {@link Landscape}
	 */
	public Landscape load();
	
}
