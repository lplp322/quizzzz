package client.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class LeaderboardCtrl {
    private final MainCtrl mainCtrl;

    @FXML
    private AnchorPane mainWindow;

    /**
     *
     * @param mainCtrl
     */
    @Inject
    public LeaderboardCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }

    /**
     *
     * @param w
     * @param h
     */
    @FXML
    public void setWindowSize(double w, double h) {
        mainWindow.setPrefSize(w, h);
    }
}
