package Controller;

import Logic.SimulatorModel;

import javax.swing.*;

public abstract class AbstractController extends JPanel {
	protected SimulatorModel sim;
	
	public AbstractController(SimulatorModel sim) {
		this.sim=sim;
	}
}
