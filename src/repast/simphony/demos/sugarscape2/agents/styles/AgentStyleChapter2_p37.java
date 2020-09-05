package repast.simphony.demos.sugarscape2.agents.styles;

import java.awt.Color;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch2;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class AgentStyleChapter2_p37 extends DefaultStyleOGL2D {

	/**
	 * The following link is helpful for understanding how to create a 
	 * HEATMAP (color proportional to a 0-1 range value): <br />
	 * http://www.andrewnoske.com/wiki/Code_-_heatmaps_and_color_gradients
	 */
	@Override
	public Color getColor(Object agent) {
		double maxthesh=250d;
		SugarAgent_ch2 ag = (SugarAgent_ch2) agent;
		float value = Math.min(Math.max((float)(ag.resourceGetHolding("sugar")/maxthesh),0f), 1f);
		//System.out.println(value);
		
		int aR = 0;   int aG = 0; int aB=255;  // RGB for our 1st color (blue in this case).
		int bR = 255; int bG = 0; int bB=0;    // RGB for our 2nd color (red in this case).
		 
		int red   = (int) ((float)(bR - aR) * value + (float)aR);      // Evaluated as -255*value + 255.
		int green = (int) ((float)(bG - aG) * value + (float)aG);      // Evaluates as 0.
		int blue  = (int) ((float)(bB - aB) * value + (float)aB);      // Evaluates as 255*value + 0.
		  
		return new Color(red,green,blue);
	}

	


}
