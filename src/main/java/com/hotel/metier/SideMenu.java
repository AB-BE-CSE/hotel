package main.java.com.hotel.metier;

import com.jfoenix.controls.JFXListView;
import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;
import java.util.Observer;

@FXMLController(value = "/main/java/com/hotel/presentation/SideMenu.fxml", title = "")
public class SideMenu {

    private static Observer obs;
    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    @ActionTrigger("buttons")
    private Label reservation;
    @FXML
    @ActionTrigger("buttons")
    private Label client;
    @FXML
    @ActionTrigger("buttons")
    private Label chambre;
    @FXML
    @ActionTrigger("buttons")
    private Label compte;
    @FXML
    @ActionTrigger("buttons")
    private Label historique;
    @FXML
    private JFXListView<Label> sideList;

    public static void setObs(Observer obs) {
        SideMenu.obs = obs;
    }

    @PostConstruct
    public void init() throws FlowException, VetoException {
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
        sideList.propagateMouseEventsToParent();
        sideList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    contentFlowHandler.handle(newVal.getId());
//                    obs.update(null, this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");
        bindNodeToController(reservation, ReservationController.class, contentFlow, contentFlowHandler);
        bindNodeToController(client, ClientController.class, contentFlow, contentFlowHandler);
        bindNodeToController(compte, CompteController.class, contentFlow, contentFlowHandler);
        bindNodeToController(historique, HistoriqueController.class, contentFlow, contentFlowHandler);
        bindNodeToController(chambre, ChambreController.class, contentFlow, contentFlowHandler);

    }

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        flow.withGlobalLink(node.getId(), controllerClass);
    }

}
