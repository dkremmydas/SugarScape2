package gr.kremmydas.sugarscape.agents;

import repast.simphony.context.RepastElement;


/**
 * 
 * An Sugarscape agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public class Agent implements RepastElement {
	
	private int id;
	
	private int ini_x, ini_y;

	public Agent() {};
	
	

	public int getIni_x() {
		return ini_x;
	}

	public void setIni_x(int ini_x) {
		this.ini_x = ini_x;
	}

	public int getIni_y() {
		return ini_y;
	}

	public void setIni_y(int ini_y) {
		this.ini_y = ini_y;
	}

	@Override
	public void setId(Object id) {
		this.id = Integer.valueOf(id.toString());
	}

	@Override
	public Object getId() {
		return this.id;
	}

	
	

}
