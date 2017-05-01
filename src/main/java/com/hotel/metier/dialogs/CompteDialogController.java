package main.java.com.hotel.metier.dialogs;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Utilisateur;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.UtilisateurDAO;
import org.hsqldb.rights.User;

import java.io.IOException;

/**
 * Created by Admin on 22/03/2017.
 */
public class CompteDialogController {
    private static CompteDialogController compteDialogController = null;
    @FXML
    private JFXDialog dialog;
    @FXML
    private JFXTextField nomDuCompte;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmPass;
    @FXML
    private JFXComboBox<String> type;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField numTel;

    public CompteDialogController() {
        Platform.runLater(() -> {
            numTel.textProperty().addListener((observable, oldValue, newValue) -> {
                numTel.validate();
            });
            type.getItems().addAll(Utilisateur.Type.ADMIN.toString(), Utilisateur.Type.CHEF.toString(), Utilisateur.Type.RECEPTIONISTE.toString());
            clear();
            dialog.setOnDialogClosed(e -> {
                clear();
            });
        });
    }

    public static CompteDialogController getInstance() {
        if (compteDialogController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(CompteDialogController.class
                    .getResource("/main/java/com/hotel/presentation/dialogs/CompteDialog.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            compteDialogController = fxmlLoader.getController();
        }
        return compteDialogController;

    }

    @FXML
    private void enregistrer() {
        if (!nom.validate() || !prenom.validate() || !numTel.validate() || !nomDuCompte.validate() || !password.validate() || !confirmPass.validate())
            return;
        if (!password.getText().equals(confirmPass.getText()))
            return;

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nom.getText());
        utilisateur.setUsername(nomDuCompte.getText());
        utilisateur.setPrenom(prenom.getText());
        utilisateur.setPassword(password.getText());
        utilisateur.setTel(numTel.getText());
        utilisateur.setType(type.getValue().charAt(0) + "");
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        utilisateurDAO.create(utilisateur);
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
