package gr.kremmydas.sugarscape.agents;

/**
 * Genetic Characteristics (p. 23-24)
 * 
 * @author Dimitris Kremmydas
 *
 */
public class AgentGeneticCharacteristics {
	
	/**
	 * Level of vision
	 */
	int visionLevel;
	
	/**
	 * Initial x,y position
	 */
	int ini_x, ini_y;
	

	public AgentGeneticCharacteristics(int visionLevel, int x, int y) {
		super();
		this.visionLevel = visionLevel;
		this.ini_x=x; this.ini_y=y;
	}


	public int getVisionLevel() {
		return visionLevel;
	}

	public void setVisionLevel(int visionLevel) {
		this.visionLevel = visionLevel;
	}


	public int getIni_x() {
		return ini_x;
	}


	public int getIni_y() {
		return ini_y;
	}
	
	

}
