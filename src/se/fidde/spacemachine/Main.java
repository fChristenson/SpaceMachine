package se.fidde.spacemachine;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Main {

    private static final String RUNNING = "Running...";
    private static final String STOPPED = "Stopped!";

    private static Clicker clicker;

    public static void main(String[] args) throws AWTException,
            InterruptedException {

        // create our clicker
        clicker = new Clicker();

        final JPanel panel = new JPanel();

        // add label and button input field

        JPanel panel3 = new JPanel();
        panel3.add(new Label("Please enter the button you want to be pressed."));
        panel.add(panel3);

        final JFormattedTextField textField = new JFormattedTextField();
        textField.setBackground(Color.WHITE);
        textField.setPreferredSize(new Dimension(100, 20));
        textField.setMaximumSize(new Dimension(100, 20));

        JPanel panel4 = new JPanel();
        panel4.add(textField);
        panel.add(panel4);

        // add label and number spinner
        JPanel panel5 = new JPanel();
        panel5.add(new Label("Please select the delay for each click."));
        panel.add(panel5);

        JPanel panel6 = new JPanel();
        final JSpinner jSpinner = new JSpinner();
        jSpinner.setModel(new SpinnerNumberModel(1, 1, 60, 1));
        jSpinner.setPreferredSize(new Dimension(40, 20));
        panel6.add(jSpinner);
        panel6.add(new JLabel("seconds"));
        panel.add(panel6);

        // add start button
        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Integer valueInteger = (Integer) jSpinner.getValue();
                clicker.setDelay(valueInteger);
                clicker.setKeyToPress((int) textField.getText().toUpperCase()
                        .charAt(0));

                if (!clicker.isRunning()) {
                    Executors.newSingleThreadExecutor().execute(clicker);
                }

            }
        });
        JPanel panel2 = new JPanel();
        panel2.add(startBtn);

        // add a stop button
        JButton stopBtn = new JButton("Stop");
        stopBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clicker.stopClicker();
            }
        });
        panel2.add(stopBtn);
        panel.add(panel2);

        // create frame and set visible true
        JFrame frame = new JFrame("SpaceMachine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clicker.stopClicker();
                ;
            }
        });

        frame.setResizable(false);
        frame.setSize(300, 200);

        frame.add(panel);
        frame.setVisible(true);

    }
}
