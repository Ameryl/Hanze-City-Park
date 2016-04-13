package View;

import Logic.*;
import javax.swing.*;

public abstract class AbstractView extends JPanel {

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
