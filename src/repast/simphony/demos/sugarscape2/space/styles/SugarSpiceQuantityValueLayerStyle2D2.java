package repast.simphony.demos.sugarscape2.space.styles;

import java.awt.Color;

import repast.simphony.demos.sugarscape2.builders.SugarSpaceFactory;
import repast.simphony.valueLayer.ValueLayer;
import repast.simphony.visualizationOGL2D.ValueLayerStyleOGL;

public class SugarSpiceQuantityValueLayerStyle2D2 implements ValueLayerStyleOGL {
	
	

	public SugarSpiceQuantityValueLayerStyle2D2() {
	}

	/**
	 * The following link is helpful for understanding how to create a 
	 * HEATMAP (color proportional to a 0-1 range value): <br />
	 * http://www.andrewnoske.com/wiki/Code_-_heatmaps_and_color_gradients
	 */
	@Override
	public Color getColor(double... coordinates) {
		
		double sugar = SugarSpaceFactory.getSugarspace().resourceGetHoldingAtXY("sugar", (int)coordinates[0], (int)coordinates[1]);
		
		double spice = SugarSpaceFactory.getSugarspace().resourceGetHoldingAtXY("spice", (int)coordinates[0], (int)coordinates[1]);
		
		int qp23; int qp1;
		
		if((sugar/(sugar+spice)>.7)) {
			
			qp1=20;
			qp23 = (int)Math.min(sugar*12,255);
			
		} 
		else  if((sugar/(sugar+spice)<.3)) {
			
			qp1=170;
			qp23 = (int)Math.min(sugar*12,255);
			
		}else {
			qp1=50;
			qp23 = (int)Math.min(((sugar+spice)/2)*12,255);
		}
		

		
		return new Color(qp1,qp23,qp23);
		
	}

	@Override
	public float getCellSize() {
		return 15f;
	}

	@Override
	public void init(ValueLayer layer) {
		
	}

}
