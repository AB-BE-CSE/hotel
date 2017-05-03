package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import main.java.com.hotel.metier.dialogs.ChambreDialogController;
import main.java.com.hotel.model.Chambre;
import main.java.com.hotel.modeldao.ChambreDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;


@FXMLController(value = "/main/java/com/hotel/presentation/Chambre.fxml", title = "")
public class ChambreController implements Observer {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;

    @FXML
    private AnchorPane content;
    @FXML
    private JFXButton ajouterChambre;
    @FXML
    private TableView<Chambre> chambreTableau;
    @FXML
    private TableColumn<Chambre, Integer> idColumn;
    @FXML
    private TableColumn<Chambre, Integer> numColumn;
    @FXML
    private TableColumn<Chambre, Integer> etageColumn;
    @FXML
    private TableColumn<Chambre, String> categorieColumn;
    @FXML
    private TableColumn<Chambre, Double> prixColumn;
    @FXML
    private TableColumn<Chambre, String> descriptionColumn;
    @FXML
    private TableColumn<Chambre, Boolean> etatColumn;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        ajouterChambre.setOnAction(event -> {
            ChambreDialogController.getInstance().ouvrir((StackPane) context.getRegisteredObject("ContentPane"));
        });
        findAll();
        idColumn.setCellValueFactory(param -> param.getValue().idChambreProperty().asObject());
        categorieColumn.setCellValueFactory(param -> param.getValue().getCategorie().nomProperty());
        prixColumn.setCellValueFactory(param -> param.getValue().getCategorie().prixProperty().asObject());
        numColumn.setCellValueFactory(param -> param.getValue().numeroChambreProperty().asObject());
        etageColumn.setCellValueFactory(param -> param.getValue().etageProperty().asObject());
        descriptionColumn.setCellValueFactory(param -> param.getValue().getCategorie().descriptionProperty());
        etatColumn.setCellValueFactory(param -> param.getValue().checkProperty().asObject());

        chambreTableau.setEditable(true);


        numColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        numColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            if (event.getNewValue()== null)
                return;
            chambre.setNumeroChambre(event.getNewValue());
            modifer(chambre);
        });

        etageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        etageColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            chambre.setNumeroChambre(event.getNewValue());
            modifer(chambre);
        });

        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            chambre.getCategorie().setDescription(event.getNewValue());
            modifer(chambre);
        });

        prixColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        prixColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            chambre.getCategorie().setPrix(event.getNewValue());
            modifer(chambre);
        });
        ChambreDialogController.addObserver(this);
    }

    private void findAll() {
        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
        chambreTableau.getItems().clear();
        chambreTableau.getItems().addAll(chambreDAO.findAll());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Chambre) {
            chambreTableau.getItems().add((Chambre) arg);
        }
    }

    private void modifer(Chambre chambre) {
        // creation d'un DAO, appelle de la maéthode update
        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
        chambreDAO.update(chambre);
    }
}
