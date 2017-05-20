package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.dialogs.CompteDialogController;
import main.java.com.hotel.model.Utilisateur;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.UtilisateurDAO;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;


@FXMLController(value = "/main/java/com/hotel/presentation/Compte.fxml", title = "")
public class CompteController implements Observer, Gestion {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;
    @FXML
    private AnchorPane content;
    @FXML
    private JFXButton ajouterCompte;
    @FXML
    private TableView<Utilisateur> userTable;
    @FXML
    private TableColumn<Utilisateur, String> userColumn;
    @FXML
    private TableColumn<Utilisateur, StringProperty> typeColumn;
    @FXML
    private Label nomUser;
    @FXML
    private Label prenomUser;
    @FXML
    private Label tel;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        ajouterCompte.setButtonType(JFXButton.ButtonType.RAISED);
        ajouterCompte.setOnAction(e -> ajouter());
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        userColumn.setCellValueFactory(param -> param.getValue().usernameProperty());
//        typeColumn.setCellValueFactory(param -> param.getValue().typeProperty());
        userTable.getItems().addAll(utilisateurDAO.findAll());
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
        typeColumn.setCellValueFactory(i -> {
            StringProperty value = i.getValue().typeProperty();
            // binding to constant value
            return Bindings.createObjectBinding(() -> value);
        });

        userColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        userColumn.setOnEditCommit(event -> {
            Utilisateur utilisateur = event.getRowValue();
            utilisateur.setUsername(event.getNewValue());
            modifier(utilisateur);
        });

        typeColumn.setCellFactory(col -> {
            TableCell<Utilisateur, StringProperty> c = new TableCell<>();
            final JFXComboBox<String> comboBox = new JFXComboBox<>();
            comboBox.getItems().addAll(
                    Utilisateur.Type.ADMIN.toString(),
                    Utilisateur.Type.CHEF.toString(),
                    Utilisateur.Type.RECEPTIONISTE.toString());

            comboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                Utilisateur utilisateur = (Utilisateur) c.getTableRow().getItem();
                utilisateur.setType(newValue.charAt(0) + "");
                modifier(utilisateur);

            });
            c.itemProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue != null) {
                    comboBox.valueProperty().unbindBidirectional(oldValue);
                }
                if (newValue != null) {
                    comboBox.valueProperty().bindBidirectional(newValue);
                }
            });
            c.graphicProperty().bind(Bindings.when(c.emptyProperty()).then((Node) null).otherwise(comboBox));
            return c;
        });

        CompteDialogController.addObserver(this);

        userTable.setRowFactory(param -> {
            TableRow<Utilisateur> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY)
                    DeleteContextMenu.getInstance(this).show(userTable, row.getItem());
            });
            return row;
        });
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Utilisateur) {
            userTable.getItems().add((Utilisateur) o);
        }
    }

    private void showDetails(Utilisateur utilisateur) {
        nomUser.setText(utilisateur.getNom());
        prenomUser.setText(utilisateur.getPrenom());
        tel.setText(utilisateur.getTel());
    }


    @Override
    public void ajouter() {
        CompteDialogController.getInstance().ouvrir((StackPane) context.getRegisteredObject("ContentPane"));
    }

    @Override
    public void modifier(Object utilisateur) {
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        utilisateurDAO.update((Utilisateur) utilisateur);
    }

    @Override
    public void supprimer(Object toDelete) {
        userTable.getItems().remove(toDelete);
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        utilisateurDAO.delete((Utilisateur) toDelete);
    }

    @Override
    public void chercher() {

    }
}
