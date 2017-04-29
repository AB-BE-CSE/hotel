package main.java.com.hotel.metier.dialogs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.util.VetoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by Admin on 22/03/2017.
 */
public class ChambreDialogController {
    private static ChambreDialogController chambreDialogController = null;
    @FXML
    private JFXDialog dialog;

    @FXML
    private JFXTextField nbrchambre;
    @FXML
    private JFXTextField numDebut;
    @FXML
    private JFXTextField numFIN;
    @FXML
    private JFXTextField capacite;
    @FXML
    private JFXTextField Etage;
    @FXML
    private JFXComboBox categorie;


    public ChambreDialogController() {
        Platform.runLater(() -> {
            dialog.setTransitionType(JFXDialog.DialogTransition.BOTTOM);
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
        nbrchambre.setText("");
//TODO
        categorie.setValue(null);
    }
}
