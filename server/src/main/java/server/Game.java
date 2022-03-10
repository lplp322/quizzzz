package server;

import server.database.ActivityRepository;
import commons.*;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable{
    private List<Player> players;
    private int lobbyId;
    private List<Question> questions;
    private int gameType;
    private Round round;

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

    public List<Player> getPlayers() {
        return players;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getGameType() {
        return gameType;
    }

    public Round getRound() { return round; }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", lobbyId=" + lobbyId +
                ", questions=" + questions +
                ", gameType=" + gameType +
                '}';
    }

    /**
     * Will return trim object of the game class, that will be sent to the client
     * @return TrimmedGame object of this game
     */
    public TrimmedGame trim() {
        return new TrimmedGame(lobbyId, "Question 1", 19, 20,1);
    }

    /**
     * Will check for the correctness of the player answer and give him points
     * @param name - name of the player
     * @param round - round number
     * @param answer - String with provided answer
     * @return True if answer was correct
     */
    public boolean checkPlayerAnswer(String name, int round, String answer) {
        System.out.println(getRound().getRound());
        System.out.println(getQuestions().get(round).getAnswer());
        if(getRound().getRound() == round){
            if(getQuestions().get(round).getAnswer().equals(answer)){
                //!!!!!!!!Need to be implemented - player score increase(create map of <name, player> not list of players)
                return true;
            }

            return false;
        }
        else{
            System.out.println("False round");
            return false;
        }
    }
}
