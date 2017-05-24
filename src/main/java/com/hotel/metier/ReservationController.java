package main.java.com.hotel.metier;

import io.datafx.controller.FXMLController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.layouts.Planning;

import javax.annotation.PostConstruct;
/** @author Nadir Belarouci*/


@FXMLController(value = "/main/java/com/hotel/presentation/Reservation.fxml", title = "")
public class ReservationController implements Gestion {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;
    @FXML
    private AnchorPane content;
    @FXML
    private Planning oneWeekPlanning;
    @FXML
    private Planning monthPlanning;
    @FXML
    private Planning twoWeekPlanning;

    @PostConstruct
    public void init() throws FlowException, VetoException {
        oneWeekPlanning.setContext(context);
        monthPlanning.setContext(context);
        twoWeekPlanning.setContext(context);
    }


    @Override
    public void ajouter() {

    }

    @Override
    public void modifier(Object toModifier) {

    }

    @Override
    public void supprimer(Object toDelete) {

    }

    @Override
    public void chercher() {

    }
}
