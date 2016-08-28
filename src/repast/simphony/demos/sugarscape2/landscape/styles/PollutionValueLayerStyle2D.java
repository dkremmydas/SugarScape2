package repast.simphony.demos.sugarscape2.landscape.styles;

import java.awt.Color;

import repast.simphony.valueLayer.ValueLayer;
import repast.simphony.visualizationOGL2D.ValueLayerStyleOGL;

public class PollutionValueLayerStyle2D implements ValueLayerStyleOGL {
	
	protected ValueLayer layer;

	public PollutionValueLayerStyle2D() {
	}

	/**
	 * The following link is helpful for understanding how to create a 
	 * HEATMAP (color proportional to a 0-1 range value): <br />
	 * http://www.andrewnoske.com/wiki/Code_-_heatmaps_and_color_gradients
	 */
	@Override
	public Color getColor(double... coordinates) {
		double q = layer.get(coordinates);
		float qp = (float)(q/50);
		if(qp>0.99) {qp=1f;}
		return new Color((int)(qp*255), (int)(qp*255), (int)(qp*255));
	}

	@Override
	public float getCellSize() {
		return 15f;
	}

	@Override
	public void init(ValueLayer layer) {
		this.layer = layer;
	}

}
