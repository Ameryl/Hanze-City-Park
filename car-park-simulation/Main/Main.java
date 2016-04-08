package Main;

import Controller.SimulatorController;

import Model.SimulatorModel;
import View.SimulatorView;

public class Main{
    public static void main(String[]args)
    {
        SimulatorController sim = new SimulatorController(new SimulatorView(3, 6, 30), new SimulatorModel());

    }
}