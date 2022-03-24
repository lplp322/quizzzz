package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ActivityViewerCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private AnchorPane mainWindow;

    @FXML
    private Button quitButton;


    /**
     * Instantiates a Splash Controller
     * @param mainCtrl The Main Controller
     */
    @Inject
    public ActivityViewerCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    public void setWindowSize(double w, double h){
        mainWindow.setPrefSize(w,h);
    }

    public void returnToMenu(){
        this.mainCtrl.showSplash();
    }
}
