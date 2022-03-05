package client.scenes;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonElement;
import commons.TrimmedGame;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.*;

import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLConnection;
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


    public void getGameInfo() throws IOException {

        boolean gameFinished = false;

        while (gameFinished!= true) {
            URL url = new URL("http://localhost:8080/1/getGameInfo");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
//            URLConnection http = (HttpURLConnection)url.openConnection();

            Gson g = new Gson();
//            Player p = g.fromJson(jsonString, Player.class)
            System.out.println("asdfasdfasdf");
            System.out.println(http.getContent().toString());

            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (http.getInputStream(), Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            String jsonString = textBuilder.toString();

            System.out.println(jsonString);


            commons.TrimmedGame trimmedGame = g.fromJson( jsonString, commons.TrimmedGame.class);

            currentRoundLabel.setText("currentRound" + trimmedGame.getRoundsLeft());
            timerLabel.setText("Time: " + trimmedGame.getTimer());
            questionLabel.setText(trimmedGame.getCurrentQuestion());

            if (trimmedGame.getQuestionType() == 1) {
                this.threeChoicesEnable();
            }

            else if (trimmedGame.getQuestionType() == 2) {
                this.guessEnable();
            }

            http.disconnect();

            if (trimmedGame.getRoundsLeft() == 0) {
                gameFinished = true;
            }
            gameFinished = true;
        }




    }
//    public void getRequest() throws IOException {
//        URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
//
//// Open a connection(?) on the URL(??) and cast the response(???)
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//// Now it's "open", we can set the request method, headers etc.
//        connection.setRequestProperty("accept", "application/json");
//
//// This line makes the request
//        InputStream responseStream = connection.getInputStream();
//
//// Manually converting the response body InputStream to APOD using Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        APOD apod = mapper.readValue(responseStream, APOD.class);
//
//// Finally we have the response
//        System.out.println(apod.title);
//    }
}





