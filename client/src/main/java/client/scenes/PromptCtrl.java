package client.scenes;

import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PromptCtrl {
    private final MainCtrl mainCtrl;
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
    public void onClickStart(){
        if(nameField.getText().matches("[a-zA-Z0-9]+")){
            errorLabel.setVisible(false);
        }
        else{
            errorLabel.setVisible(true);
            errorLabel.setText("Name can only contain letters/numbers!");
        }
    }
    public void onClickMenu(){
        this.mainCtrl.showSplash();
    }
}
