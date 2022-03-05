package server;

import server.database.ActivityRepository;

import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable{
    private List<Player> players;
    private int lobbyId;
    private List<Question> questions;
    private int gameType;
    private Round round;

    //The number of rounds in a game
    private final int gameRounds = 20;

    public Game(List<Player> players, int lobbyId, int gameType, ActivityRepository dtBase) {
        this.players = players;
        this.lobbyId = lobbyId;
        this.gameType = gameType;
        round = new Round();

        questions = new ArrayList<>();
        for(int i = 0; i < gameRounds; i++) {
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

    public int getGameRounds() {
        return gameRounds;
    }

    public TrimmedGame trim() {
        if(round.isHalfTimerUsed()) {
            return new TrimmedGame(lobbyId, questions.get(0), questions.size(),
                    round.getTimer(), round.getPlayerWhoUsedJoker().getName(), round.getHalvedTimer());
        }

        return new TrimmedGame(lobbyId, questions.get(0), questions.size(),
                round.getTimer());
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
}
