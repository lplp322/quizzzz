package client.scenes;

import com.google.gson.Gson;
import com.google.inject.Inject;
import commons.TrimmedGame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.Reader;
import java.net.HttpURLConnection;


import java.net.URL;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.LinkedList;

public class GameCtrl {
//    private final MainCtrl mainCtrl;

    @FXML
    private Button choiceA;

    @FXML
    private Label questionLabel;

    @FXML
    private Button choiceB;

    @FXML
    private Button choiceC;

    private int currentround;

    @FXML
    private Button halfTimeJokerButton;

    @FXML
    private Button doublePointsJokerButton;

    @FXML
    private Button eliminateWrongButton;

    @FXML
    private Button quitGameButton;

    @FXML
    private ListView playerList;

    @FXML
    private TextField guessText;

    @FXML
    private Button submitButton;

    @FXML
    private Label currentRoundLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label answerLabel;

    private MainCtrl mainCtrl;

    private static String link = "http://localhost:8080/";
    private static int lastRoundAnswered = -1;





//    public MostPowerCtrl(MainCtrl mainCtrl) {
//        this.mainCtrl = mainCtrl;
//        this.threeChoicesEnable();
//    }

    /**
     * Injecting mostpowercontroller
     * @param mainCtrl
     */
    @Inject
    public GameCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }


    /**
     * This method changes the FXML so that only the appropriate buttons/text is visible
     * for the 2 types of questions where the user chooses one of 3 choices
     */
    public void threeChoicesEnable() {
        this.choiceA.setVisible(true);
        this.choiceB.setVisible(true);
        this.choiceC.setVisible(true);
        this.guessText.setVisible(false);
        this.submitButton.setVisible(false);

    }

    /**
     * This method changes the FXML so that the user only sees the appropriate information for
     * the guessing type of question
     */
    public void guessEnable() {
        this.choiceA.setVisible(false);
        this.choiceB.setVisible(false);
        this.choiceC.setVisible(false);
        this.guessText.setVisible(true);
        this.submitButton.setVisible(true);

    }

    /**
     * Getting game info in a new thread
     */
    public void getGameInfo() {
        Thread t1 = new Thread(()-> {
            while(true) {
                Platform.runLater(() -> {
                            try {
                                URL url = new URL(link + mainCtrl.getCurrentID()
                                        +"/" + mainCtrl.getName() + "/getGameInfo");
                                //for now all gameID's are set to 1,
                                //but these need to be changed once the gameID is stored from the sever
                                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                Gson g = new Gson();
                                String jsonString = httpToJSONString(http);
                                commons.TrimmedGame trimmedGame = g.fromJson(jsonString, commons.TrimmedGame.class);
                                currentround = trimmedGame.getRoundNum();
                                if (trimmedGame.getTimer() < 0) {//works for now, BUT NEEDS TO BE CHANGED IN TRIMMEDGAME
                                    showTimeout(trimmedGame);
                                } else {
                                    showRound(trimmedGame);
                                }
                                //System.out.println("ok");
                                http.disconnect();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }});
        t1.start();
    }

    /**
     * Showing the timeout
     * @param trimmedGame
     */
    public void showTimeout(TrimmedGame trimmedGame) {
        currentRoundLabel.setText("Round is over");
        timerLabel.setText("Timeout");
        questionLabel.setText(trimmedGame.getCurrentQuestion());
        answerLabel.setVisible(true);
        if(currentround>lastRoundAnswered) answerLabel.setText("You have not answered");
    }

    /**
     * Showing the round screen
     * @param trimmedGame
     */
    private void showRound(TrimmedGame trimmedGame) {
        answerLabel.setVisible(false);
        currentRoundLabel.setText("currentRound " + trimmedGame.getRoundNum());
        timerLabel.setText("Time: " + trimmedGame.getTimer());
        questionLabel.setText(trimmedGame.getCurrentQuestion());
        if (trimmedGame.getQuestionType() == 1 || trimmedGame.getQuestionType() == 2) {
            this.threeChoicesEnable();
            if(trimmedGame.getPossibleAnswers().size() == 3) {
                choiceA.setText(trimmedGame.getPossibleAnswers().get(0));
                choiceB.setText(trimmedGame.getPossibleAnswers().get(1));
                choiceC.setText(trimmedGame.getPossibleAnswers().get(2));
            }
        } else this.guessEnable();
    }

    /**
     * @param http this is a http connection that the response of which will be turned into a string
     * @return The http response in JSON format
     */
    public static String httpToJSONString(HttpURLConnection http) {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (http.getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = textBuilder.toString();
        return jsonString;
    }


    /**
     * @param joker this is a string related to which joker is being passed to the server
     * @throws IOException
     */
    public  void jokerMessage(String joker) throws IOException {

//        URL url = new URL("http://localhost:8080/1/P1/checkAnswer/" + currentround + "/" + joker);
        URL url = new URL(link + this.mainCtrl.getCurrentID()
                + "/" + this.mainCtrl.getName() + "/joker/" + currentround + "/" + joker);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
//        http.setRequestMethod("PUT");
        System.out.println(http.getResponseCode());
        String response = httpToJSONString(http);
        System.out.println(response);
        http.disconnect();

    }

    /**
     * @param answer is a string related to which answer the user has chosen.
     * @throws IOException
     */
    public void sendAnswer(String answer) throws IOException {
        URL url = new URL(link + this.mainCtrl.getCurrentID() + "/"
                + this.mainCtrl.getName() + "/checkAnswer/" +
                currentround + "/" + answer);

            System.out.println(this.mainCtrl.getName());
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
            System.out.println(http.getResponseCode());
        String response = httpToJSONString(http);
            System.out.println(response);
        printAnswerCorrectness(response);
        http.disconnect();

    }


    /**
     * @throws IOException
     */
    public void choiceASend () throws IOException {

        if (this.checkCanAnswer()) {
            this.sendAnswer("0");
            lastRoundAnswered = this.currentround;
        }
    }

    /**
     * @throws IOException
     */
    public void choiceBSend() throws IOException {
        if (this.checkCanAnswer()) {
            this.sendAnswer("1");
            lastRoundAnswered = this.currentround;
        }
    }

    /**
     * @throws IOException
     */
    public void choiceCSend() throws IOException {
        if (this.checkCanAnswer()) {
            this.sendAnswer("2");
            lastRoundAnswered = this.currentround;

        }
    }


    /**
     * @return the list of entries in the leaderboard from the server
     * @throws IOException if the link is not valid
     */
    public LinkedList<commons.LeaderboardEntry> getLeaderboard() throws IOException {
        URL url = new URL(link + "leaderboard" );
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        Gson g = new Gson();
        String jsonString = httpToJSONString(http);
        LinkedList<commons.LeaderboardEntry> leaderboardList = g.fromJson(jsonString, LinkedList.class);
        http.disconnect();
        return leaderboardList;
    }


    /**
     * @return returns true if the user can still answer this question
     */
    public boolean checkCanAnswer() {
        if (this.currentround > lastRoundAnswered) {
            return true;
        }
        return false;
    }

    /**
     * Changing the label with answer, when response to the answer received
     * @param response - response from server in String format
     */
    public void printAnswerCorrectness(String response) {
        answerLabel.setText("Your answer is " + response);

    }

}





