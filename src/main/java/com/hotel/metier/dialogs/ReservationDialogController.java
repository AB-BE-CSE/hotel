package main.java.com.hotel.metier.dialogs;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.login.LoginController;
import main.java.com.hotel.login.PrincipalImpl;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.model.Client;
import main.java.com.hotel.model.Facture;
import main.java.com.hotel.model.Reservation;
import main.java.com.hotel.model.Utilisateur;
import main.java.com.hotel.modeldao.ClientDAO;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.ReservationDAO;

import java.io.IOException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Admin on 22/03/2017.
 */
public class ReservationDialogController implements Observer {
    private static ReservationDialogController reservationDialogController = null;
    @FXML
    private JFXDialog dialog;

    @FXML
    private JFXTextField somme;

    @FXML
    private JFXListView<Client> clients;

    private Reservation reservation;


    public ReservationDialogController() {
        Platform.runLater(() -> {
            clients.getItems().addAll(
                    ((ClientDAO) DAOFactory.getDAO(StringRessources.CLIENT))
                            .findAll());

            ClientDAO.addObserver(this);
            clear();
            dialog.setOnDialogClosed(e -> {
                clear();
            });

        });
    }

    public static ReservationDialogController getInstance(Reservation reservation) {
        if (reservationDialogController == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(ReservationDialogController.class
                    .getResource("/main/java/com/hotel/presentation/dialogs/ReservationDialog.fxml"));
            try {
                fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            reservationDialogController = fxmlLoader.getController();
        }
        reservationDialogController.reservation = reservation;
        return reservationDialogController;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @FXML
    private void enregistrer() {
        boolean erreur = false;
        if (!somme.validate())
            erreur = true;

        if (erreur)
            return;

        reservation.setDateReservation(new Date());
        reservation.setFacture(new Facture(0, new Date(), Double.valueOf(somme.getText())));
        Utilisateur utilisateur = ((PrincipalImpl) LoginController.getLoginContext().getSubject().getPrincipals().iterator().next()).getUser();
        reservation.setUtilisateur(utilisateur);
        reservation.setClient(clients.getSelectionModel().getSelectedItem());

        ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAO(StringRessources.RESERVATION);
        reservationDAO.create(reservation);
        dialog.close();
    }

    @FXML
    private void annuler() {

        dialog.close();
    }

    @FXML
    private void ajouterClient() {
        ClientDialogController.getInstance().ouvrir(dialog.getDialogContainer());
    }

    public void ouvrir(StackPane stackPane) {
        dialog.show(stackPane);

    }

    private void clear() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Client) {
            clients.getItems().add((Client) arg);
        }
    }
}
