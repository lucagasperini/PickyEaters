package com.pickyeaters.logic.view.gui.pickie;

import com.pickyeaters.logic.controller.application.pickie.EatingPreferencesController;
import com.pickyeaters.logic.controller.exception.BeanException;
import com.pickyeaters.logic.controller.exception.DAOException;
import com.pickyeaters.logic.view.AppData;
import com.pickyeaters.logic.view.bean.AllergyBean;
import com.pickyeaters.logic.view.bean.DishIngredientBean;
import com.pickyeaters.logic.view.bean.EatingPreferenceBean;
import com.pickyeaters.logic.view.bean.PreferenceIngredientBean;
import com.pickyeaters.logic.view.gui.VirtualPaneView;
import com.pickyeaters.logic.view.gui.virtual.VirtualShowIngredientView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EatingPreferencesView extends VirtualShowIngredientView {
    private final EatingPreferencesController controller = new EatingPreferencesController();
    public EatingPreferencesView(VirtualPaneView parent) {
        super("/form/pickie/EatingPreferences.fxml", "PICKY_PERSONALIZEEATINGPREFERENCES", parent);

        textTaste.setText(i18n("taste"));
        textTasteMyDislikedIngredients.setText(i18n("taste_my_disliked_ingredients"));
        textTasteExplanation.setText(i18n("taste_explanation"));
        buttonAddIngredient.setText(i18n("add_disliked_ingredient"));
        textReligiousNeeds.setText(i18n("religious_needs"));
        checkboxReligiousHalal.setText(i18n("religious_needs_halal"));
        checkboxReligiousKosher.setText(i18n("religious_needs_kosher"));
        textHealthNeeds.setText(i18n("health_needs"));
        textHealthAllergies.setText(i18n("health_needs_allergies"));
        textHealthOthers.setText(i18n("health_needs_others"));
        checkboxHealthPregnant.setText(i18n("health_needs_others_pregnant"));
        textLifeStyleNeeds.setText(i18n("lifestyle_needs"));
        checkboxLifestyleVegan.setText(i18n("lifestyle_needs_vegan"));
        checkboxLifestyleVegetarian.setText(i18n("lifestyle_needs_vegetarian"));
        checkboxLifestylePescatarian.setText(i18n("lifestyle_needs_pescatarian"));
        checkboxLifestyleCarnivore.setText(i18n("lifestyle_needs_carnivore"));
        buttonSave.setText(i18nGlobal("SAVECHANGES"));
        try {
            List<String> allergyList = controller.getAllergyList();

            for(String allergy : allergyList) {
                vboxAllergy.getChildren().add(new CheckBox(allergy));
            }

            EatingPreferenceBean bean = controller.getUserPreferences(AppData.getInstance().getUser());
            checkboxHealthPregnant.setSelected(bean.isPregnant());
            checkboxLifestyleCarnivore.setSelected(bean.isCarnivore());
            checkboxLifestylePescatarian.setSelected(bean.isPescatarian());
            checkboxLifestyleVegetarian.setSelected(bean.isPescatarian());
            checkboxReligiousHalal.setSelected(bean.isHalal());
            checkboxReligiousKosher.setSelected(bean.isKosher());
            checkboxLifestyleVegan.setSelected(bean.isVegan());
            for(PreferenceIngredientBean ingredient : bean.getIngredientList()) {
                setupAddIngredient(
                        ingredient.getName(),
                        ingredient.isCooked() ? "true" : "false",
                        null
                );
            }

            for(String allergy : bean.getAllergyList()) {
                for(Node node : vboxAllergy.getChildren()) {
                    CheckBox cb = (CheckBox) node;
                    if(cb.getText().equals(allergy)) {
                        cb.setSelected(true);
                    }
                }
            }
        } catch (DAOException | BeanException e) {
            showError(e);
        }
    }

    @Override
    protected void setup(Map<String, String> arg) {
        if(arg != null) {
            setupAddIngredient(
                    arg.get("addIngredientName"),
                    arg.get("addIngredientCooked"),
                    arg.get("addIngredientOptional")
            );
            setupRemoveIngredient(arg.get("removeIngredient"));
        }
        showTitle();

    }

    @FXML
    private Button buttonAddIngredient;

    @FXML
    private Button buttonSave;

    @FXML
    private CheckBox checkboxAllergen;

    @FXML
    private CheckBox checkboxHealthPregnant;

    @FXML
    private CheckBox checkboxLifestyleCarnivore;

    @FXML
    private CheckBox checkboxLifestylePescatarian;

    @FXML
    private CheckBox checkboxLifestyleVegan;

    @FXML
    private CheckBox checkboxLifestyleVegetarian;

    @FXML
    private CheckBox checkboxReligiousHalal;

    @FXML
    private CheckBox checkboxReligiousKosher;

    @FXML
    private Text textHealthAllergies;

    @FXML
    private Text textHealthNeeds;

    @FXML
    private Text textHealthOthers;

    @FXML
    private Text textLifeStyleNeeds;

    @FXML
    private Text textReligiousNeeds;

    @FXML
    private Text textTaste;

    @FXML
    private Text textTasteExplanation;

    @FXML
    private Text textTasteMyDislikedIngredients;

    @FXML
    private VBox vboxIngredient;

    @FXML
    private VBox vboxAllergy;

    @FXML
    protected void clickAddIngredient(ActionEvent event) {
        AddDislikedIngredientView view = new AddDislikedIngredientView(this);
        view.show();
    }

    @FXML
    void clickSaveChanges(ActionEvent event) {
        try {
            EatingPreferenceBean bean = new EatingPreferenceBean();
            bean.setCarnivore(checkboxLifestyleCarnivore.isSelected());
            bean.setHalal(checkboxReligiousHalal.isSelected());
            bean.setVegan(checkboxLifestyleVegan.isSelected());
            bean.setKosher(checkboxReligiousKosher.isSelected());
            bean.setPescatarian(checkboxLifestylePescatarian.isSelected());
            bean.setPregnant(checkboxHealthPregnant.isSelected());
            bean.setVegetarian(checkboxLifestyleVegetarian.isSelected());

            List<String> allergyList = new ArrayList<>();
            for(Node node : vboxAllergy.getChildren()) {
                CheckBox cb = (CheckBox) node;
                if(cb.isSelected()) {
                    allergyList.add(cb.getText());
                }
            }
            List<PreferenceIngredientBean> ingredientList = new ArrayList<>();
            for(DishIngredientBean ingredientBean : ingredientBeanList) {
                ingredientList.add(new PreferenceIngredientBean(
                        ingredientBean.getName(),
                        ingredientBean.isCooked()
                ));
            }

            bean.setAllergyList(allergyList);
            bean.setIngredientList(ingredientList);

            controller.setUserPreferences(bean, AppData.getInstance().getUser());
        } catch (BeanException | DAOException e) {
            showError(e);
        }
    }

}
