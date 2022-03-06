package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SplashCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private Button singleplayerButton;

    @FXML
    private Button multiplayerButton;

    /**
     * Instantiates a Splash Controller
     * @param mainCtrl The Main Controller
     */
    @Inject
    public SplashCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     * Function to be executed when the singlePlayer button is pressed
     */
    public void singlePlayer() {
        this.mainCtrl.showPrompt();
    }


}