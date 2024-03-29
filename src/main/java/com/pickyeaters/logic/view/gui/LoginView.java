package com.pickyeaters.logic.view.gui;

import com.pickyeaters.logic.controller.application.LoginController;
import com.pickyeaters.logic.controller.exception.BeanException;
import com.pickyeaters.logic.controller.exception.ControllerException;
import com.pickyeaters.logic.view.AppData;
import com.pickyeaters.logic.view.bean.LoginBean;
import com.pickyeaters.logic.controller.application.SettingsController;
import com.pickyeaters.logic.controller.exception.LoginControllerException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class LoginView extends VirtualPaneView {
    @FXML
    private TextField inputEmail;
    @FXML
    private PasswordField inputPassword;
    @FXML
    private Button buttonLogin;
    @FXML
    private Text textEmail;
    @FXML
    private Text textPassword;
    @FXML
    protected Text textTitle;
    @FXML
    protected Text textSubtitle;
    @FXML
    protected Button buttonBackLogin;

    private final LoginController controller = new LoginController();

    public LoginView(VirtualPaneView parent) {
        super("/form/Login.fxml", "LOGIN", parent);
    }

    @Override
    protected void setup(Map<String, String> arg) {
        textEmail.setText(i18n("EMAIL"));
        textPassword.setText(i18n("PASSWORD"));
        buttonLogin.setText(i18n("LOGIN"));
        textTitle.setText(i18n("TITLE"));
        textSubtitle.setText(i18n("SUBTITLE"));
        buttonBackLogin.setText(i18nGlobal("BACK"));
    }
    @FXML
    private void clickButtonLogin(ActionEvent event) {

        LoginBean loginBean = new LoginBean(
                inputEmail.getText(),
                inputPassword.getText()
        );

        try {
            AppData.getInstance().setUser(controller.auth(loginBean));
            Map<String, String> result = new HashMap<>();
            result.put("login", "true");
            showParent(result);
        } catch (ControllerException | BeanException ex) {
            showError(ex);
        }
    }

    @FXML
    private void clickButtonBackLogin(ActionEvent event) {
        VirtualPaneView.getActiveView().showParent();
    }
}
