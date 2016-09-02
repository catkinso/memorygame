/*
 * MemoryGamePanel
 * 
 * This is the class for the gui. It passes the button clicks along to the
 * game controller. It also displays elements as per the game controller.
 *
 * The methods provided to the game controller do not have to be on the EDT.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MemoryGamePanel extends JPanel
                                          implements MouseListener, GamePanel {

    private int buttonBorder = 0; // 0 - off, 1 - red border, 2 - green border

    private Color[ ] ledColors = new Color[ ]{Color.BLACK, Color.BLACK,
                                              Color.BLACK, Color.BLACK,
                                              Color.BLACK, Color.BLACK,
                                              Color.BLACK, Color.BLACK,
                                              Color.BLACK};
    private JLabel l1;

    private JButton j1;
    private JButton j2;
    private JButton j3;
    private JButton j4;
    private JButton j5;
    private JButton j6;
    private JButton j7;
    private JButton j8;
    private JButton j9;

    private JButton jStart;

    public GameController memoryCtrl;


    public MemoryGamePanel( ) {
        setLayout(null);
        setLocation(5, 5);
        setSize(330, 490);

        l1 = new JLabel("", SwingConstants.CENTER);
        l1.setLocation(50, 20);
        l1.setSize(230, 40);
        l1.setBackground(Color.BLUE);
        add(l1);

        j1 = new JButton("1");
        j1.setLocation(50, 105);
        j1.setSize(60, 60);
        j1.addMouseListener(this);
        add(j1);

        j2 = new JButton("2");
        j2.setLocation(135, 105);
        j2.setSize(60, 60);
        j2.addMouseListener(this);
        add(j2);

        j3 = new JButton("3");
        j3.setLocation(220, 105);
        j3.setSize(60, 60);
        j3.addMouseListener(this);
        add(j3);

        j4 = new JButton("4");
        j4.setLocation(50, 215);
        j4.setSize(60, 60);
        j4.addMouseListener(this);
        add(j4);

        j5 = new JButton("5");
        j5.setLocation(135, 215);
        j5.setSize(60, 60);
        j5.addMouseListener(this);
        add(j5);

        j6 = new JButton("6");
        j6.setLocation(220, 215);
        j6.setSize(60, 60);
        j6.addMouseListener(this);
        add(j6);

        j7 = new JButton("7");
        j7.setLocation(50, 325);
        j7.setSize(60, 60);
        j7.addMouseListener(this);
        add(j7);

        j8 = new JButton("8");
        j8.setLocation(135, 325);
        j8.setSize(60, 60);
        j8.addMouseListener(this);
        add(j8);

        j9 = new JButton("9");
        j9.setLocation(220, 325);
        j9.setSize(60, 60);
        j9.addMouseListener(this);
        add(j9);

        jStart = new JButton("Start");
        jStart.setLocation(100, 400);
        jStart.setSize(130, 40);
        jStart.addMouseListener(this);
        add(jStart);
    }


    public void setLabelText(final String str) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                l1.setText(str);
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void clearLabelText( ) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                l1.setText("");
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }

    
    public void setLedYellow(final int led) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                ledColors[led - 1] = Color.YELLOW;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void setLedGreen(final int led) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                ledColors[led - 1] = Color.GREEN;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void setLedRed(final int led) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                ledColors[led - 1] = Color.RED;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void setLedBlack(final int led) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                ledColors[led - 1] = Color.BLACK;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void setLedRedX( ) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                ledColors[0] = Color.RED;
                ledColors[1] = Color.BLACK;
                ledColors[2] = Color.RED;
                ledColors[3] = Color.BLACK;
                ledColors[4] = Color.RED;
                ledColors[5] = Color.BLACK;
                ledColors[6] = Color.RED;
                ledColors[7] = Color.BLACK;
                ledColors[8] = Color.RED;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void setAllLedsBlack( ) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                for (int i = 0; i < 9; i++)
                    ledColors[i] = Color.BLACK;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void showRedButtonBox( ) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                buttonBorder = 1;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void clearRedButtonBox( ) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                buttonBorder = 0;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void showGreenButtonBox( ) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                buttonBorder = 2;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    public void clearGreenButtonBox( ) {
        Runnable r = new Runnable( ) {
            public void run( ) {
                buttonBorder = 0;

                repaint( );
            }
        };

        if (SwingUtilities.isEventDispatchThread( ))
            r.run( );
        else
            SwingUtilities.invokeLater(r);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // button border
        if (buttonBorder > 0) {
            Graphics2D g2 = (Graphics2D)g;
            if (buttonBorder == 1)
                g2.setColor(Color.RED);
            else if (buttonBorder == 2)
                g2.setColor(Color.GREEN);
            float thickness = 4;
            Stroke oldStroke = g2.getStroke( );
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRect(42,70,250,325);
            g2.setStroke(oldStroke);
        }

        // j1
        g.setColor(ledColors[0]);
        g.fillOval(71, 80, 18, 18);


        // j2
        g.setColor(ledColors[1]);
        g.fillOval(156, 80, 18, 18);


        // j3
        g.setColor(ledColors[2]);
        g.fillOval(241, 80, 18, 18);


        // j4
        g.setColor(ledColors[3]);
        g.fillOval(71, 190, 18, 18);


        // j5
        g.setColor(ledColors[4]);
        g.fillOval(156, 190, 18, 18);


        // j6
        g.setColor(ledColors[5]);
        g.fillOval(241, 190, 18, 18);
        

        // j7
        g.setColor(ledColors[6]);
        g.fillOval(71, 300, 18, 18);


        // j8
        g.setColor(ledColors[7]);
        g.fillOval(156, 300, 18, 18);


        // j9
        g.setColor(ledColors[8]);
        g.fillOval(241, 300, 18, 18);
            
    }


    private int getButtonIndex(AWTEvent e) {
        int button = 0;

        if (e.getSource( ) == j1)
            button = 1;
        else if (e.getSource( ) == j2)
            button = 2;
        else if (e.getSource( ) == j3)
            button = 3;    
        else if (e.getSource( ) == j4)
            button = 4;    
        else if (e.getSource( ) == j5)
            button = 5;    
        else if (e.getSource( ) == j6)
            button = 6;    
        else if (e.getSource( ) == j7)
            button = 7;    
        else if (e.getSource( ) == j8)
            button = 8;    
        else if (e.getSource( ) == j9)
            button = 9;

        return button;
    }


    public void mousePressed(MouseEvent e)
    {
        int button = getButtonIndex(e);

        if (button > 0)
            memoryCtrl.numButtonPressed(button);  

        if (e.getSource( ) == jStart)
            memoryCtrl.startButtonPressed( );
    } 

   
    public void mouseReleased(MouseEvent e)
    {
        int button = getButtonIndex(e);
        
        if (button > 0)
            memoryCtrl.numButtonReleased(button);

        if (e.getSource( ) == jStart)
            memoryCtrl.startButtonReleased( );
    }


    public void mouseClicked(MouseEvent e) { }    
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
}
