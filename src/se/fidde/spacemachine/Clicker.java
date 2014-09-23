package se.fidde.spacemachine;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Clicker implements Runnable {

    public boolean loopFlag;
    private Robot robot;

    public Clicker() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        loopFlag = true;
        do {
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            System.out.println("Pressed space...");
            robot.setAutoDelay(10000);
        } while (loopFlag);
    }

}