package repast.simphony.demos.sugarscape2.utilities;


/**
 * Classes that implement this interface provide a method that initiate the configuration that uses the 
 * Repast Simphony's environment.
 * 
 * @author Dimitris Kremmydas
 *
 */
public interface ConfigurableFromRepastEnvironment {
	
	/**
	 * In this method, the Repast Simphony environmental variables should be used
	 * in order to initialize the internal state of an object 
	 */
	public void configureFromEnvironment();

}
