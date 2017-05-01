package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.dialogs.ClientDialogController;
import main.java.com.hotel.model.Client;
import main.java.com.hotel.modeldao.ClientDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;


@FXMLController(value = "/main/java/com/hotel/presentation/Client.fxml", title = "")
public class ClientController implements Observer {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;
    @FXML
    private AnchorPane content;
    @FXML
    private JFXButton ajouterClient;
    @FXML
    private TableView tableClient;
    @FXML
    private TableColumn<Client, String> nomColumn;
    @FXML
    private TableColumn<Client, String> prenomColumn;
    @FXML
    private TableColumn<Client, Date> dateNaissColumn;
    @FXML
    private TableColumn<Client, String> numTelColumn;
    @FXML
    private TableColumn<Client, String> numPieceEntiteColumn;


    @PostConstruct
    public void init() throws FlowException, VetoException {
        ClientDialogController.addObserver(this);
        ajouterClient.setButtonType(JFXButton.ButtonType.RAISED);
        ajouterClient.setOnAction(e -> {
            ClientDialogController.getInstance().ouvrir((StackPane) context.getRegisteredObject("ContentPane"));
        });
        ClientDAO clientDAO = (ClientDAO) DAOFactory.getDAO(StringRessources.CLIENT);
        tableClient.getItems().addAll(clientDAO.findAll());
        nomColumn.setCellValueFactory(param -> param.getValue().nomProperty());
        prenomColumn.setCellValueFactory(param -> param.getValue().prenomProperty());
        dateNaissColumn.setCellValueFactory(param -> param.getValue().dateNaissanceProperty());
        numTelColumn.setCellValueFactory(param -> param.getValue().telProperty());

        tableClient.setEditable(true);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(event -> {
           Client client =  event.getRowValue();
           client.setNom(event.getNewValue());
           modifier(client);
        });

    }

    private void modifier(Client client) {
        ClientDAO clientDAO = (ClientDAO) DAOFactory.getDAO(StringRessources.CLIENT);
        clientDAO.update(client);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Client){
            tableClient.getItems().add(arg);
        }
    }
}
