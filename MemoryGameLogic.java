/*
 * MemoryGameLogic
 *
 * This is the logic for the game. It creates the randon sequences and
 * checks the users sequences. It also keeps track of if the game has
 * been won.
 */

import java.util.Random;

public class MemoryGameLogic {

    private int sequence[ ];
    private int ipSequence[ ];
    private int currentSequenceIndex;
    private int currentIpSequence;


    public MemoryGameLogic( ) {
        sequence = new int[0];
        ipSequence = new int[0];
        currentSequenceIndex = 0;
        currentIpSequence = 0;
    }


    public int getGameLevel( ) {
        return sequence.length;
    }


    public void newGame(int level) {
        int i;
        Random rand = new Random( );

        if (sequence.length != level)
            sequence = new int[level];

        if (ipSequence.length != level)
            ipSequence = new int[level];

        currentSequenceIndex = 0;
        currentIpSequence = 0;

        for (i = 0; i < ipSequence.length; i++)
            ipSequence[i] = -1;

        for (i = 0; i < sequence.length; i++) {
            sequence[i] = rand.nextInt(9) + 1;

            if (i > 0) {
                while (sequence[i] == sequence[i - 1])
                    sequence[i] = rand.nextInt(9) + 1;
            }
        }
    }


    public int getCurrentGuess( ) {
        return currentIpSequence;
    }


    public int getNextSequenceNumber( ) {
        int num;

        if (currentSequenceIndex >= sequence.length)
            return -1;

        num = sequence[currentSequenceIndex];
        currentSequenceIndex++;
        
        return num;
    }


    public boolean checkNextInputNumber(int ip) {
        boolean ipCorrect = false;
    
        if (currentIpSequence >= sequence.length)
            return false;
    
        ipSequence[currentIpSequence] = ip;

        if (ipSequence[currentIpSequence] == sequence[currentIpSequence])
            ipCorrect = true;

        currentIpSequence++;

        return ipCorrect;
    }


    public boolean gameWon( ) {
        boolean won = true;

        for (int i = 0; i < sequence.length; i++) {
            if (sequence[i] != ipSequence[i]) {
                won = false;
                break;
            }
        }

        return won;
    }
}
