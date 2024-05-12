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

import hei.school.gas_station.models.MovementStock;
import hei.school.gas_station.repository.utils.MovementStockType;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MovementStockRepository implements CrudOperationsTemplate<MovementStock> {
    private final QueryTemplate qt;
    private final StationsRepository stationRepo;
    private final ProductsRepository productRepo;

    @Override
    public MovementStock findById(long id) {
        return qt.executeSingleQuery("select * from movement_stock where id = ?",
                ps -> ps.setLong(1, id), this::getResult);
    }

    public List<MovementStock> findByIdStation(long stationId) {
        return qt.executeQuery("select * from movement_stock where id_station = ?",
                ps -> ps.setLong(1, stationId), this::getResult);
    }

    @Override
    public List<MovementStock> findAll() {
        return qt.executeQuery("select * from movement_stock", this::getResult);
    }

    @Override
    public MovementStock save(MovementStock toSave) {
        return qt.executeUpdate("insert into movement_stock (movement_datetime,movement_type,movement_quantity,movement_amount,id_station,id_product) values (?,?,?,?,?,?)",
                ps -> {
                    ps.setTimestamp(1, Timestamp.from(toSave.getMovementDatetime()));
                    ps.setString(2, String.valueOf(toSave.getMovementType()));
                    ps.setDouble(3, toSave.getMovementQuantity());
                    ps.setDouble(4, toSave.getMovementAmount());
                    ps.setLong(5, toSave.getStation().getId());
                    ps.setLong(6, toSave.getProduct().getId());
                }) != 0 ? toSave : null;
    }

    @Override
    public MovementStock update(MovementStock toUpdate) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public MovementStock delete(MovementStock toDelete) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private MovementStock getResult(ResultSet rs) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(Columns.MOVEMENT_DATETIME);
        Instant instant = timestamp != null ? timestamp.toInstant() : null;
        MovementStockType movementType = MovementStockType.valueOf(rs.getString(Columns.MOVEMENT_TYPE));
        return new MovementStock(
                rs.getLong(Columns.ID),
                instant,
                movementType,
                rs.getDouble(Columns.MOVEMENT_QUANTITY),
                rs.getDouble(Columns.MOVEMENT_AMOUNT),
                stationRepo.findById(rs.getLong(Columns.ID_STATION)),
                productRepo.findById(rs.getLong(Columns.ID_PRODUCT))
        );
    }
}
