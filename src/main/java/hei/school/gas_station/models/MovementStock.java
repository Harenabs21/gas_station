package hei.school.gas_station.models;
import lombok.*;
import java.time.Instant;
import hei.school.gas_station.repository.utils.MovementStockType;
import hei.school.gas_station.models.templates.StationAndProductRel;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MovementStock extends StationAndProductRel{
    private long id;
    private Instant movementDatetime;
    private MovementStockType movementType;

    public MovementStock(long id, Instant movementDatetime, MovementStockType movementType, long idStation, long idProduct) {
        super(idStation, idProduct);
        this.id = id;
        this.movementDatetime = movementDatetime;
        this.movementType = movementType;
    }
}
