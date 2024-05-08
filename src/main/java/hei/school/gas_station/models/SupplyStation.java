package hei.school.gas_station.models;
import lombok.*;
import hei.school.gas_station.models.templates.StationAndProductRel;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SupplyStation extends StationAndProductRel {
    private long id;
    private double supplyQuantity;
    private Instant supplyDate;

    public SupplyStation(long id, double supplyQuantity, Instant supplyDate, long idStation, long idProduct) {
        super(idStation, idProduct);
        this.id = id;
        this.supplyQuantity = supplyQuantity;
        this.supplyDate = supplyDate;
    }
}
