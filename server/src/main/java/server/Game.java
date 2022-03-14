package server;

import commons.TrimmedGame;
import server.database.ActivityRepository;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable{
    private List<Player> players;
    private int lobbyId;
    private List<Question> questions;
    private int gameType;
    private Round round;

    /**
     * constructor for game
     * @param players
     * @param lobbyId
     * @param gameType
     * @param dtBase
     */
    public Game(List<Player> players, int lobbyId, int gameType, ActivityRepository dtBase) {
        this.players = players;
        this.lobbyId = lobbyId;
        this.gameType = gameType;
        round = new Round();

        questions = new ArrayList<>();
        for(int i = 0; i < round.getTotalRounds(); i++) {
            Question tempQuestion = new Question(dtBase);
            questions.add(tempQuestion);
        }

    }

    /**
     * method which handles simulating the game rounds
     */
    @Override
    public void run() {
        try {
            while(round.getGameStatus() == 1) {
                round.tickDown();
                Thread.sleep(1000);
                System.out.println(round);
            }
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * returns list of players
     * @return the list of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * returns lobby-id
     * @return the lobby-id
     */
    public int getLobbyId() {
        return lobbyId;
    }

    /**
     * returns list of all the questions
     * @return the list of all the questions
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * returns the type of the game(multi-player, single-player)
     * @return the type of the game
     */
    public int getGameType() {
        return gameType;
    }

    /**
     * returns the round object
     * @return the round object
     */
    public Round getRound() { return round; }

    /**
     * Trims game for generic purposes, not for a given player
     * @return the current object as TrimmedGame, with full timer
     */
    public TrimmedGame trim(){
        Question currQuestion = questions.get(0);
        return new TrimmedGame(lobbyId, currQuestion.getQuestion(), questions.size(), round.getTimer(),
                currQuestion.getAnswers(), currQuestion.getType());

    }
    /**
     * trims the current object
     * @param requester Name of the player requesting the trimmed game
     * @return the current object as TrimmedGame
     */
    public TrimmedGame trim(String requester) {
        Question currQuestion = questions.get(0);
        if (round.isHalfTimerUsed()){
            if (!requester.equals(round.getPlayerWhoUsedJoker().getName())) {
                return new TrimmedGame(lobbyId, currQuestion.getQuestion(), questions.size(), round.getHalvedTimer(),
                        currQuestion.getAnswers(), currQuestion.getType());
            }
        }
        return new TrimmedGame(lobbyId, currQuestion.getQuestion(), questions.size(), round.getTimer(),
                currQuestion.getAnswers(), currQuestion.getType());

    }


    /**
     * returns the object as a string
     * @return the object as a string
     */
    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", lobbyId=" + lobbyId +
                ", questions=" + questions +
                ", gameType=" + gameType +
                '}';
    }

}
