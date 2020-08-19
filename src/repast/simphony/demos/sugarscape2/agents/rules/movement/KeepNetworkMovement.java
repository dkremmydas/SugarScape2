package repast.simphony.demos.sugarscape2.agents.rules.movement;

import java.util.HashSet;
import java.util.Set;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.demos.sugarscape2.utilities.NeighbourhoodFunctions;
import repast.simphony.demos.sugarscape2.utilities.Utility;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.space.graph.Network;
import repast.simphony.space.graph.RepastEdge;
import repast.simphony.space.grid.GridPoint;


/**
 * Move to the point with the more resource
 * 
 * @author Dimitris Kremmydas
 *
 */
public class KeepNetworkMovement extends DefaultMovement {
	

	/**
	 * Moore or von-Neumman
	 */	
	protected Utility.TypeOfVision typeOfVision;


	public KeepNetworkMovement(String valueLayerName) {
		super(valueLayerName);
	}

	private Set<SugarAgent_ch2> neighbors = new HashSet<SugarAgent_ch2>();


	@Override
	public GridPoint move(SugarAgent_ch2 a,Set<GridPoint> gs) {

		GridPoint gps_old = a.getCurrentPosition();
		
		//find neighbors of old position
		setNeighbors(a, gps_old);
		updateNetwork(a);
		
		//3. Return the GridPoint that is higher in the list
		//	 Since the points passed to the method contain only empty GridPoints, we do not check for emptiness
		return super.move(a, gs);
	}


	private void setNeighbors(SugarAgent_ch2 a,GridPoint gp) {
		this.neighbors.clear();

		Iterable<GridPoint> points_neigh;
		
		if(this.typeOfVision==Utility.TypeOfVision.MOORE) {
			points_neigh = NeighbourhoodFunctions.getMoorePoints(gp, a.getContext().getGrid(), 1);
		} else {
			points_neigh = NeighbourhoodFunctions.getVonNeumanPoints(gp, a.getContext().getGrid(), 1);
		}


		for(GridPoint gpp: points_neigh ) {

			Iterable<Object> objs = a.getContext().getGrid().getObjectsAt(gpp.getX(),gpp.getY());

			for(Object obj: objs) {
				if(obj.getClass().equals(SugarAgent_ch2.class)) {
					SugarAgent_ch2 aa = (SugarAgent_ch2) obj;
					if(aa.isAlive()) {
						this.neighbors.add(aa);
					}

				}
			}

		}

	}


	private void updateNetwork(SugarAgent_ch2 a) {

		Network<Object> net = (Network<Object>) a.getContext().getProjection("neigbours");

		//delete old edges
		Iterable<RepastEdge<Object>> dai = net.getOutEdges(a);
		Set<RepastEdge<Object>> str = new HashSet<>();
		for(RepastEdge<Object> da: dai) {
			str.add(da);
		}
		for(RepastEdge<Object> da: str) {
			net.removeEdge(da);
		}

		//build new edges (for the point where the agent is
		for(SugarAgent_ch2 aa: this.neighbors ) {
			if(! aa.equals(a)) net.addEdge(a, aa);
		}
	}
	
	
	
	
	@Override
	public void configureFromEnvironment() {	
		
		super.configureFromEnvironment();
		
		String typeOfVision_str = RunEnvironment.getInstance().getParameters().getString("VisionType");
		
		typeOfVision = (typeOfVision_str.equalsIgnoreCase("Moore"))?Utility.TypeOfVision.MOORE:Utility.TypeOfVision.NEUMMAN;
		
	}







}