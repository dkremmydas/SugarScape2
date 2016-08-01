package gr.kremmydas.sugarscape.agents;

import java.util.List;

import repast.simphony.context.RepastElement;
import repast.simphony.engine.schedule.ISchedulableAction;


/**
 * 
 * An Sugarscape agent
 * 
 * @author Dimitris Kremmydas
 *
 */
public class Agent implements RepastElement {
	
	protected Integer id;
	
	protected int ini_x, ini_y;
	
	//advanced
	List<ISchedulableAction> scheduledActions;

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

	public List<ISchedulableAction> getScheduledActions() {
		return scheduledActions;
	}

	public void setScheduledActions(List<ISchedulableAction> scheduledActions) {
		this.scheduledActions = scheduledActions;
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
