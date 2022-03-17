package client.scenes;

import com.google.gson.Gson;
import com.google.inject.Inject;
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

public class MostPowerCtrl {
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
    public MostPowerCtrl(MainCtrl mainCtrl) {
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
//                                URL url = new URL("http://localhost:8080/-1/getGameInfo");
                                URL url = new URL(link + this.mainCtrl.getCurrentID()+ "/getGameInfo" );
                                //for now all gameID's are set to 1,
                                //but these need to be changed once the gameID is stored from the sever
                                HttpURLConnection http = (HttpURLConnection) url.openConnection();
                                Gson g = new Gson();
                                String jsonString = httpToJSONString(http);
                                commons.TrimmedGame trimmedGame = g.fromJson(jsonString, commons.TrimmedGame.class);
                                currentRoundLabel.setText("currentRound" + trimmedGame.getRoundsLeft());
                                timerLabel.setText("Time: " + trimmedGame.getTimer());
                                questionLabel.setText(trimmedGame.getCurrentQuestion());
                                this.currentround = 20 - trimmedGame.getRoundsLeft();
                                System.out.println("ok");
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
        System.out.println(httpToJSONString(http));
        http.disconnect();

    }

    /**
     * @param answer is a string related to which answer the user has chosen.
     * @throws IOException
     */
    public void sendAnswer(String answer) throws IOException {
//        URL url = new URL("http://localhost:8080/1/P1/checkAnswer/" + currentRoundLabel.getText() + "/" + answer);
        //for now all gameID's are set to 1 but these need to be changed once the gameID is stored from the sever
        // also the round and the name

//        URL url = new URL("http://localhost:8080/1/P1/checkAnswer/" + currentround + "/" + answer);
        URL url = new URL(link + this.mainCtrl.getCurrentID() + "/"
                + this.mainCtrl.getName() + "/checkAnswer/" +
                currentround + "/" + answer);
        System.out.println(this.mainCtrl.getName());
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
        System.out.println(http.getResponseCode());
        System.out.println(httpToJSONString(http));
        http.disconnect();

    }


    /**
     * @throws IOException
     */
    public void choiceASend () throws IOException {

        if (this.checkCanAnswer()) {
            this.sendAnswer(choiceA.getText());
            lastRoundAnswered = this.currentround;
        }
    }

    /**
     * @throws IOException
     */
    public void choiceBSend() throws IOException {
        if (this.checkCanAnswer()) {
            this.sendAnswer(choiceB.getText());
            lastRoundAnswered = this.currentround;
        }
    }

    /**
     * @throws IOException
     */
    public void choiceCSend() throws IOException {
        if (this.checkCanAnswer()) {
            this.sendAnswer(choiceC.getText());
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


}





