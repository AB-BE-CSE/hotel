package main.java.com.hotel.metier.layouts;

import com.jfoenix.controls.JFXRippler;
import javafx.scene.layout.ColumnConstraints;

import java.time.LocalDate;
import java.util.stream.IntStream;

/**
 * Created by Admin on 20/05/2017.
 */
public class MonthPlanning extends Planning {

    public MonthPlanning() {
        super();
        nbrCol = 30;
        IntStream.range(0, nbrCol).forEach(value -> {
            header.add(createRippler(
                    String.format("%02d", LocalDate
                            .now()
                            .plusDays(value)
                            .getDayOfMonth()), 50)
                    , value, 0);
        });

        IntStream.range(0, nbrCol).forEach(value -> {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(50);
            plannig.getColumnConstraints().add(columnConstraints);
        });
        plannig.setGridLinesVisible(true);


    }

    @Override
    public void setMouseListener() {

        plannig.setOnMousePressed(event -> {
            JFXRippler rippler = createRippler("", 50);
            
            plannig.add(rippler, (int) (event.getX() * nbrCol / plannig.getWidth()), (int) (event.getY() * 20 / plannig.getHeight()));
        });
    }
}
