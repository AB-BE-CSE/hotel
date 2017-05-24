package main.java.com.hotel.metier.layouts;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import main.java.com.hotel.metier.StringRessources;
import main.java.com.hotel.metier.dialogs.ReservationDialogController;
import main.java.com.hotel.metier.menus.ReservationContextMenu;
import main.java.com.hotel.model.Reservation;
import main.java.com.hotel.modeldao.ChambreDAO;
import main.java.com.hotel.modeldao.DAOFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nadir Belarouci on 22/05/2017.
 */
public abstract class Planning extends BorderPane implements Observer {
    protected GridPane header;
    protected GridPane plannig;
    protected int nbrCol;
    protected boolean drawing;
    protected int width;
    private GridPane leftside;
    private Reservation reservation;
    private ViewFlowContext context;

    public Planning() {
        leftside = new GridPane();
        header = new GridPane();
        plannig = new GridPane();
        ScrollPane headerScrollPane = new ScrollPane();
        ScrollPane planningScrollPane = new ScrollPane();
        ScrollPane leftsideScrollPane = new ScrollPane();
        planningScrollPane.setContent(plannig);
        headerScrollPane.setContent(header);
        leftsideScrollPane.setContent(leftside);

        headerScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        headerScrollPane.hvalueProperty().bind(planningScrollPane.hvalueProperty());
        headerScrollPane.setMinHeight(60);

        leftsideScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftsideScrollPane.vvalueProperty().bind(planningScrollPane.vvalueProperty());
        leftsideScrollPane.setMinWidth(120);


        BorderPane.setMargin(headerScrollPane, new Insets(0, 10, 0, 120));
        BorderPane.setMargin(leftsideScrollPane, new Insets(0, 0, 20, 0));
        leftside.setVgap(1);
        plannig.setVgap(1);
        setTop(headerScrollPane);
        setCenter(planningScrollPane);
        setLeft(leftsideScrollPane);


//        IntStream.range(0, 21).forEach(value -> {
//            leftside.add(createRippler("Chambre: " + String.format("%02d", value), 120, "WHITE"), 0, value);
//            RowConstraints rowConstraints = new RowConstraints();
//            rowConstraints.setPrefHeight(45);
//            plannig.getRowConstraints().add(rowConstraints);
//
//        });
        drawing = false;
        setMouseListener();
        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);


        chambreDAO.findAll().forEach(chambre -> {
            leftside.addColumn(0,createRippler(
                    StringRessources.CHAMBRE + ": "
                            + chambre.getEtage()
                            + String.format("%02d", chambre.getNumeroChambre())
                    , 120,"WHITE"));
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(45);
            plannig.getRowConstraints().add(rowConstraints);
        });

    }


    @Override
    public void update(Observable o, Object arg) {
        drawing = (Boolean) arg;
        if (drawing) {
            reservation = new Reservation();

        } else {

            ReservationDialogController.getInstance(reservation).ouvrir((StackPane) context.getRegisteredObject("ContentPane"));;
        }
    }

    public void setMouseListener() {
        plannig.setOnMousePressed(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                ReservationContextMenu.getInstance(this).show(plannig);
            }
        });
        plannig.setOnMouseDragged(event -> {
            if (drawing) {
                JFXRippler rippler = createRippler("", width, "#00ADB5");
                if (getNodeFromGridPane(plannig, (int) (event.getX() * nbrCol / plannig.getWidth()),
                        (int) (event.getY() * plannig.getRowConstraints().size() / plannig.getHeight())) == null) {

                    plannig.add(rippler, (int) (event.getX() * nbrCol / plannig.getWidth()), (int) (event.getY() * plannig.getRowConstraints().size() / plannig.getHeight()));
                }
            }
        });
    }

    public JFXRippler createRippler(String string, double width, String color) {
        Label l1 = new Label(string);
        l1.setStyle("-fx-background-color:" + color + ";");
        l1.setPadding(new Insets(15));
        l1.setPrefWidth(width);
        l1.setPrefHeight(45);
        JFXRippler rippler1 = new JFXRippler(l1);
        JFXDepthManager.setDepth(rippler1, 2);

        return rippler1;
    }

    protected Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {

            if (node instanceof JFXRippler)
                if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                    return node;
                }
        }
        return null;
    }

    public void setContext(ViewFlowContext context) {
        this.context = context;
    }
}
