package se.fidde.spacemachine;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

    private static final String RUNNING = "Running...";
    private static final String STOPPED = "Stopped!";
    private static Clicker clicker;
    private static final Label label = new Label(
            "Press start to start auto space clicks");

    public static void main(String[] args) throws AWTException,
            InterruptedException {

        // create panel and add some instruction text
        JPanel panel = new JPanel(new BorderLayout());
        label.setAlignment(Label.CENTER);
        panel.add(label, BorderLayout.CENTER);

        // create our clicker
        clicker = new Clicker();

        // make a start button
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText(RUNNING);
                System.out.println(RUNNING);

                // hit space every 10 sec
                Executors.newSingleThreadExecutor().execute(clicker);

            }
        });
        panel.add(startButton, BorderLayout.NORTH);

        // make a stop button
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clicker.loopFlag = false;
                label.setText(STOPPED);
                System.out.println(STOPPED);
            }
        });
        panel.add(stopButton, BorderLayout.SOUTH);

        // create frame and set visible true
        JFrame frame = new JFrame("SpaceMachine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clicker.loopFlag = false;
            }
        });

        frame.setResizable(false);
        frame.setSize(300, 150);

        frame.add(panel);
        frame.setVisible(true);

    }

}
