package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXButton;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.dialogs.HistoriqueDialogController;

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
    private JFXButton ajout;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        // l'ombre d boutton//
        ajout.setButtonType(JFXButton.ButtonType.RAISED);
        // attribuer une action a ce boutton
        ajout.setOnAction(event -> {
            HistoriqueDialogController.getInstance().ouvrir((StackPane) context.getRegisteredObject("ContentPane"));
        });

    }


}
