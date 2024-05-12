package hei.school.gas_station.services;

import hei.school.gas_station.models.MovementStock;
import hei.school.gas_station.models.Stock;
import hei.school.gas_station.repository.MovementStockRepository;
import hei.school.gas_station.repository.utils.MovementStockType;
import hei.school.gas_station.utils.exceptions.ExcessiveQuantityException;
import hei.school.gas_station.utils.exceptions.NotEnoughStockException;
import hei.school.gas_station.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class MovementStockService {
    private final StockService stockService;
    private final StationsService stationsService;
    private final ProductsService productsService;
    private final MovementStockRepository movementStockRepository;

    public List<MovementStock> findAllMovementStocks() {
        return movementStockRepository.findAll();
    }

    public List<MovementStock> findMovementStocksByIdStation(long stationId) {
        List<MovementStock> movementStocks = movementStockRepository.findByIdStation(stationId);
        if (movementStocks == null) {
            throw new NotFoundException("Station associated with this movement doesn't exist");
        }
        return movementStocks;
    }

    public MovementStock findSingleMovementStockByHisId(long id) {
        MovementStock movementStock = movementStockRepository.findById(id);
        if (movementStock == null) {
            throw new NotFoundException("Movement doesn't exist");
        }
        return movementStock;
    }

    public MovementStock createNewMovementStock(MovementStock toSave) throws NotEnoughStockException {
        Stock latestStock = stockService.findLatestStock(toSave.getStation().getId(), toSave.getProduct().getId());
        double productsPrice = productsService.findSingleProductByHisId(toSave.getProduct().getId()).getUnitPrice();
        double maxQuantity = 200.0;
        if (toSave.getMovementType() == MovementStockType.ENTRY) {
            latestStock.setQuantity(latestStock.getQuantity() + toSave.getMovementQuantity());
            stockService.createNewStock(latestStock);
            toSave.setMovementAmount(0.0);
        } else if (toSave.getMovementType() == MovementStockType.EXIT) {
            if (latestStock.getQuantity() > toSave.getMovementQuantity()) {
                isAmountAndQuantityNull(toSave, productsPrice);
                latestStock.setQuantity(latestStock.getQuantity() - toSave.getMovementQuantity());
            } else if (toSave.getMovementQuantity() > maxQuantity) {
                throw new ExcessiveQuantityException("Only 200L is authorized");
            } else {
                throw new NotEnoughStockException("Stock in not enough anymore, please do a supply");
            }
            stockService.createNewStock(latestStock);
        }
        toSave.setMovementDatetime(Instant.now());
        return movementStockRepository.save(toSave);
    }

    private void isAmountAndQuantityNull(MovementStock obj, double price) {
        if (obj.getMovementAmount() == 0.0) {
            obj.setMovementAmount(obj.getMovementQuantity() * price);
        } else if (obj.getMovementQuantity() == 0.0) {
            double amount = obj.getMovementAmount() / price;
            obj.setMovementQuantity(Math.round(amount * 100) / 100.0);
        }
    }


}
