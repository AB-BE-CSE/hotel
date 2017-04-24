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
public class DialogSample {
    private static DialogSample dialogSample = null;
    @FXML
    private JFXDialog dialog;

    public DialogSample() {
        Platform.runLater(() -> {

            clear();
            dialog.setOnDialogClosed(e -> {
                clear();
            });
        });
    }

    public static DialogSample getInstance() {
        if (dialogSample == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogSample.class
                    .getResource("/main/java/com/hotel/presentation/dialogs/DialogSample.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialogSample = fxmlLoader.getController();
        }
        return dialogSample;

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
