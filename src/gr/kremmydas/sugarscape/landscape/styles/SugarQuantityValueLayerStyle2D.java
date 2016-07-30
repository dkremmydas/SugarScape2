package gr.kremmydas.sugarscape.landscape.styles;

import java.awt.Color;

import repast.simphony.valueLayer.ValueLayer;
import repast.simphony.visualizationOGL2D.ValueLayerStyleOGL;

public class SugarQuantityValueLayerStyle2D implements ValueLayerStyleOGL {
	
	protected ValueLayer layer;

	public SugarQuantityValueLayerStyle2D() {
	}

	@Override
	public Color getColor(double... coordinates) {
		int q = (int) layer.get(coordinates);
		return Color.getHSBColor(21/(q+1), 21/(q+1), 0.8f);
		Col
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
