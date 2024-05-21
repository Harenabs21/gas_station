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

import hei.school.gas_station.models.Stock;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class StockRepository implements CrudOperationsTemplate<Stock> {
    private final QueryTemplate qt;
    private final StationsRepository stationRepo;
    private final ProductsRepository productRepo;

    @Override
    public Stock findById(long id) {
        return qt.executeSingleQuery("select * from stock where id = ?",
                ps -> ps.setLong(1, id), (this::getResultSet)
        );
    }

    public Stock findByIdStationAndIdProduct(long stationId, long productId, String sortView) {
        return qt.executeSingleQuery("select * from stock where id_station = ? and id_product = ? order by stock_datetime " + sortView + " LIMIT 1",
                ps -> {
                    ps.setLong(1, stationId);
                    ps.setLong(2, productId);
                }, this::getResultSet);
    }

    public List<Stock> findByIdStation(long stationId) {
        return qt.executeQuery("select * from stock where id_station = ?", ps -> ps.setLong(1, stationId), this::getResultSet);
    }

    @Override
    public List<Stock> findAll() {
        return qt.executeQuery("select * from stock", this::getResultSet);
    }

    @Override
    public Stock save(Stock toSave) {
        return qt.executeUpdate("insert into stock (stock_datetime,product_capacity,evaporation_rate,id_station,id_product) values (?,?,?,?,?)",
                ps -> {
                    ps.setTimestamp(1, Timestamp.from(toSave.getStockDatetime()));
                    ps.setDouble(2, toSave.getQuantity());
                    ps.setLong(3, toSave.getStation().getId());
                    ps.setLong(4, toSave.getProduct().getId());
                }) != 0 ? toSave : null;
    }

    @Override
    public Stock update(Stock toUpdate) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Stock delete(Stock toDelete) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Stock getResultSet(ResultSet rs) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(Columns.STOCK_DATETIME);
        Instant instant = timestamp != null ? timestamp.toInstant() : null;
        return new Stock(
                rs.getLong(Columns.ID),
                rs.getDouble(Columns.PRODUCT_CAPACITY),
                instant,
                stationRepo.findById(rs.getLong(Columns.ID_STATION)),
                productRepo.findById(rs.getLong(Columns.ID_PRODUCT))
        );
    }


}
