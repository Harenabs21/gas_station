package hei.school.gas_station.controller;

import hei.school.gas_station.models.Stock;
import hei.school.gas_station.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.findAllStocks();
    }
    @GetMapping("/station/{id}")
    public List<Stock> getAllStocksOfStation(@PathVariable(name = "id") long stationId) {
        return stockService.findStocksByIdStation(stationId);
    }
    @GetMapping("/{id}")
    public Stock getSingleStock(@PathVariable long id){
        return stockService.findStockById(id);
    }
    @GetMapping("/first-stock")
    public Stock getFirstStockOfProduct(@RequestParam long stationId, @RequestParam long productId) {
        return stockService.findFirstStock(stationId,productId);
    }

    @GetMapping("/last-stock")
    public Stock getLastStockOfProduct(@RequestParam long stationId, @RequestParam long productId) {
        return stockService.findLatestStock(stationId,productId);
    }
    @PostMapping
    public Stock postNewStock(@RequestBody Stock toSave) {
        return stockService.createNewStock(toSave);
    }
}
