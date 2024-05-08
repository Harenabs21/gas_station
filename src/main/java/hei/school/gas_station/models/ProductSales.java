package hei.school.gas_station.models;
import lombok.*;
import hei.school.gas_station.models.templates.StationAndProductRel;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ProductSales extends StationAndProductRel {
    private long id;
    private double salesPrice;
    private double salesCapacity;
    public ProductSales(long id, double salesPrice, double salesCapacity, long idStation, long idProduct) {
        super(idStation,idProduct);
        this.id = id;
        this.salesPrice = salesPrice;
        this.salesCapacity = salesCapacity;
    }
}