package com.pickyeaters.logic.view.gui.pickie;

import com.pickyeaters.logic.controller.application.SettingsController;
import com.pickyeaters.logic.controller.application.pickie.PickieController;
import com.pickyeaters.logic.view.gui.FindRestaurantView;
import com.pickyeaters.logic.view.gui.VirtualPaneView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.Map;

public class PickieHomeView extends VirtualPaneView {
    @FXML
    private Text textTitle;
    @FXML
    private Text textSubtitle;
    @FXML
    private Button buttonReviewDish;
    @FXML
    private Button buttonFindRestaurant;
    @FXML
    private Button buttonEatingPreferences;
    private PickieController controller;
    public PickieHomeView(PickieController controller, VirtualPaneView parent) {
        super("/form/pickie/Home.fxml", parent);
        this.controller = controller;
    }

    @FXML
    protected void clickFindRestaurant(ActionEvent event) {
        FindRestaurantView findRestaurantView = new FindRestaurantView(
                controller.getFindRestaurant(),
                this
        );
        findRestaurantView.show();
    }

    @Override
    protected void setup(Map<String, String> arg) {
        textTitle.setText(SettingsController.i18n("PICKY_HOME_TITLE"));
        textSubtitle.setText(SettingsController.i18n("PICKY_HOME_SUBTITLE"));
        buttonFindRestaurant.setText(SettingsController.i18n("PICKY_HOME_FINDRESTAURANT"));
        buttonReviewDish.setText(SettingsController.i18n("PICKY_HOME_REVIEWADISH"));
        buttonEatingPreferences.setText(SettingsController.i18n("PICKY_HOME_PERSONALIZEEATINGPREFERENCES"));
    }
}
