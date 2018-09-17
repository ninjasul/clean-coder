public class Game {
    private static final int TEN_SCORE = 10;
    private final int MAX_FRAME_CNT = 10;
    private int[] rolls = new int [21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int getScore() {
        int score = 0;
        int firstRollInFrame = 0;

        for(int frame = 0; frame < MAX_FRAME_CNT; frame++ ) {

            if( isStrike(firstRollInFrame) ) {
                score += TEN_SCORE + nextBallsForStrike(firstRollInFrame);
                firstRollInFrame++;
            }
            else if( isSpare(firstRollInFrame) ) {
                score += TEN_SCORE + nextBallForSpare(firstRollInFrame);
                firstRollInFrame += 2;
            }
            else {
                score += nextBallsForFrame(firstRollInFrame);
                firstRollInFrame += 2;
            }

            //System.out.printf("frame: %d, firstRollInFrame: %d, score: %d\n", frame, firstRollInFrame, score );
        }

        return score;
    }

    private int nextBallsForFrame(int firstRollInFrame) {
        return rolls[firstRollInFrame] + rolls[firstRollInFrame + 1];
    }

    private int nextBallsForStrike(int firstRollInFrame) {
        return rolls[firstRollInFrame + 1] + rolls[firstRollInFrame + 2];
    }

    private int nextBallForSpare(int firstRollInFrame) {
        return rolls[firstRollInFrame + 2];
    }

    private boolean isStrike(int firstRollInFrame) {
        return rolls[firstRollInFrame] == TEN_SCORE;
    }

    private boolean isSpare(int firstRollInFrame) {
        return rolls[firstRollInFrame] + rolls[firstRollInFrame + 1] == TEN_SCORE;
    }

}