package View;

import Logic.*;
import javax.swing.*;

public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = -2767764579227738552L;
	protected SimulatorModel sim;

	public AbstractView(SimulatorModel model) {
		this.sim=model;
		model.addView(this);
	}

	public SimulatorModel getModel() {
		return sim;
	}

	public void updateView() {
		repaint();
	}
}
