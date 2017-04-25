package main.java.com.hotel.metier.dialogs;

import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * Created by Admin on 22/03/2017.
 */
public class ChambreDialogController {
    private static ChambreDialogController chambreDialogController = null;
    @FXML
    private JFXDialog dialog;

    public ChambreDialogController() {
        Platform.runLater(() -> {

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

    public void ouvrir(StackPane stackPane) {
        dialog.show(stackPane);

    }

    private void clear() {

    }
}
