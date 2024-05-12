package hei.school.gas_station.services;

import hei.school.gas_station.models.Stock;
import hei.school.gas_station.repository.StockRepository;
import hei.school.gas_station.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    public Stock findStockById(long id) {
        Stock stock = stockRepository.findById(id);
        if (stock == null) {
            throw new NotFoundException("Stock not found");
        }
        return stock;
    }

    public List<Stock> findStocksByIdStation(long stationId) {
        List<Stock> stockList = stockRepository.findByIdStation(stationId);
        if (stockList == null) {
            throw new NotFoundException("Station doesn't exist");
        }
        return stockList;
    }

    public Stock findLatestStock(long stationId, long productId) {
        return stockRepository.findByIdStationAndIdProduct(stationId, productId, "DESC");
    }

    public Stock findFirstStock(long stationId, long productId) {
        return stockRepository.findByIdStationAndIdProduct(stationId, productId, "ASC");
    }

    public Stock createNewStock(Stock toSave) {
        toSave.setStockDatetime(Instant.now());
        return stockRepository.save(toSave);
    }
}
