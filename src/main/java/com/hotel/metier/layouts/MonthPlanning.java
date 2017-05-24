package main.java.com.hotel.metier.layouts;

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
        width = 50;
        IntStream.range(0, nbrCol).forEach(value -> {
            header.add(createRippler(
                    String.format("%02d", LocalDate
                            .now()
                            .plusDays(value)
                            .getDayOfMonth()), width,"WHITE")
                    , value, 0);
        });

        IntStream.range(0, nbrCol).forEach(value -> {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPrefWidth(width);
            plannig.getColumnConstraints().add(columnConstraints);
        });
       plannig.setGridLinesVisible(true);


    }


}
