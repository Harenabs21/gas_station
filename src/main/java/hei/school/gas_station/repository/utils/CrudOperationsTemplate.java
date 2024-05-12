package hei.school.gas_station.repository.utils;
import java.util.List;
public interface CrudOperationsTemplate<T> {
    T findById(long id);

    List<T> findAll();

    T save(T toSave);

    T update(T toUpdate);

    T delete(T toDelete);
}
