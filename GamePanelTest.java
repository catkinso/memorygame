/*
 * GamePanelTest
 *
 * This class exercises the MemoryGamePanel. It cycles through the panels
 * different items. It is not a part of the memory game.
 */

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanelTest implements Runnable, GameController {

    private MemoryGamePanel mp;
    private Timer t;
    private int toggle = 0;
    private int testSequence = 0;
    private int testSequenceRun;

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Memory");
        frame.setSize(340, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        mp = new MemoryGamePanel( );
        frame.getContentPane( ).add(mp);

        mp.memoryCtrl = this;

    }


    public GamePanelTest( ) {
        System.out.println("GamePanelTest");
    }

 
    public void numButtonPressed(int button) { 
        mp.setLedGreen(button); 
    }


    public void numButtonReleased(int button){ }


    public void startButtonPressed( ) {
        if (testSequenceRun == 0) {
            start( );
        }
        else {
            stop( );
        }
    }


    public void startButtonReleased( ) {
        mp.clearGreenButtonBox( );
    }


    public void run( ) {
        createAndShowGUI( );
    }

    public class GuiStuff extends TimerTask {
        public void run( ) {
       
            if (testSequenceRun == 0) {
                mp.clearLabelText( );
                mp.clearGreenButtonBox( );
                mp.clearRedButtonBox( );
                mp.setAllLedsBlack( );

                testSequence = 0;

                t.cancel( );
            }

            switch (testSequence) {
                case 1:
                    mp.setLabelText("Green box on");
                    mp.showGreenButtonBox( );
                    break;
                case 2:     
                    mp.setLabelText("Green box off");
                    mp.clearGreenButtonBox( );
                    break;
                case 3:     
                    mp.setLabelText("Red box on");
                    mp.showRedButtonBox( );
                    break;
                case 4:     
                    mp.setLabelText("Red box off");
                    mp.clearRedButtonBox( );
                    break;
                case 5:
                    mp.setLabelText("Led 1 red");
                    mp.setLedRed(1);
                    break;
                case 6:
                    mp.setLabelText("Led 2 red");
                    mp.setLedRed(2);
                    break;
                case 7:     
                    mp.setLabelText("Led 3 red");
                    mp.setLedRed(3);
                    break;
                case 8:
                    mp.setLabelText("Led 4 yellow");
                    mp.setLedYellow(4);
                    break;
                case 9:     
                    mp.setLabelText("Led 5 yellow");
                    mp.setLedYellow(5);
                    break;
                case 10:     
                    mp.setLabelText("Led 6 yellow");
                    mp.setLedYellow(6);
                    break;
                case 11:
                    mp.setLabelText("Led 7 green");
                    mp.setLedGreen(7);
                    break;
                case 12:    
                    mp.setLabelText("Led 8 green");
                    mp.setLedGreen(8);
                    break;
                case 13:
                    mp.setLabelText("Led 9 green");
                    mp.setLedGreen(9);
                    break;
                case 14:
                    mp.setLabelText("Clear all Leds");
                    mp.setAllLedsBlack( );
                    break;
                case 15:
                    mp.setLabelText("Led Red X");
                    mp.setLedRedX( );
                    break;
                case 16:
                    mp.setLabelText("Clear all Leds");
                    mp.setAllLedsBlack( );
                    break;
                default:
                    break;
            }

            testSequence++;
            if (testSequence > 16)
                testSequence = 1;
        }
    }

    public void start( ) {
        //if (t == null) {
        //    t = new Timer( );
        //}

        testSequenceRun = 1;
        t = new Timer( );
        t.schedule(new GuiStuff( ), 500, 500);
    }

    public void stop( ) {
        testSequenceRun = 0;
    }

    public static void main(String[] args) {
        //System.out.println("thread name: " + Thread.currentThread().getName());
        GamePanelTest gpt = new GamePanelTest( );
        javax.swing.SwingUtilities.invokeLater(gpt);
        gpt.start( );
    }
}
