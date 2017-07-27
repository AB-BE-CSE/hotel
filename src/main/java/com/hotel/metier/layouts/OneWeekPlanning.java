package main.java.com.hotel.metier.layouts;

import javafx.scene.layout.ColumnConstraints;

import java.time.LocalDate;
import java.util.stream.IntStream;

/**
 * Created by Nadir Belarouci on 20/05/2017.
 */
public class OneWeekPlanning extends Planning {
    public OneWeekPlanning() {
        super();
        nbrCol = 7;
        width = 170;
        IntStream.range(0, nbrCol).forEach(value -> {
            header.add(createRippler(
                    String.format("%02d", LocalDate
                            .now()
                            .plusDays(value)
                            .getDayOfMonth()), width, "WHITE"), value, 0);


        });

        IntStream.range(0, nbrCol).forEach(value -> {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(width);
            plannig.getColumnConstraints().add(columnConstraints);
        });


    }


}
