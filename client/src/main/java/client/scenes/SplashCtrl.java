package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class SplashCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private AnchorPane mainWindow;

    @FXML
    private Button singleplayerButton;

    @FXML
    private Button multiplayerButton;

    @Inject
    public SplashCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void singlePlayer() {
        this.mainCtrl.showSinglePlayerPrompt();
    }

    @FXML
    public void setWindowSize(double w, double h){
        mainWindow.setPrefSize(w,h);
    }

}