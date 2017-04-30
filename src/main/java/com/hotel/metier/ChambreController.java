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
import main.java.com.hotel.metier.dialogs.ChambreDialogController;

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


    @PostConstruct
    public void init() throws FlowException, VetoException {
        ajouterChambre.setOnAction(event -> {
            ChambreDialogController.getInstance().ouvrir((StackPane)context.getRegisteredObject("ContentPane"));
        });
    }


}
