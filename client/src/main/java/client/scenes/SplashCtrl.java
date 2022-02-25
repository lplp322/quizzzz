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

    @Inject
    public SplashCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }
}