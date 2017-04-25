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
public class HistoriqueDialogController {
    private static HistoriqueDialogController historiqueDialogController = null;
    @FXML
    private JFXDialog dialog;

    public HistoriqueDialogController() {
        Platform.runLater(() -> {

            clear();
            dialog.setOnDialogClosed(e -> {
                clear();
            });
        });
    }

    public static HistoriqueDialogController getInstance() {
        if (historiqueDialogController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(HistoriqueDialogController.class
                    .getResource("/main/java/com/hotel/presentation/dialogs/HistoriqueDialog.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            historiqueDialogController = fxmlLoader.getController();
        }
        return historiqueDialogController;

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
