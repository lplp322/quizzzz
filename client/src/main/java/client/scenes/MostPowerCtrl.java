package client.scenes;

import jakarta.ws.rs.core.MediaType;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

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
