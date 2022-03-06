package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class PromptCtrl {
    @FXML
    private AnchorPane mainWindow;

    /**
     * Changes the size of the AnchorPlane
     * @param w - preferred width
     * @param h - preferred height
     */
    @FXML
    public void setWindowSize(double w, double h){
        mainWindow.setPrefSize(w,h);
    }
}
