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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.dialogs.ChambreDialogController;
import main.java.com.hotel.model.Chambre;
import main.java.com.hotel.modeldao.ChambreDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import javax.annotation.PostConstruct;


@FXMLController(value = "/main/java/com/hotel/presentation/Chambre.fxml", title = "")
public class ChambreController {

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
    }

    private void findAll() {
        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
        chambreTableau.getItems().clear();
        chambreTableau.getItems().addAll(chambreDAO.findAll());
    }

}
