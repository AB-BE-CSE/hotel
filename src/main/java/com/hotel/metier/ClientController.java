package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.dialogs.ClientDialogController;
import main.java.com.hotel.metier.menus.DeleteContextMenu;
import main.java.com.hotel.model.Client;
import main.java.com.hotel.modeldao.ClientDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @author Hihcham Bali
 */

@FXMLController(value = "/main/java/com/hotel/presentation/Client.fxml", title = "")
public class ClientController implements Observer, Gestion {

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
        ClientDAO.addObserver(this);
        ajouterClient.setButtonType(JFXButton.ButtonType.RAISED);
        ajouterClient.setOnAction(e -> ajouter());
        ClientDAO clientDAO = (ClientDAO) DAOFactory.getDAO(StringRessources.CLIENT);
        tableClient.getItems().addAll(clientDAO.findAll());
        nomColumn.setCellValueFactory(param -> param.getValue().nomProperty());
        prenomColumn.setCellValueFactory(param -> param.getValue().prenomProperty());
        dateNaissColumn.setCellValueFactory(param -> param.getValue().dateNaissanceProperty());
        numTelColumn.setCellValueFactory(param -> param.getValue().telProperty());
        numPieceEntiteColumn.setCellValueFactory(param -> param.getValue().numeroPieceIdentiteProperty());

        tableClient.setEditable(true);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setNom(event.getNewValue());
            modifier(client);
        });
        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setPrenom(event.getNewValue());
            modifier(client);
        });
        numTelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numTelColumn.setOnEditCommit(event -> {
            Client client = event.getRowValue();
            client.setTel(event.getNewValue());
            modifier(client);
        });

        tableClient.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                    DeleteContextMenu.getInstance(this).show(tableClient, row.getItem());
                }
            });
            return row;
        });
    }


    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Client) {
            Client client = (Client) arg;
            if (client.getIdClient() != 0)
                tableClient.getItems().add(arg);
        }
    }

    @Override
    public void ajouter() {
        ClientDialogController.getInstance().ouvrir((StackPane) context.getRegisteredObject("ContentPane"));
    }

    @Override
    public void modifier(Object client) {
        ClientDAO clientDAO = (ClientDAO) DAOFactory.getDAO(StringRessources.CLIENT);
        clientDAO.update((Client) client);
    }

    @Override
    public void supprimer(Object toDelete) {
        if (!(toDelete instanceof Client))
            throw new IllegalArgumentException();

        if (toDelete == null || ((Client) toDelete).getIdClient() == 0)
            return;


        tableClient.getItems().remove(toDelete);
        ClientDAO clientDAO = (ClientDAO) DAOFactory.getDAO(StringRessources.CLIENT);
        clientDAO.delete((Client) toDelete);
    }

    @Override
    public void chercher() {

    }
}
