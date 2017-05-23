package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import main.java.com.hotel.metier.dialogs.ChambreDialogController;
import main.java.com.hotel.model.Categorie;
import main.java.com.hotel.model.Chambre;
import main.java.com.hotel.modeldao.CategorieDAO;
import main.java.com.hotel.modeldao.ChambreDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;


@FXMLController(value = "/main/java/com/hotel/presentation/Chambre.fxml", title = "")
public class ChambreController implements Observer, Gestion {

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
    private TableColumn<Chambre, Categorie> categorieColumn;
    @FXML
    private TableColumn<Chambre, Double> prixColumn;
    @FXML
    private TableColumn<Chambre, String> descriptionColumn;
    @FXML
    private TableColumn<Chambre, Boolean> etatColumn;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        ajouterChambre.setOnAction(event -> ajouter());
        findAll();
        idColumn.setCellValueFactory(param -> param.getValue().idChambreProperty().asObject());
//        categorieColumn.setCellValueFactory(param -> param.getValue().getCategorie().nomProperty());
        prixColumn.setCellValueFactory(param -> param.getValue().getCategorie().prixProperty().asObject());
        numColumn.setCellValueFactory(param -> param.getValue().numeroChambreProperty().asObject());
        etageColumn.setCellValueFactory(param -> param.getValue().etageProperty().asObject());
        descriptionColumn.setCellValueFactory(param -> param.getValue().getCategorie().descriptionProperty());
        etatColumn.setCellValueFactory(param -> param.getValue().checkedProperty().asObject());

        chambreTableau.setEditable(true);


        numColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        numColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            if (event.getNewValue() == null)
                return;
            chambre.setNumeroChambre(event.getNewValue());
            modifier(chambre);
        });

        etageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        etageColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            chambre.setNumeroChambre(event.getNewValue());
            modifier(chambre);
        });

        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            chambre.getCategorie().setDescription(event.getNewValue());
            modifier(chambre);
        });

        prixColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        prixColumn.setOnEditCommit(event -> {
            Chambre chambre = event.getRowValue();
            chambre.getCategorie().setPrix(event.getNewValue());
            modifier(chambre);
        });
        ChambreDialogController.addObserver(this);
        DeleteContextMenu.getInstance(this);

        chambreTableau.setRowFactory(tv -> {
            TableRow<Chambre> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY) {
                    DeleteContextMenu.getInstance(this).show(chambreTableau, row.getItem());
                }
            });
            return row;
        });
        categorieColumn.setCellValueFactory(i -> new SimpleObjectProperty<>(i.getValue().getCategorie()));


        ObservableList<Categorie> categories = FXCollections.observableArrayList(
                ((CategorieDAO) DAOFactory.getDAO(StringRessources.CATEGORIE)).findAll());
        categorieColumn.setCellFactory(ComboBoxTableCell.forTableColumn(categories));
        categorieColumn.setOnEditCommit(event -> {
            Chambre chambre = (Chambre) event.getRowValue();
            if (chambre == null)
                return;

            chambre.setCategorie(event.getNewValue());
            modifier(chambre);
            chambreTableau.refresh();
        });
    }

    private void findAll() {
        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
        chambreTableau.getItems().clear();
        chambreTableau.getItems().addAll(chambreDAO.findAll());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Chambre) {
            Chambre chambre = (Chambre) arg;
            if (chambre.getIdChambre() != 0)
                chambreTableau.getItems().add((Chambre) arg);
        }
    }


    @Override
    public void ajouter() {
        ChambreDialogController.getInstance().ouvrir((StackPane) context.getRegisteredObject("ContentPane"));
    }

    @Override
    public void modifier(Object chambre) {
        // creation d'un DAO, appelle de la maéthode update
        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
        chambreDAO.update((Chambre) chambre);
    }

    @Override
    public void supprimer(Object toDelete) {
        if (!(toDelete instanceof Chambre)) {
            throw new IllegalArgumentException();
        }
        if (toDelete == null || ((Chambre) toDelete).getIdChambre() == 0)
            return;

        chambreTableau.getItems().remove(toDelete);
        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
        chambreDAO.delete((Chambre) toDelete);

    }

    @Override
    public void chercher() {

    }
}
