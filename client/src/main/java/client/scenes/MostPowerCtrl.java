package client.scenes;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;

import java.net.URL;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

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





//    public MostPowerCtrl(MainCtrl mainCtrl) {
//        this.mainCtrl = mainCtrl;
//        this.threeChoicesEnable();
//    }


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
     * This is the method that will continously poll the server in order to update game information
     * @throws IOException
     */
    public void getGameInfo() throws IOException {

        boolean gameFinished = false;

        while (gameFinished!= true) {
            URL url = new URL("http://localhost:8080/1/getGameInfo");
            //for now all gameID's are set to 1 but these need to be changed once the gameID is stored from the sever
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            Gson g = new Gson();
            String jsonString = httpToJSONString(http);
            commons.TrimmedGame trimmedGame = g.fromJson( jsonString, commons.TrimmedGame.class);
            currentRoundLabel.setText("currentRound" + trimmedGame.getRoundsLeft());
            currentround = trimmedGame.getRoundsLeft();
            timerLabel.setText("Time: " + trimmedGame.getTimer());
            questionLabel.setText(trimmedGame.getCurrentQuestion());

            if (trimmedGame.getQuestionType() == 1) {
                this.threeChoicesEnable();
            }

            else if (trimmedGame.getQuestionType() == 2) {
                this.guessEnable();
            }

            if (trimmedGame.getRoundsLeft() == 0) {
                gameFinished = true;
            }
            gameFinished = true; //this is hardcoded to make the code stop
            http.disconnect();
        }

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

        URL url = new URL("http://localhost:8080/1/P1/checkAnswer/" + currentround + "/" + joker);
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

        URL url = new URL("http://localhost:8080/1/P1/checkAnswer/" + currentround + "/" + answer);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("PUT");
        System.out.println(http.getResponseCode());
        System.out.println(httpToJSONString(http));
        http.disconnect();

    }


    public void choiceASend () throws IOException {
        this.sendAnswer(choiceA.getText());
    }

    public void choiceBSend() throws IOException {
        this.sendAnswer(choiceB.getText());
    }

    public void choiceCSend() throws IOException {
        this.sendAnswer(choiceC.getText());
    }






}





