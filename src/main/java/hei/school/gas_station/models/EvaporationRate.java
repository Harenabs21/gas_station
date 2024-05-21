package hei.school.gas_station.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EvaporationRate {
    private long id;
    private int rateValue;
    private  Stations station;
    private  Products product;
}
