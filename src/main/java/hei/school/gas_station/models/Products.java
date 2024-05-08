package hei.school.gas_station.models;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Products {
    private long id;
    private String productName;
    private double productCapacity;
    private double unitPrice;
    private int evaporationRate;
}
