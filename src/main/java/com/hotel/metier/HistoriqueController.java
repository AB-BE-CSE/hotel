package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXDatePicker;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.model.Reservation;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.ReservationDAO;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Date;


@FXMLController(value = "/main/java/com/hotel/presentation/Historique.fxml", title = "")
public class HistoriqueController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;
    @FXML
    private AnchorPane content;
    @FXML
    private JFXDatePicker dateDebutPicker;
    @FXML
    private JFXDatePicker dateFinPicker;
    @FXML
    private Label totalLabel;
    @FXML
    private TableView<Reservation> historiqueTable;
    @FXML
    private TableColumn<Reservation, Integer> idReservationColumn;
    @FXML
    private TableColumn<Reservation, String> userColumn;
    @FXML
    private TableColumn<Reservation, String> clientColumn;
    @FXML
    private TableColumn<Reservation, Date> dateArriveeColumn;
    @FXML
    private TableColumn<Reservation, Date> dateSortieColumn;
    @FXML
    private TableColumn<Reservation, Double> totalColumn;


    @PostConstruct
    public void init() throws FlowException, VetoException {

        idReservationColumn.setCellValueFactory(param -> param.getValue().idReservationProperty().asObject());
        userColumn.setCellValueFactory(param -> param.getValue().getUtilisateur().usernameProperty());
        clientColumn.setCellValueFactory(param -> param.getValue().getClient().nomProperty());
        dateArriveeColumn.setCellValueFactory(param -> param.getValue().dateArriveProperty());
        dateSortieColumn.setCellValueFactory(param -> param.getValue().dateSortieProperty());
        totalColumn.setCellValueFactory(param -> param.getValue().getFacture().sommeProperty().asObject());
        dateDebutPicker.setValue(LocalDate.now());
        dateFinPicker.setValue(LocalDate.now());
        findBetween();

        dateDebutPicker.setOnAction(e -> {
            findBetween();
        });
        dateFinPicker.setOnAction(e -> {
            findBetween();
        });
    }

    private void findBetween() {

        ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDAO(StringRessources.RESERVATION);
        historiqueTable.getItems().clear();
//        historiqueTable.getItems().addAll(reservationDAO.findBetween(null, null));

    }


}
