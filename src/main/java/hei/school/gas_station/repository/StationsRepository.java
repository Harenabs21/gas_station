package hei.school.gas_station.repository;

import hei.school.gas_station.repository.utils.CrudOperationsTemplate;
import hei.school.gas_station.repository.utils.Columns;
import hei.school.gas_station.utils.QueryTemplate;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import hei.school.gas_station.models.Stations;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class StationsRepository implements CrudOperationsTemplate<Stations> {
    private final QueryTemplate qt;

    @Override
    public Stations findById(long id) {
        return qt.executeSingleQuery("select * from stations where id = ?",
                ps -> ps.setLong(1, id),
                this::getResult
        );

    }

    @Override
    public List<Stations> findAll() {
        return qt.executeQuery("select * from stations", this::getResult);
    }

    @Override
    public Stations save(Stations toSave) {
        return qt.executeUpdate("insert into stations (name,place) values (?,?)",
                ps -> {
                    ps.setString(1, toSave.getName());
                    ps.setString(2, toSave.getPlace());
                }) != 0 ? toSave : null;
    }

    @Override
    public Stations update(Stations toUpdate) {
        return qt.executeUpdate("update stations set name = ?,place = ? where id = ?",
                ps -> {
                    ps.setString(1, toUpdate.getName());
                    ps.setString(2, toUpdate.getPlace());
                    ps.setLong(3, toUpdate.getId());
                }) != 0 ? toUpdate : null;
    }

    @Override
    public Stations delete(Stations toDelete) {
        return qt.executeUpdate("delete from stations where id = ?",
                ps -> ps.setLong(1, toDelete.getId())
        ) != 0 ? toDelete : null;
    }

    private Stations getResult(ResultSet rs) throws SQLException {
        return new Stations(rs.getLong(Columns.ID),
                rs.getString(Columns.NAME),
                rs.getString(Columns.PLACE)
        );
    }


}
