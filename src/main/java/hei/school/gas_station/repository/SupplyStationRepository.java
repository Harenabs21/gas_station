package hei.school.gas_station.repository;

import hei.school.gas_station.repository.utils.CrudOperationsTemplate;
import hei.school.gas_station.repository.utils.Columns;
import hei.school.gas_station.utils.QueryTemplate;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import hei.school.gas_station.models.SupplyStation;
import org.springframework.stereotype.Repository;
@Repository
@AllArgsConstructor
public class SupplyStationRepository implements CrudOperationsTemplate<SupplyStation> {
    private final QueryTemplate qt;

    @Override
    public SupplyStation findById(long id) {
        return qt.executeSingleQuery("select * from supply_station where id = ?",
        ps -> ps.setLong(1,id),this::getResult);
    }

    @Override
    public List<SupplyStation> findAll() {
        return qt.executeQuery("select * from supply_station",this::getResult);
    }

    @Override
    public Integer save(SupplyStation toSave) {
        return qt.executeUpdate("insert into supply_station (supply_quantity,id_station,id_product) values(?,?,?)",
        ps ->{
            ps.setDouble(1,toSave.getSupplyQuantity());
            ps.setLong(2,toSave.getIdStation());
            ps.setLong(3,toSave.getIdProduct());
        });
    }

    @Override
    public Integer update(SupplyStation toUpdate) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public SupplyStation delete(SupplyStation toDelete) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private SupplyStation getResult(ResultSet rs) throws SQLException{
        Timestamp timestamp = rs.getTimestamp(Columns.SUPPLY_DATE);
        Instant instant = timestamp != null ? timestamp.toInstant() : null;
        return new SupplyStation(
            rs.getLong(Columns.ID),
            rs.getDouble(Columns.SUPPLY_QUANTITY),
            instant,
            rs.getLong(Columns.ID_STATION),
            rs.getLong(Columns.ID_PRODUCT)
        );
    }
    
}
