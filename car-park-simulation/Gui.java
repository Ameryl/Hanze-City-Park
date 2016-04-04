import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Created by vincent on 1-4-2016.
 */
public class Gui {

    private JFrame frame;

    public Gui() {

        makeFrame();


    }

    private void makeFrame()
    {
        frame = new JFrame("ImageViewer");
        makeMenuBar(frame);

        Container contentPane = frame.getContentPane();

        JLabel label = new JLabel("I am a label. I can display some text.");
        contentPane.add(label);

        // building is done - arrange the components and show
        frame.pack();
        frame.setVisible(true);
    }

    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // create the File menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);

        JMenuItem runSimulator = new JMenuItem("RunSimulator");
        runSimulator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                runsim();
            }
        });
        fileMenu.add(runSimulator);


        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {  }
        });
        fileMenu.add(quitItem);
    }
    //kutniek
    private void quit()
    {
        System.exit(0);
    }

    public void runsim()
    {
        try {
            Simulator sim = new Simulator();
            sim.run();
        }

        catch(Exception ex) {
            System.out.println(ex.toString());
        }
    }
    }

