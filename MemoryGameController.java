/*
 * MemoryGameController
 *
 * This class is the controller for the game. It responds to button presses
 * when appropriate and sets timers to define the flow of the game. It is 
 * the bridge between the game logic and the gui. 
 */

import java.util.Timer;
import java.util.TimerTask;

public class MemoryGameController implements GameController {

    public MemoryGamePanel mp;
    public MemoryGameLogic gl;

    private enum GameStates {
        STARTUP,
        STARTSEQ,
        SHOWSEQ,
        WAITFORUSERSEQ,
        WRONGUSERINPUT,
        USERWINS
    }
    GameStates gameState;

    private Timer startSequenceTimer;
    private int startSequenceState;

    private Timer memorySequenceTimer;

    private Timer winningSequenceTimer;
    private int winningSequenceState;

    private Timer losingSequenceTimer;
    private int losingSequenceState;

    private int buttonPressed[ ] = {0, 0, 0, 0, 0, 0, 0, 0, 0};


    public void numButtonPressed(int button)
    {
        switch (gameState) {
            case WAITFORUSERSEQ:
                mp.setLedRed(button);
                buttonPressed[button - 1] = 1;
                
                if (gl.checkNextInputNumber(button) == true) {
                    int level = gl.getGameLevel( );
                    int guess = gl.getCurrentGuess( ) ;
                    mp.setLabelText("Correct input... " + guess + "/" + level);

                    if (gl.gameWon( ) == true) {
                        gameState = GameStates.USERWINS;
                        showWinningSequence( );
                    }
                } 
                else {
                    gameState = GameStates.WRONGUSERINPUT;
                    showLosingSequence( );
                }
                break;
            default:
                break;
        }
    }


    public void numButtonReleased(int button) {
        if (buttonPressed[button - 1] == 1) {
            mp.setLedBlack(button);
            buttonPressed[button - 1] = 0;
        }
    }


    public void startButtonPressed( ) {
        ;
    }


    public void startButtonReleased( ) {
        switch (gameState) {
            case STARTUP:
                gameState = GameStates.STARTSEQ;
                showStartSequence( );
                break;
            case STARTSEQ:
                stopStartSequence( );
                gameState = GameStates.SHOWSEQ;
                showMemorySequence( );
                break;
            case USERWINS:
                gameState = GameStates.STARTSEQ;
                stopWinningSequence( );
                showStartSequence( );
                break;
            case WRONGUSERINPUT:
                gameState = GameStates.STARTSEQ;
                stopLosingSequence( );
                showStartSequence( );
                break;
            default:
                break;
        }
    }


    public void startGame( ) {
        gameState = GameStates.STARTUP;
        mp.setLabelText("Press Start button to play...");
    }


    private class StartSequenceTask extends TimerTask {
        public void run( ) {
            switch (startSequenceState) {
                case 0:
                    mp.setLabelText("Get ready to memorize LED's...");
                    break;
                case 1:
                    mp.setLabelText("Get ready to memorize LED's...3");
                    mp.setLedYellow(1);
                    mp.setLedYellow(2);
                    mp.setLedYellow(3);
                    break;
                case 2:
                    mp.setLabelText("Get ready to memorize LED's...2");
                    mp.setLedYellow(4);
                    mp.setLedYellow(5);
                    mp.setLedYellow(6);
                    break;
                case 3:
                    mp.setLabelText("Get ready to memorize LED's...1");
                    mp.setLedGreen(7);
                    mp.setLedGreen(8);
                    mp.setLedGreen(9);
                    break;
                case 4:
                    mp.clearLabelText( );
                    mp.setAllLedsBlack( );

                    gameState = GameStates.SHOWSEQ;
                    showMemorySequence( );

                    startSequenceTimer.cancel( );
                    break;
                default:
                    break;
            }
            startSequenceState++;
        }
    }


    private void showStartSequence( ) {
        startSequenceState = 0;
        startSequenceTimer = new Timer( );
        startSequenceTimer.schedule(new StartSequenceTask( ), 0, 500);
    }


    private void stopStartSequence( ) {
        mp.clearLabelText( );
        mp.setAllLedsBlack( );
        startSequenceTimer.cancel( );
    }


    private class MemorySequenceTask extends TimerTask {
        public void run( ) {
            int currentLed = gl.getNextSequenceNumber( );

            mp.setAllLedsBlack( );

            if (currentLed >= 1) {
                mp.setLedRed(currentLed);
            }
            else {
                mp.clearLabelText( );

                gameState = GameStates.WAITFORUSERSEQ;
                mp.setLabelText("Input sequence now:");
                memorySequenceTimer.cancel( );
            }
        }
    }


    private void showMemorySequence( ) {
        gl.newGame(5);
        mp.setLabelText("Memorize sequence!!");
        memorySequenceTimer = new Timer( );
        memorySequenceTimer.schedule(new MemorySequenceTask( ), 1000, 1000);    
    }


    private class winningSequenceTask extends TimerTask {
        public void run( ) {
            if (winningSequenceState == 0) {
                mp.showGreenButtonBox( );
                mp.setLedGreen(1);
                mp.setLedGreen(9);
                mp.setLabelText("Game WON !!!!!");

                winningSequenceState = 1;
            }
            else if (winningSequenceState == 1) {
                mp.clearGreenButtonBox( );
                mp.clearLabelText( );
                mp.setAllLedsBlack( );

                winningSequenceState = 2;
            }
            else if (winningSequenceState == 2) {
                mp.showGreenButtonBox( );
                mp.setLedGreen(2);
                mp.setLedGreen(8);
                mp.setLabelText("Press Start to play again.");

                winningSequenceState = 3;
            }
            else if (winningSequenceState == 3) {
                mp.clearGreenButtonBox( );
                mp.clearLabelText( );
                mp.setAllLedsBlack( );

                winningSequenceState = 4;
            }
            else if (winningSequenceState == 4) {
                mp.showGreenButtonBox( );
                mp.setLedGreen(3);
                mp.setLedGreen(7);
                mp.setLabelText("Game WON !!!!!");

                winningSequenceState = 5;
            }
            else if (winningSequenceState == 5) {
                mp.clearGreenButtonBox( );
                mp.clearLabelText( );
                mp.setAllLedsBlack( );

                winningSequenceState = 6;
            }
            else if (winningSequenceState == 6) {
                mp.showGreenButtonBox( );
                mp.setLedGreen(4);
                mp.setLedGreen(6);
                mp.setLabelText("Press Start to play again.");

                winningSequenceState = 7;
            }
            else if (winningSequenceState == 7) {
                mp.clearGreenButtonBox( );
                mp.clearLabelText( );
                mp.setAllLedsBlack( );

                winningSequenceState = 0;
            }
        }
    }


    private void showWinningSequence( ) {
        winningSequenceState = 0;
        winningSequenceTimer = new Timer( );
        winningSequenceTimer.schedule(new winningSequenceTask( ), 1000, 750);    
    }

    
    private void stopWinningSequence( ) {
        winningSequenceTimer.cancel( );
        mp.clearGreenButtonBox( );
        mp.clearLabelText( );
    }


    private class losingSequenceTask extends TimerTask {
        public void run( ) {
            if (losingSequenceState == 0) {
                mp.showRedButtonBox( );
                mp.setAllLedsBlack( );
                mp.setLabelText("Wrong input!!!");

                losingSequenceState = 1;
            }
            else if (losingSequenceState == 1) {
                mp.clearRedButtonBox( );
                mp.setLedRedX( );
                mp.setLabelText("Press Start to play again.");

                losingSequenceState = 0;
            }
        }
    }


    private void showLosingSequence( ) {
        losingSequenceState = 0;
        losingSequenceTimer = new Timer( );
        losingSequenceTimer.schedule(new losingSequenceTask( ), 0, 750);    
    }

    
    private void stopLosingSequence( ) {
        losingSequenceTimer.cancel( );
        mp.clearRedButtonBox( );
        mp.clearLabelText( );
        mp.setAllLedsBlack( );
    }
}
