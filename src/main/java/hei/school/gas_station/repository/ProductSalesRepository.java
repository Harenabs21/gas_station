package hei.school.gas_station.repository;

import hei.school.gas_station.repository.utils.CrudOperationsTemplate;
import hei.school.gas_station.repository.utils.Columns;
import hei.school.gas_station.utils.QueryTemplate;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import hei.school.gas_station.models.ProductSales;
import org.springframework.stereotype.Repository;
@Repository
@AllArgsConstructor
public class ProductSalesRepository implements CrudOperationsTemplate<ProductSales> {
    private final QueryTemplate qt;

    @Override
    public ProductSales findById(long id) {
        return qt.executeSingleQuery("select * from product_sales where id = ?",
        ps -> ps.setLong(1,id),this::getResult);
    }

    @Override
    public List<ProductSales> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Integer save(ProductSales toSave) {
        return qt.executeUpdate("insert into product_sales (sales_price, sales_capacity, id_station,id_product) values (?,?,?,?)",
        ps -> {
            ps.setDouble(1,toSave.getSalesPrice());
            ps.setDouble(2,toSave.getSalesCapacity());
            ps.setLong(3,toSave.getIdStation());
            ps.setLong(4,toSave.getIdProduct());
        });
    }

    @Override
    public Integer update(ProductSales toUpdate) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public ProductSales delete(ProductSales toDelete) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private ProductSales getResult(ResultSet rs) throws SQLException {
        return new ProductSales(
            rs.getLong(Columns.ID),
            rs.getDouble(Columns.SALES_PRICE),
            rs.getDouble(Columns.SALES_CAPACITY),
            rs.getLong(Columns.ID_STATION),
            rs.getLong(Columns.ID_PRODUCT)
        );
    }
    
}
