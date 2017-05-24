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
 * @author Ilyas Bouyacoub
 * Created by Ilyas Bouyacoub on 22/03/2017.
 */
public class ChambreDialogController implements Observer {
    private static ChambreDialogController chambreDialogController = null;
//    private static List<Observer> observers = new ArrayList<>();
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
            dialog.setOnDialogOpened(event -> {
                numDebut.requestFocus();
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

  /*  public static void addObserver(Observer obs) {
        observers.add(obs);
    }

    public static void updateObservers(Object o) {
        for (Observer obs : observers) {
            obs.update(null, o);
        }
    }

    public static void deleteObservers() {
        observers = new ArrayList<>();
    }*/

    @FXML
    private void enregistrer() {
        boolean erreur = false;
        if (!numFIN.validate())
            erreur = true;
        if (!numDebut.validate())
            erreur = true;
        if (!etage.validate())
            erreur = true;
        if (erreur)
            return;

        int fin = Integer.valueOf(numFIN.getText());
        int debut = Integer.valueOf(numDebut.getText());
        int etage = Integer.valueOf(this.etage.getText());

        for (int i = debut; i <= fin; i++) {
            Chambre chambre = new Chambre();
            chambre.setNumeroChambre(i);
            chambre.setEtage(etage);
            chambre.setChecked(false);
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
