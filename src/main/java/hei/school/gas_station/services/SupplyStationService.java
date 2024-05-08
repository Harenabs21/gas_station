package hei.school.gas_station.services;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import hei.school.gas_station.repository.SupplyStationRepository;
import hei.school.gas_station.repository.MovementStockRepository;
import hei.school.gas_station.repository.ProductsRepository;
import hei.school.gas_station.repository.utils.MovementStockType;
import hei.school.gas_station.models.MovementStock;
import hei.school.gas_station.models.SupplyStation;
import hei.school.gas_station.models.Products;

@Service
@AllArgsConstructor
public class SupplyStationService {
    private final SupplyStationRepository supplyRepo;
    private final MovementStockRepository movementRepo;
    private final ProductsRepository productRepo;

    public Integer createNewSupply(SupplyStation toSave) {
        supplyRepo.save(toSave);
        MovementStock movementEntry = new MovementStock(0, null, MovementStockType.ENTRY, toSave.getIdStation(), toSave.getIdProduct());
        movementRepo.save(movementEntry);
        Products product = productRepo.findById(toSave.getIdProduct());
        double sumOfCapacity = toSave.getSupplyQuantity() + product.getProductCapacity();
        productRepo.updateProductCapacity(sumOfCapacity,product.getId());
        return 0;
    }

}
