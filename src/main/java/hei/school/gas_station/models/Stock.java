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
    private int evaporationRate;
    private Stations station;
    private Products product;
}
