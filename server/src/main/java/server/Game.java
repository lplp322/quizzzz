package server;

import server.database.ActivityRepository;
import commons.*;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private int lobbyId;
    private List<Question> questions;
    private int gameType;

    //The number of rounds in a game
    private final int gameRounds = 20;

    public Game(List<Player> players, int lobbyId, int gameType, ActivityRepository dtBase) {
        this.players = players;
        this.lobbyId = lobbyId;
        this.gameType = gameType;

        questions = new ArrayList<>();
        for(int i = 0; i < gameRounds; i++) {
            Question tempQuestion = new Question(dtBase);
            questions.add(tempQuestion);
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

    public int getGameRounds() {
        return gameRounds;
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", lobbyId=" + lobbyId +
                ", questions=" + questions +
                ", gameType=" + gameType +
                ", gameRounds=" + gameRounds +
                '}';
    }

    public TrimmedGame trim() {
        return new TrimmedGame(lobbyId, "Question 1", 19, 20);
    }

}
