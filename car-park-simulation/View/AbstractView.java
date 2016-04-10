package View;

import Logic.*;

import javax.swing.*;

public abstract class AbstractView extends JPanel {
	protected AbstractModel sim;

	public AbstractView(AbstractModel model) {
		this.sim = model;
		model.addView(this);

		setLayout(null);
	}

	public void updateView() {
		repaint();
	}

}
