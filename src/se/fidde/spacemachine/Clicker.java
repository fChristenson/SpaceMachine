package se.fidde.spacemachine;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JOptionPane;

public class Clicker implements Runnable {

    private boolean isRunning;
    private int delay;
    private int keyToPress;
    private Robot robot;

    public Clicker() throws AWTException {
        robot = new Robot();
    }

    @Override
    public void run() {
        isRunning = true;
        do {
            try {
                robot.keyPress(keyToPress);
                robot.keyRelease(keyToPress);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Ooops that button doesnt work right now!", null,
                        JOptionPane.ERROR_MESSAGE);
                isRunning = false;
                break;
            }
            robot.setAutoDelay(delay * 1000);
        } while (isRunning);
    }

    public void stopClicker() {
        isRunning = false;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public int getKeyToPress() {
        return keyToPress;
    }

    public void setKeyToPress(int keyToPress) {
        this.keyToPress = keyToPress;
    }

}