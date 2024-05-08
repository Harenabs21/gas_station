package hei.school.gas_station.services;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import hei.school.gas_station.repository.ProductSalesRepository;
import hei.school.gas_station.repository.MovementStockRepository;
import hei.school.gas_station.repository.ProductsRepository;
import hei.school.gas_station.repository.utils.MovementStockType;
import hei.school.gas_station.models.MovementStock;
import hei.school.gas_station.models.ProductSales;
import hei.school.gas_station.models.Products;

@Service
@AllArgsConstructor
public class ProductSalesService {
    private final ProductSalesRepository productSalesRepo;
    private final MovementStockRepository movementStockRepo;
    private final ProductsRepository productRepo;


    public Integer newSalesProduct(ProductSales toSave) {
        Integer saved = productSalesRepo.save(toSave);
        MovementStock movement = new MovementStock(0,null,MovementStockType.EXIT,toSave.getIdStation(),toSave.getIdProduct());
        movementStockRepo.save(movement);
        Products product = productRepo.findById(toSave.getIdProduct());
        double sumOfCapacity = product.getProductCapacity() - toSave.getSalesCapacity();
        productRepo.updateProductCapacity(sumOfCapacity,product.getId());
        return saved;
    }

}
