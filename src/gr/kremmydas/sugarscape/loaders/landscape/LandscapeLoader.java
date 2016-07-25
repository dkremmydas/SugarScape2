package gr.kremmydas.sugarscape.loaders.landscape;

import gr.kremmydas.sugarscape.landscape.Landscape;

public interface LandscapeLoader {
	
	/**
	 * Returns a {@link Landscape}
	 * @return
	 */
	public Landscape load();
	
}
