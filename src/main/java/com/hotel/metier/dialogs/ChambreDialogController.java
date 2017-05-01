package main.java.com.hotel.metier.dialogs;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Categorie;
import main.java.com.hotel.model.Chambre;
import main.java.com.hotel.modeldao.CategorieDAO;
import main.java.com.hotel.modeldao.ChambreDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Admin on 22/03/2017.
 */
public class ChambreDialogController implements Observer {
    private static ChambreDialogController chambreDialogController = null;
    @FXML
    private JFXDialog dialog;


    @FXML
    private JFXTextField numDebut;
    @FXML
    private JFXTextField numFIN;

    @FXML
    private JFXTextField etage;
    @FXML
    private JFXComboBox<Categorie> categorie;


    public ChambreDialogController() {

        Platform.runLater(() -> {
/*
            numFIN.textProperty().addListener((observable, oldValue, newValue) -> {
                numFIN.validate();
            });
            numDebut.textProperty().addListener((observable, oldValue, newValue) -> {
                numDebut.validate();
            });
            etage.textProperty().addListener((observable, oldValue, newValue) -> {
                etage.validate();
            });
*/
            CategorieDAO.addObserver(this);
            categorie.getItems().addAll(((CategorieDAO) DAOFactory.getDAO(StringRessources.CATEGORIE)).findAll());
            clear();
            dialog.setOnDialogClosed(e -> {
                clear();
            });
        });
    }

    public static ChambreDialogController getInstance() {
        if (chambreDialogController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(ChambreDialogController.class
                    .getResource("/main/java/com/hotel/presentation/dialogs/ChambreDialog.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            chambreDialogController = fxmlLoader.getController();
        }
        return chambreDialogController;

    }

    @FXML
    private void enregistrer() {
        if (!numFIN.validate() || !numDebut.validate() || !etage.validate())
            return;
        int fin = Integer.valueOf(numFIN.getText());
        for (int i = Integer.valueOf(numDebut.getText()); i <= fin; i++) {
            Chambre chambre = new Chambre();
            chambre.setNumeroChambre(i);
            chambre.setEtage(Integer.valueOf(etage.getText()));
            chambre.setCheck(false);
            chambre.setCategorie(categorie.getValue());
            ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
            chambreDAO.create(chambre);
        }

        dialog.close();
    }

    @FXML
    private void annuler() {

        dialog.close();
    }

    @FXML
    private void ouvrirCategorieDialog() {
        CategorieDialogController.getInstance().ouvrir(dialog.getDialogContainer());
    }

    public void ouvrir(StackPane stackPane) {
        dialog.show(stackPane);

    }

    private void clear() {

        numFIN.setText("");
        numDebut.setText("");
        etage.setText("");
        categorie.setValue(null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Categorie)
            categorie.getItems().add((Categorie) arg);
    }
}
