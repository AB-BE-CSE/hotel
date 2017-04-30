package main.java.com.hotel.metier.dialogs;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Client;
import main.java.com.hotel.modeldao.ClientDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Admin on 22/03/2017.
 */
public class ClientDialogController {
    private static ClientDialogController clientDialogController = null;
    @FXML
    private JFXDialog dialog;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField numeroTel;
    @FXML
    private JFXTextField numeroPieceEntite;

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
        if (!numeroTel.validate() || !nom.validate() || !prenom.validate())
            return;
        Client client = new Client();
        client.setNom(nom.getText());
        client.setPrenom(prenom.getText());
        client.setTel(numeroTel.getText());
        client.setDateNaissance(Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        ClientDAO clientDAO = (ClientDAO) DAOFactory.getDAO(StringRessources.CLIENT);
//        clientDAO.create(client);
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
