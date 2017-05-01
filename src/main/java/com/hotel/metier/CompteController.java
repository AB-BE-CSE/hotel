package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.dialogs.CompteDialogController;
import main.java.com.hotel.model.Utilisateur;
import main.java.com.hotel.modeldao.DAOFactory;
import main.java.com.hotel.modeldao.UtilisateurDAO;
import org.hsqldb.rights.User;

import javax.annotation.PostConstruct;
import java.util.Observable;
import java.util.Observer;


@FXMLController(value = "/main/java/com/hotel/presentation/Compte.fxml", title = "")
public class CompteController  implements Observer{

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
    private TableColumn<Utilisateur,String> userColumn;
    @FXML
    private TableColumn<Utilisateur,String> typeColumn;
    @FXML
    private Label nomUser;
    @FXML
    private Label prenomUser;
    @FXML
    private Label tel;
    @PostConstruct
    public void init() throws FlowException, VetoException {
        ajouterCompte.setButtonType(JFXButton.ButtonType.RAISED);
        ajouterCompte.setOnAction(e -> {
            CompteDialogController.getInstance().ouvrir((StackPane) context.getRegisteredObject("ContentPane"));
        });
        UtilisateurDAO utilisateurDAO = (UtilisateurDAO) DAOFactory.getDAO(StringRessources.USER);
        userColumn.setCellValueFactory(param -> param.getValue().usernameProperty());
        typeColumn.setCellValueFactory(param -> param.getValue().typeProperty());
        userTable.getItems().addAll(utilisateurDAO.findAll());
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showDetails(newValue));
        CompteDialogController.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (o instanceof Utilisateur){
            userTable.getItems().add((Utilisateur) o);
        }
    }
    private void showDetails(Utilisateur utilisateur){
        nomUser.setText(utilisateur.getNom());
        prenomUser.setText(utilisateur.getPrenom());
        tel.setText(utilisateur.getTel());
    }
}
