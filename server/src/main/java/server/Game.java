package server;

import commons.TrimmedGame;
import server.database.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Game implements Runnable{
    private Map<String, Player> players;
    private int lobbyId;
    private List<Question> questions;
    private int gameType;
    private Round round;

    /**
     * @param players map of players that are in the game
     * @param lobbyId the ID of the lobby that they were in before
     * @param gameType the type of game that is being played (single or multiplayer)
     * @param dtBase the database for the activities
     */
    public Game(Map<String, Player> players, int lobbyId, int gameType, ActivityRepository dtBase) {
        this.players = players;
        this.lobbyId = lobbyId;
        this.gameType = gameType;
        round = new Round();

        questions = new ArrayList<>();
        for(int i = 0; i < round.getTotalRounds(); i++) {
            Question tempQuestion = new Question(dtBase);
            questions.add(tempQuestion);
            System.out.println(tempQuestion.getQuestion());
        }
//
//        for (int i =0; i < players.size(); i ++) {
//            this.playerScore.put(players.get(i).getName(), 0);
//        }

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
                //System.out.println(round);
            }
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * @return returns the map of names to the players
     */
    public Map<String, Player> getPlayers() {
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
        Question currQuestion = questions.get(round.getRound());
        return new TrimmedGame(lobbyId, currQuestion.getQuestion(), questions.size(), round.getTimer(),
                currQuestion.getAnswers(), currQuestion.getType());

    }
    /**
     * trims the current object
     * @param requester Name of the player requesting the trimmed game
     * @return the current object as TrimmedGame
     */
    public TrimmedGame trim(String requester) {
        if (round.getGameStatus() == 2) {
            return new TrimmedGame(lobbyId, null, -1, 0, new ArrayList<String>(), 0);
        }
        Question currQuestion = questions.get(round.getRound());
        if (round.isHalfTimerUsed()){
            if (!requester.equals(round.getPlayerWhoUsedJoker().getName())) {
                return new TrimmedGame(lobbyId, currQuestion.getQuestion(), round.getRound(), round.getHalvedTimer(),
                        currQuestion.getAnswers(), currQuestion.getType());
            }
        }
        return new TrimmedGame(lobbyId, currQuestion.getQuestion(), round.getRound(), round.getTimer(),
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


    /**
     * Will check for the correctness of the player answer and give him points
     * @param name - name of the player
     * @param round - round number
     * @param answer - String with provided answer
     * @return True if answer was correct
     */
    public boolean checkPlayerAnswer(String name, int round, int answer) {
        System.out.println("Round: "+ getRound().getRound());
        System.out.println("Correct answer: "+ getQuestions().get(round).getAnswer());
        if(getRound().getRound() == round){
            Question currQuestion = questions.get(getRound().getRound());
            System.out.println("Question type " + currQuestion.getType());
            if(currQuestion.getType() == 1 || currQuestion.getType() == 0){
                //TO BE IMPLEMENTED
                return true;
            }
            else{
                int correctAns = -1;
                for(int i=0; i<currQuestion.getAnswers().size(); i++){
                    if(currQuestion.getAnswers().get(i).equals(currQuestion.getAnswer())) correctAns = i;
                }
                System.out.println("Correct answer: "+ correctAns);
                if(correctAns == -1) System.out.println("errrroororroror");
                if (correctAns == answer){
                    updatePlayerScore(name, 100);
                    return true;
                }
                return false;
            }
        }
        else{
            System.out.println("False round");
            return false;
        }
    }

    /**
     * @param name name of the player that the score is being updated for
     * @return returns the updated score of the player
     */
    public int updatePlayerScore(String name, int points) {
        Player player = this.players.get(name);
        int score = player.getScore();
        score = score + points;
        player.setScore(score);
        return score;
    }
}
