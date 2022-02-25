package client.scenes;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

import java.net.HttpURLConnection;

import java.net.URL;


public class MostPowerCtrl {

    @FXML
    private Button choiceA;

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



    public void choiceASend() throws IOException {
        URL url = new URL("localhost:8080/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

    }





}
