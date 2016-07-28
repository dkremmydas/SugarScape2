package gr.kremmydas.sugarscape;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.loaders.agents.AgentLoader;
import gr.kremmydas.sugarscape.loaders.agents.ExcelAgentLoader;
import gr.kremmydas.sugarscape.loaders.landscape.ExcelLandscapeLoader;
import gr.kremmydas.sugarscape.loaders.landscape.LandscapeLoader;

import org.apache.log4j.Level;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import simphony.util.messages.MessageCenter;

public class SimulationContext extends DefaultContext<Agent> implements ContextBuilder<Agent>{

	private static SimulationContext instance=null;
	
	
	private Landscape landscape;
	
	
	public static SimulationContext getInstance() {
		if (SimulationContext.instance==null) {SimulationContext.instance=new SimulationContext();}
		return SimulationContext.instance;
	}
	
	/**
	 * Private Constructor, so the existence of a unique instance of MainContext is enforced. 
	 */
	public SimulationContext() {
		super("SimulationContext","SimulationContext");
		SimulationContext.instance = this;
	}
	
	public static void logMessage(Class<?> clazz, Level level,Object message) {
		MessageCenter mc = MessageCenter.getMessageCenter(clazz);
		mc.fireMessageEvent(level, message, null);
	}
	
	/**
	 * It builds the Contexts of Agroscape. <br />
	 * The steps that this method does, are: <br />
	 * 1. //TODO complete documentation
	 * 
	 */
	@Override
	public Context<Agent> build(Context<Agent> context) {
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Start building SimulationContext");
		SimulationContext sc = new SimulationContext();
		
		//1. Load Landscape
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Loading Landscape ...");
		LandscapeLoader ll = new ExcelLandscapeLoader();
		landscape = ll.load();
		sc.landscape = landscape;
		RunEnvironment.getInstance().getCurrentSchedule().schedule(landscape);
		
		//2. load agents
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Loading Agents ...");
		AgentLoader al = new ExcelAgentLoader();
		al.addAgents(sc);
		Iterable<Agent> ia = sc.getObjects(Agent.class);
		for(Agent a : ia) {RunEnvironment.getInstance().getCurrentSchedule().schedule(a);}
		
		System.out.println("Number of Projections: " + sc.getProjections().size());
		System.out.println("Number of Agents: " + sc.getObjects(Agent.class).size());
		System.out.println("Number of Scheduled Actions: " + RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
		
		return sc;
	}

	public Landscape getLandscape() {
		return landscape;
	}
	
	
	
}
