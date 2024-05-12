package hei.school.gas_station.models;
import lombok.*;
import java.time.Instant;
import hei.school.gas_station.repository.utils.MovementStockType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovementStock {
    private long id;
    private Instant movementDatetime;
    private MovementStockType movementType;
    private double movementQuantity;
    private double movementAmount;
    private Stations station;
    private Products product;
}
