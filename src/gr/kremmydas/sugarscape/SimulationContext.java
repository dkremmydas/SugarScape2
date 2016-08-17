package gr.kremmydas.sugarscape;

import gr.kremmydas.sugarscape.agents.Agent;
import gr.kremmydas.sugarscape.landscape.Landscape;
import gr.kremmydas.sugarscape.loaders.agents.AgentLoader;
import gr.kremmydas.sugarscape.loaders.landscape.LandscapeLoader;
import gr.kremmydas.sugarscape.loaders.userPanel.Chapter2CustomUserPanel;
import gr.kremmydas.sugarscape.loaders.userPanel.CustomPanelLoader;

import java.util.List;

import org.apache.log4j.Level;

import repast.simphony.context.Context;
import repast.simphony.context.DefaultContext;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.engine.schedule.ISchedulableAction;
import repast.simphony.ui.RSApplication;
import simphony.util.messages.MessageCenter;

public class SimulationContext extends DefaultContext<Agent> implements ContextBuilder<Agent>{

	private static SimulationContext instance=null;
	
	private String landscapeLoaderRoot="gr.kremmydas.sugarscape.loaders.landscape.";
	private String agentLoaderRoot="gr.kremmydas.sugarscape.loaders.agents.";
	
	private Landscape landscape;
	
	/**
	 * The real tick of the simulation. 
	 */
	private int realTick = 0;
	
	
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
		
		LandscapeLoader ll;
		try {
			String ls =  landscapeLoaderRoot + RunEnvironment.getInstance().getParameters().getString("landscapeLoaderClass");
			ll = (LandscapeLoader) Class.forName(ls).newInstance();
			landscape = ll.load();
			sc.landscape = landscape;
			RunEnvironment.getInstance().getCurrentSchedule().schedule(landscape);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//2. load agents
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Loading Agents ...");
		
		AgentLoader al;
		try {
			String ls =  agentLoaderRoot + RunEnvironment.getInstance().getParameters().getString("agentLoaderClass");
			al = (AgentLoader) Class.forName(ls).newInstance();
			al.addAgents(sc);
			
			//add any ScheduledActions manually
			Iterable<Agent> ia = sc.getObjects(Agent.class);
			
			//advanced
			for(Agent a : ia) {
				List<ISchedulableAction> sa = RunEnvironment.getInstance().getCurrentSchedule().schedule(a);
				a.setScheduledActions(sa);
			}
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Since loading has finished, output some details on DEBUG mode
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Everything is loaded.");
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Number of Projections: " + sc.getProjections().size());
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Number of Agents: " + sc.getObjects(Agent.class).size());
		SimulationContext.logMessage(this.getClass(), Level.DEBUG, "Number of Scheduled Actions: " + RunEnvironment.getInstance().getCurrentSchedule().getActionCount());
	
		
		//3. load custom panel if not in batch mode
		if(! RunEnvironment.getInstance().isBatch()) {
		CustomPanelLoader cpl = new Chapter2CustomUserPanel();
			RSApplication.getRSApplicationInstance().addCustomUserPanel(cpl.loadPanel());
		}	
			
		
		//4. If in batch mode, terminate after 1500 ticks
		if(RunEnvironment.getInstance().isBatch()) {
			RunEnvironment.getInstance().endAt(1500);
		}
		
		
		return sc;
	}

	public Landscape getLandscape() {
		return landscape;
	}

	public int getRealTick() {
		return realTick;
	}

	public void setRealTick(int realTick) {
		this.realTick = realTick;
	}
	
	public void incrementTick() {
		this.realTick++;
	}
	
	
	
}
