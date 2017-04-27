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

import javax.annotation.PostConstruct;



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
    private TableView tableHisto;
    @FXML
    private TableColumn idReservationColumn;
    @FXML
    private TableColumn clientColumn;
    @FXML
    private TableColumn dateArriveeColumn;
    @FXML
    private TableColumn dateSorieColumn;
    @FXML
    private TableColumn totalColumn;


    @PostConstruct
    public void init() throws FlowException, VetoException {


    }


}
