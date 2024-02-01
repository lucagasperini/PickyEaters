package com.pickyeaters.logic.view.cli.restaurateur;

import com.pickyeaters.logic.controller.application.SettingsController;
import com.pickyeaters.logic.controller.application.restaurateur.MenuDetailsController;
import com.pickyeaters.logic.controller.exception.ControllerException;
import com.pickyeaters.logic.view.bean.DishBean;
import com.pickyeaters.logic.view.cli.VirtualRequestView;

import java.util.List;
import java.util.Map;

public class MenuDetailsView extends VirtualRequestView {
    private final MenuDetailsController controller;
    public MenuDetailsView(MenuDetailsController controller) {
        super("MenuDetails");
        this.controller = controller;
    }

    @Override
    public void show(Map<String, String> arg) {
        requestLoop();
    }

    @Override
    protected boolean request(String request) {
        switch (request) {
            case "show", "s":
                showMenu();
                return true;
            case "edit", "e":
                editDish();
                return true;
            case "add", "a":
                addDish();
                return true;
            case "remove", "r":
                removeDish();
                return true;
            default:
                return false;
        }
    }

    @Override
    protected String requestHelp() {
        return """
                [show, s]
                [edit, e]
                [add, a]
                [remove, r]""";
    }

    private void showMenu() {
        try {
            List<DishBean> list = controller.getMenu();
            for(DishBean i : list) {
                printField("RESTAURATEUR_MANAGEMENUDETAILS_NAME",i.getName());
                printField("RESTAURATEUR_MANAGEMENUDETAILS_DESCRIPTION",i.getDescription());
                printField(
                        "RESTAURATEUR_MANAGEMENUDETAILS_CATEGORY",
                        SettingsController.i18n("DISH_TYPE_" + i.getCategory())
                );
                printFieldList("RESTAURATEUR_MANAGEMENUDETAILS_INGREDIENTS",i.getIngredientList());
                printFieldBoolean("RESTAURATEUR_MANAGEMENUDETAILS_ACTIVE",i.isActive());
                print("##################################");
            }
        } catch (ControllerException e) {
            showError(e);
        }
    }

    private void editDish() {
        throw new UnsupportedOperationException();
    }

    private void addDish() {
        throw new UnsupportedOperationException();
    }

    private void removeDish() {
        throw new UnsupportedOperationException();
    }
}