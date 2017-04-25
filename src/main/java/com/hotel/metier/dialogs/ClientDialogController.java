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
public class ClientDialogController {
    private static ClientDialogController clientDialogController = null;
    @FXML
    private JFXDialog dialog;

    public ClientDialogController() {
        Platform.runLater(() -> {

            clear();
            dialog.setOnDialogClosed(e -> {
                clear();
            });
        });
    }

    public static ClientDialogController getInstance() {
        if (clientDialogController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientDialogController.class
                    .getResource("/main/java/com/hotel/presentation/dialogs/ClientDialog.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clientDialogController = fxmlLoader.getController();
        }
        return clientDialogController;

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
