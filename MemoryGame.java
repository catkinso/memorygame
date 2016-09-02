/*
 * MemoryGame
 *
 * This is the class that ties the panel, controller and game together.
 *
 * This class contains the main method.
 */

import javax.swing.*;

public class MemoryGame implements Runnable {

    private MemoryGamePanel mp;
    private MemoryGameController mc;
    private MemoryGameLogic gl;

    public void run( ) {
        JFrame frame = new JFrame("Memory");
        frame.setSize(340, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        mp = new MemoryGamePanel( );
        frame.getContentPane( ).add(mp);

        mc = new MemoryGameController( );
        mp.memoryCtrl = mc;
        mc.mp = mp;

        gl = new MemoryGameLogic( );
        mc.gl = gl;

        mc.startGame( );
    }

    public static void main(String[] args) {
        MemoryGame mg = new MemoryGame( );
        SwingUtilities.invokeLater(mg);
    }
}
