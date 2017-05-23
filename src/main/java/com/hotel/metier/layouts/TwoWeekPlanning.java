package main.java.com.hotel.metier.layouts;

import javafx.scene.layout.ColumnConstraints;

import java.time.LocalDate;
import java.util.stream.IntStream;

/**
 * Created by Admin on 20/05/2017.
 */
public class TwoWeekPlanning extends Planning {
    public TwoWeekPlanning() {
        super();
        nbrCol = 15;
        IntStream.range(0, nbrCol).forEach(value -> {
            header.add(createRippler(
                    String.format("%02d", LocalDate
                            .now()
                            .plusDays(value)
                            .getDayOfMonth()), 80), value, 0);
        });
        IntStream.range(0, nbrCol).forEach(value -> {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(80);
            plannig.getColumnConstraints().add(columnConstraints);
        });


    }

    @Override
    public void setMouseListener() {

    }
}
