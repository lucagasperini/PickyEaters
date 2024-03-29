package com.pickyeaters.logic.view.gui.restaurateur.widget;

import com.pickyeaters.logic.view.bean.ShowDishBean;
import com.pickyeaters.logic.view.gui.VirtualPaneView;
import com.pickyeaters.logic.view.gui.VirtualWidget;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class DishListItemWidget extends VirtualWidget {
    @FXML
    private Text textName;
    @FXML
    private Text textDescription;
    @FXML
    private Button buttonUpdateDish;
    @FXML
    private Button buttonDeleteDish;
    @FXML
    private ListView<String> listviewIngredient;
    @FXML
    private CheckBox checkBoxActive;

    public DishListItemWidget(VirtualPaneView parent, ShowDishBean dishBean) {
        super("/form/restaurateur/widget/DishListItemWidget.fxml", "", parent);
        checkBoxActive.setSelected(dishBean.isActive());
        textName.setText(dishBean.getName());
        textDescription.setText(dishBean.getDescription());

        for(String i : dishBean.getIngredientList()) {
            listviewIngredient.getItems().add(i);
        }
    }


    @FXML
    private void clickCheckBoxActive(ActionEvent actionEvent) {
        Map<String, String> arg = new HashMap<>();
        arg.put("activeDish", textName.getText());
        toParent(arg);
    }

    @FXML
    private void clickButtonUpdateDish(ActionEvent actionEvent) {
        Map<String, String> arg = new HashMap<>();
        arg.put("updateDish", textName.getText());
        toParent(arg);
    }

    @FXML
    private void clickButtonDeleteDish(ActionEvent actionEvent) {
        Map<String, String> arg = new HashMap<>();
        arg.put("deleteDish", textName.getText());
        toParent(arg);
    }
}
