package repast.simphony.demos.sugarscape2.utilities;

import java.util.Random;

import repast.simphony.valueLayer.GridValueLayer;

public class Utility {
	
	public static enum TypeOfVision {  MOORE, NEUMMAN }

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
	
	
}
