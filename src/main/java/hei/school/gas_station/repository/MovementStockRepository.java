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
public class MovementStockRepository implements CrudOperationsTemplate<MovementStock>{
    private final QueryTemplate qt;

    @Override
    public MovementStock findById(long id) {
        return qt.executeSingleQuery("select * from movement_stock where id = ?",
        ps -> ps.setLong(1,id), this::getResult);
    }

    @Override
    public List<MovementStock> findAll() {
        return qt.executeQuery("select * from movement_stock", this::getResult);
    }

    @Override
    public Integer save(MovementStock toSave) {
        return qt.executeUpdate("insert into movement_stock (movement_type,id_station,id_product) values (?,?,?)",
        ps -> {
            ps.setString(1,String.valueOf(toSave.getMovementType()));
            ps.setLong(2,toSave.getIdStation());
            ps.setLong(3,toSave.getIdProduct());
        });
    }

    @Override
    public Integer update(MovementStock toUpdate) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public MovementStock delete(MovementStock toDelete) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private MovementStock getResult(ResultSet rs) throws SQLException{
        Timestamp timestamp = rs.getTimestamp(Columns.MOVEMENT_DATETIME);
        Instant instant = timestamp != null ? timestamp.toInstant() : null;
        MovementStockType movementType = MovementStockType.valueOf( rs.getString(Columns.MOVEMENT_TYPE));
        return new MovementStock(
            rs.getLong(Columns.ID),
            instant,
            movementType,
            rs.getLong(Columns.ID_STATION),
            rs.getLong(Columns.ID_PRODUCT)
        );
    }
}
