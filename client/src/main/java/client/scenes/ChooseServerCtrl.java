package client.scenes;

import com.google.inject.Inject;

public class ChooseServerCtrl {
    private final MainCtrl mainCtrl;

    /**
     *
     * @param mainCtrl
     */
    @Inject
    public ChooseServerCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }
}
