package hei.school.gas_station.repository.utils;
import java.util.List;
public interface CrudOperationsTemplate<T> {
    T findById(long id);

    List<T> findAll();

    Integer save(T toSave);

    Integer update(T toUpdate);

    T delete(T toDelete);
}
