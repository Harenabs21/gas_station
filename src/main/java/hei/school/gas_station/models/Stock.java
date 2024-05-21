package hei.school.gas_station.models;
import lombok.*;
import java.time.Instant;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Stock  {
    private long id;
    private double quantity;
    private Instant stockDatetime;
    private Stations station;
    private Products product;

    public Stock(double quantity, Instant now, Stations station, Products product) {
        this.quantity = quantity;
        this.stockDatetime = now;
        this.station = station;
        this.product = product;
    }
}
