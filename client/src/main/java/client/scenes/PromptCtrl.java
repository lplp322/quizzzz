package client.scenes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PromptCtrl {
    private final MainCtrl mainCtrl;
    private boolean isSingleplayer;
    @FXML
    private AnchorPane mainWindow;
    @FXML
    private Button menuButton;
    @FXML
    private Button startButton;
    @FXML
    private TextField nameField;
    @FXML
    private Label errorLabel;
    @Inject
    public PromptCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }
    /**
     * Changes the size of the AnchorPlane
     * @param w - preferred width
     * @param h - preferred height
     */
    @FXML
    public void setWindowSize(double w, double h){
        mainWindow.setPrefSize(w,h);
    }

    @FXML
    public void onClickStart() throws MalformedURLException {
        if(nameField.getText().matches("[a-zA-Z0-9]+")){
            errorLabel.setVisible(false);
            if(isSingleplayer){
                URL singleplayerGame = new URL("http://localhost:8080/singleplayer/"+nameField.getText());
                try {
                    URLConnection nameVerify  = singleplayerGame.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(nameVerify.getInputStream()));
                    String inputLine = in.readLine();
                    int ID = Integer.parseInt(inputLine);
                    this.mainCtrl.setCurrentGameID(ID);
                    this.mainCtrl.showMostPowerQuestion();
                } catch (IOException e) {
                    errorLabel.setVisible(true);
                    errorLabel.setText("Could not connect to server!");
                }
            }
        }
        else{
            errorLabel.setVisible(true);
            errorLabel.setText("Name can only contain letters/numbers!");
        }
    }
    public void onClickMenu(){
        this.mainCtrl.showSplashResied();
    }
    public void setSingleplayer(){
        isSingleplayer = true;
    }
    public void setMultiplayer(){
        isSingleplayer = false;
    }
}
