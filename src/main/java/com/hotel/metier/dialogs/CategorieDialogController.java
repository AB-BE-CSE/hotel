package main.java.com.hotel.metier.dialogs;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Categorie;
import main.java.com.hotel.modeldao.CategorieDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import java.io.IOException;

/**
 * Created by Admin on 22/03/2017.
 */
public class CategorieDialogController {
    private static CategorieDialogController categorieDialogController = null;
    @FXML
    private JFXDialog dialog;

    @FXML
    private JFXTextField nomCategorie;

    @FXML
    private JFXTextField prix;

    @FXML
    private JFXTextField description;


    public CategorieDialogController() {
        Platform.runLater(() -> {
/*            prix.textProperty().addListener((observable, oldValue, newValue) -> {
                prix.validate();
            });*/

            clear();
            dialog.setOnDialogClosed(e -> {
                clear();
            });
        });
    }

    public static CategorieDialogController getInstance() {
        if (categorieDialogController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(CategorieDialogController.class
                    .getResource("/main/java/com/hotel/presentation/dialogs/CategorieDialog.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            categorieDialogController = fxmlLoader.getController();
        }
        return categorieDialogController;

    }

    @FXML
    private void enregistrer() {
        boolean erreur= false;
        if(!nomCategorie.validate())
            erreur =true;
        if (!prix.validate())
            erreur = true;
        if(erreur)
            return;

        Categorie categorie = new Categorie();
        categorie.setNom(nomCategorie.getText());
        categorie.setPrix(Double.valueOf(prix.getText()));
        categorie.setDescription(description.getText());
        CategorieDAO categorieDAO = (CategorieDAO) DAOFactory.getDAO(StringRessources.CATEGORIE);
        categorieDAO.create(categorie);
        dialog.close();
    }

    @FXML
    private void annuler() {

        dialog.close();
    }

    public void ouvrir(StackPane stackPane) {
        dialog.show(stackPane);

    }

    private void clear() {

    }
}
