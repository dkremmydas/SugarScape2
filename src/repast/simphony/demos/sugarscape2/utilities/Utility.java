package repast.simphony.demos.sugarscape2.utilities;

import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import repast.simphony.valueLayer.GridValueLayer;

public class Utility {
	
	public static enum TypeOfVision {  MOORE, NEUMMAN }
	
	
	static Logger logger = Logger.getLogger("Sugarscape2");
	
	
	

	/**
	 * Write log messages to log4j
	 * 
	 * @param clazz the {@link Class} of the object that writes to the log
	 * @param level {@link Level} of the message
	 * @param message The message itself
	 */
	public static void logMessage(Level level,Object message) {
		
		if(level.equals(Level.DEBUG)) {
			logger.debug(message);
		}
		else if (level.equals(Level.INFO)) {
			logger.info(message);
		}
		
	}
	
	
	

	/**
	 * Get a random string with certain length
	 * 
	 * Copies from https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
	 * 
	 * @param length
	 * @return String
	 */
	public static String getRandomString(int length) {

	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < length) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;
	}
	
	
	
	/**
	 * Replace the values of a {@link GridValueLayer} with those of another one
	 * @param gvl_old The {@link GridValueLayer} to update
	 * @param gvl_new The {@link GridValueLayer} to take values from
	 */
	public static void updateGridValueLayer(GridValueLayer gvl_old, GridValueLayer gvl_new) {
		
		int x,y;
		
		for( x=0; x< gvl_old.getDimensions().getWidth();x++) {
			for( y=0; y< gvl_old.getDimensions().getHeight();y++) {
				gvl_old.set(gvl_new.get(x,y), x,y);
			}
		}
		
	}
	
	
	
	public static GridValueLayer cloneGridValueLayer(GridValueLayer gvl) {
		
		GridValueLayer gvl_clone = new GridValueLayer(gvl.getName() + "_clone", true, (int)gvl.getDimensions().getWidth(),(int)gvl.getDimensions().getHeight());
		
		int x,y;
		
		for( x=0; x< gvl.getDimensions().getWidth();x++) {
			for( y=0; y< gvl.getDimensions().getHeight();y++) {
				gvl_clone.set(gvl.get(x,y), x,y);
			}
		}
		
		
		return gvl_clone;
		
		
	}
	
	
}
