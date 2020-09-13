package repast.simphony.demos.sugarscape2.space.styles;

import java.awt.Color;

import repast.simphony.valueLayer.ValueLayer;
import repast.simphony.visualizationOGL2D.ValueLayerStyleOGL;

public class CombatFrequencyValueLayerStyle2D implements ValueLayerStyleOGL {

	protected ValueLayer layer;

	public CombatFrequencyValueLayerStyle2D() {
	}


	@Override
	public Color getColor(double... coordinates) {
		double q = layer.get(coordinates);

		//return new Color(0, 0, 255*(int)(q/DefaultCombat.total_num_of_combats));
		return new Color(
				0, 
				Math.min(15*(int)(q),255),
				Math.min(15*(int)(q),255)
				);
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
