package hei.school.gas_station.repository;

import hei.school.gas_station.repository.utils.CrudOperationsTemplate;
import hei.school.gas_station.repository.utils.Columns;
import hei.school.gas_station.utils.QueryTemplate;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import hei.school.gas_station.models.Products;
import org.springframework.stereotype.Repository;
@Repository
@AllArgsConstructor
public class ProductsRepository implements CrudOperationsTemplate<Products>{
    private final QueryTemplate qt;

    @Override
    public Products findById(long id) {
        return qt.executeSingleQuery("select * from products where id = ?", 
        ps -> ps.setLong(1,id), 
        this::getResult);
    }

    @Override
    public List<Products> findAll() {
        return qt.executeQuery("select * from products",this::getResult);
    }

    @Override
    public Integer save(Products toSave) {
        return qt.executeUpdate("insert into products (product_name,product_capacity,unit_price,evaporation_rate) values (?,?,?,?)",
        ps -> { ps.setString(1,toSave.getProductName());
                ps.setDouble(2,toSave.getProductCapacity());
                ps.setDouble(3,toSave.getUnitPrice());
                ps.setInt(4,toSave.getEvaporationRate());
        });
    }

    @Override
    public Integer update(Products toUpdate) {
        return qt.executeUpdate("update products set product_capacity = ?,unit_price = ?,evaporation_rate =? where id = ?",
        ps -> {
                ps.setDouble(1,toUpdate.getProductCapacity());
                ps.setDouble(2,toUpdate.getUnitPrice());
                ps.setInt(3,toUpdate.getEvaporationRate());
                ps.setLong(4,toUpdate.getId());
        });
    }

    public Integer updateProductCapacity(double capacity, long idProduct){
        return qt.executeUpdate("update products set product_capacity = ? where id = ?",
        ps -> {
            ps.setDouble(1,capacity);
            ps.setLong(2,idProduct);
        });
    }

    @Override
    public Products delete(Products toDelete) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Products getResult(ResultSet rs) throws SQLException{
        return new Products(
            rs.getLong(Columns.ID),
            rs.getString(Columns.PRODUCT_NAME),
            rs.getDouble(Columns.PRODUCT_CAPACITY),
            rs.getDouble(Columns.UNIT_PRICE),
            rs.getInt(Columns.EVAPORATION_RATE)
        );
    }
}
