package Main;

import Controller.SimulatorController;
import View.SimulatorView;

import Model.SimulatorModel;

public class Main{
    public static void main(String[]args)
    {
        SimulatorController sim = new SimulatorController(new SimulatorView(3, 6, 30), new SimulatorModel());
    }
}