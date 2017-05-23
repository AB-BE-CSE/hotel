package main.java.com.hotel.metier.layouts;

import com.jfoenix.controls.JFXRippler;
import com.jfoenix.effects.JFXDepthManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.stream.IntStream;

/**
 * Created by Admin on 22/05/2017.
 */
public abstract class Planning extends BorderPane {

    protected GridPane header;
    protected GridPane plannig;
    protected int nbrCol;
    private GridPane leftside;

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


        IntStream.range(0, 21).forEach(value -> {
            leftside.add(createRippler("Chambre: " + String.format("%02d", value), 120), 0, value);
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(45);
            plannig.getRowConstraints().add(rowConstraints);

        });
        setMouseListener();
//        ChambreDAO chambreDAO = (ChambreDAO) DAOFactory.getDAO(StringRessources.CHAMBRE);
//        chambreDAO.findAll().forEach(chambre -> {
//
//            leftside.add(createRippler(
//                    StringRessources.CHAMBRE + ": "
//                            + chambre.getEtage()
//                            + String.format("%02d", chambre.getNumeroChambre())
//                    , 120), 0, value);
//            RowConstraints rowConstraints = new RowConstraints();
//            rowConstraints.setPrefHeight(45);
//            plannig.getRowConstraints().add(rowConstraints);
//        });

    }

    public abstract void setMouseListener();


    public JFXRippler createRippler(String string, double width) {
        Label l1 = new Label(string);
        l1.setStyle("-fx-background-color:white;");
        l1.setPadding(new Insets(15));
        l1.setPrefWidth(width);
        l1.setPrefHeight(45);
        JFXRippler rippler1 = new JFXRippler(l1);
        JFXDepthManager.setDepth(rippler1, 2);

        return rippler1;
    }
}
