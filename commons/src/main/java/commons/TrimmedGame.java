package commons;

import java.util.List;
import java.util.Objects;

public class TrimmedGame {
    private int id;
    private String currentQuestion;
    private int roundsLeft;
    private int timer;
    private List<String> possibleAnswers;

    public TrimmedGame(int id, String currentQuestion, int roundsLeft, int timer, List<String> answers) {
        this.id = id;
        this.currentQuestion = currentQuestion;
        this.roundsLeft = roundsLeft;
        this.timer = timer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrimmedGame that = (TrimmedGame) o;
        return id == that.id && roundsLeft == that.roundsLeft && timer == that.timer &&
                Objects.equals(currentQuestion, that.currentQuestion) &&
                Objects.equals(possibleAnswers, that.possibleAnswers);
    }
}
