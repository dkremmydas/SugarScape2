package gr.kremmydas.sugarscape.loaders.landscapes;

import gr.kremmydas.sugarscape.landscape.Landscape;

public interface LandscapeLoader {
	
	/**
	 * Returns a {@link Landscape}
	 * @return
	 */
	public Landscape load();
	
}
