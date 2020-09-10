package repast.simphony.demos.sugarscape2.agents.styles;

import java.awt.Color;

import repast.simphony.demos.sugarscape2.agents.SugarAgent_ch3;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;

public class AgentStyleChapter3_p79 extends DefaultStyleOGL2D {

	/**
	 * The following link is helpful for understanding how to create a 
	 * HEATMAP (color proportional to a 0-1 range value): <br />
	 * http://www.andrewnoske.com/wiki/Code_-_heatmaps_and_color_gradients
	 */
	@Override
	public Color getColor(Object agent) {

		try {

			SugarAgent_ch3 aa = (SugarAgent_ch3) agent;
			
			if(aa.getCulturalAbility().cultureGroup(aa).equalsIgnoreCase("Blue")) {
				return new Color(20,20,255);
			} else {
				return new Color(255,0,0);
			}

		} catch (ClassCastException e) {}

		return new Color(120,120,120);
	}

	@Override
	public Color getBorderColor(Object agent) {


		try {

			SugarAgent_ch3 aa = (SugarAgent_ch3) agent;
			if(aa.getSex().equals(SugarAgent_ch3.Sex.MALE)) {
				return new Color(255,255,255);
			} 

		} catch (ClassCastException e) {}

		return super.getBorderColor(agent);	  

	}

	@Override
	public int getBorderSize(Object agent) {

		try {

			SugarAgent_ch3 aa = (SugarAgent_ch3) agent;
			if(aa.getSex().equals(SugarAgent_ch3.Sex.MALE)) {
				return 2;
			} 

		} catch (ClassCastException e) {}

		return super.getBorderSize(agent);
	}







}
