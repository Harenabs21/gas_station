package hei.school.gas_station.repository;

import hei.school.gas_station.models.EvaporationRate;
import hei.school.gas_station.repository.utils.Columns;
import hei.school.gas_station.repository.utils.CrudOperationsTemplate;
import hei.school.gas_station.utils.QueryTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@AllArgsConstructor
@Repository
public class EvaporationRateRepository implements CrudOperationsTemplate<EvaporationRate> {
    private final QueryTemplate qt;
    private final StationsRepository stationsRepository;
    private final ProductsRepository productsRepository;
    @Override
    public EvaporationRate findById(long id) {
        return qt.executeSingleQuery("select * from evaporation_rate where id = ?",
                ps -> ps.setLong(1,id) ,this::getResultSet);
    }

    @Override
    public List<EvaporationRate> findAll() {
        return qt.executeQuery("select * from evaporation_rate" , this::getResultSet);
    }

    public List<EvaporationRate> findByIdStation(long stationId) {
        return qt.executeQuery("select * from evaporation_rate where id_station = ?",
                ps -> ps.setLong(1,stationId),
                this::getResultSet);
    }

    public EvaporationRate findByIdStationAndIdProduct(long stationId, long productId) {
        return qt.executeSingleQuery("select * from evaporation_rate where id_station = ? and id_product = ?",
                ps -> {
                    ps.setLong(1, stationId);
                    ps.setLong(2, productId);
                },this::getResultSet);
    }

    @Override
    public EvaporationRate save(EvaporationRate toSave) {
        return qt.executeUpdate("insert into evaporation_rate (rate_value,id_station,id_product) values ?,?,?",
                ps -> {
                    ps.setInt(1, toSave.getRateValue());
                    ps.setLong(2, toSave.getStation().getId());
                    ps.setLong(3,toSave.getProduct().getId());
                }) != 0 ? toSave : null;
    }

    @Override
    public EvaporationRate update(EvaporationRate toUpdate) {
        return null;
    }

    @Override
    public EvaporationRate delete(EvaporationRate toDelete) {
        return null;
    }

    private EvaporationRate getResultSet(ResultSet rs) throws SQLException {
        return new EvaporationRate(
                rs.getLong(Columns.ID),
                rs.getInt(Columns.RATE_VALUE),
                stationsRepository.findById(rs.getLong(Columns.ID)),
                productsRepository.findById(rs.getLong(Columns.ID))
        );
    }
}
