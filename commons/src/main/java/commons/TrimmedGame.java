package commons;

import java.util.Objects;

public class TrimmedGame {
    private int id;
    private String currentQuestion;
    private int roundsLeft;
    private int timer;
    private boolean timeJoker;
    private String jokerUser;
    private int timerJoker;

    public TrimmedGame(int id, String currentQuestion, int roundsLeft, int timer) {
        this.id = id;
        this.currentQuestion = currentQuestion;
        this.roundsLeft = roundsLeft;
        this.timer = timer;
        this.timeJoker = false;
        this.jokerUser = null;
        this.timerJoker = -1;
    }

    public TrimmedGame(int id, String currentQuestion, int roundsLeft, int timer, String jokerUser, int timerJoker) {
        this.id = id;
        this.currentQuestion = currentQuestion;
        this.roundsLeft = roundsLeft;
        this.timer = timer;
        this.timeJoker = true;
        this.jokerUser = jokerUser;
        this.timerJoker = timerJoker;
    }

    public int getId() {
        return id;
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public int getRoundsLeft() {
        return roundsLeft;
    }

    public int getTimer() {
        return timer;
    }

    public boolean isTimeJoker() {
        return timeJoker;
    }

    public String getJokerUser() {
        return jokerUser;
    }

    public int getTimerJoker() {
        return timerJoker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrimmedGame that = (TrimmedGame) o;
        return id == that.id && roundsLeft == that.roundsLeft && timer == that.timer && timeJoker == that.timeJoker &&
                timerJoker == that.timerJoker && Objects.equals(currentQuestion, that.currentQuestion) &&
                Objects.equals(jokerUser, that.jokerUser);
    }
}

