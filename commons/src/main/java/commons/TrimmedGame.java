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
    private int questionType;

    /**
     * Creates a new TrimmedGame for singleplayer
     * @param id the game ID
     * @param currentQuestion the current question
     * @param roundsLeft the rounds left
     * @param timer the time left
     * @param questionType the question type
     */
    public TrimmedGame(int id, String currentQuestion, int roundsLeft, int timer, int questionType) {
        this.id = id;
        this.currentQuestion = currentQuestion;
        this.roundsLeft = roundsLeft;
        this.timer = timer;
        this.timeJoker = false;
        this.jokerUser = null;
        this.timerJoker = -1;
        this.questionType = questionType;
    }

    /**
     * Creates a new TrimmedGame for multiplayer
     * @param id the game Id
     * @param currentQuestion the current question
     * @param roundsLeft the rounds left
     * @param timer the timer left
     * @param jokerUser the joker user
     * @param timerJoker the time joker
     * @param questionType the question type
     */
    public TrimmedGame(int id, String currentQuestion, int roundsLeft, int timer, String jokerUser, int timerJoker,
                       int questionType) {
        this.id = id;
        this.currentQuestion = currentQuestion;
        this.roundsLeft = roundsLeft;
        this.timer = timer;
        this.timeJoker = true;
        this.jokerUser = jokerUser;
        this.timerJoker = timerJoker;
        this.questionType = questionType;
    }

    /**
     * Getter for the game id
     * @return the game id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the current question
     * @return the current question
     */
    public String getCurrentQuestion() {
        return currentQuestion;
    }

    /**
     * Getter for the rounds left
     * @return the rounds left
     */
    public int getRoundsLeft() {
        return roundsLeft;
    }

    /**
     * Getter for the time left
     * @return the time left
     */
    public int getTimer() {
        return timer;
    }

    /**
     * Getter for the time joker
     * @return true iff it is the time joker
     */
    public boolean isTimeJoker() {
        return timeJoker;
    }

    /**
     * Getter for the joker user
     * @return the joker user
     */
    public String getJokerUser() {
        return jokerUser;
    }

    /**
     * Getter for the timer joker
     * @return the timer joker
     */
    public int getTimerJoker() {
        return timerJoker;
    }

    /**
     * Getter for the question type
     * @return the question type
     */
    public int getQuestionType() {
        return questionType;
    }


    /**
     * Equals method for TrimmedGame
     * @param o the object to compare with
     * @return true iff the other object is equal to this
     */
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

