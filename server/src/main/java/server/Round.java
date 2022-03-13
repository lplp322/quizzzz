package server;

public class Round {
    // the number of the current round
    private int round;
    // how many seconds are left
    private int timer;
    // there is a joker that halves the timer for everyone but him, so we must keep the player who has used it and whether someone has
    private Player playerWhoUsedJoker;
    private boolean halfTimerUsed;
    private int halvedTimer;
    // 1 - game running, 2 - game has ended
    private int gameStatus;

    private final int totalRounds = 20;
    private final int roundTimer = 20;

    /**
     * By default, first round is 0 and timer counts down from 20
     */
    public Round() {
        this.round = 0;
        this.timer = roundTimer;
        this.halfTimerUsed = false;
        this.gameStatus = 1;
    }

    /**
     * Method that simulates the game rounds
     * when the last round has passed, change the game status to 2
     */
    public void tickDown() {
        timer--;
        if(timer == 0) {
            round++;
            timer = 20;
        }
        if(round == totalRounds) {
            gameStatus = 2;
        }
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setHalvedTimer(int halvedTimer) {
        this.halvedTimer = halvedTimer;
    }

    public void setHalfTimerUsed(boolean halfTimerUsed) {
        this.halfTimerUsed = halfTimerUsed;
    }

    public void setPlayerWhoUsedJoker(Player playerWhoUsedJoker) {
        this.playerWhoUsedJoker = playerWhoUsedJoker;
    }

    public int getRound() {
        return round;
    }

    public int getTimer() {
        return timer;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public int getHalvedTimer() {
        return halvedTimer;
    }

    public boolean isHalfTimerUsed() {
        return halfTimerUsed;
    }

    public Player getPlayerWhoUsedJoker() {
        return playerWhoUsedJoker;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public int getRoundTimer() {
        return roundTimer;
    }

    @Override
    public String toString() {
        return "Round{" +
                "round=" + round +
                ", timer=" + timer +
                ", halvedTimer=" + halvedTimer +
                ", halfTimerUsed=" + halfTimerUsed +
                ", playerWhoUsedJoker=" + playerWhoUsedJoker +
                '}';
    }
}