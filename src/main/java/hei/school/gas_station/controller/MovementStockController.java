package hei.school.gas_station.controller;

import hei.school.gas_station.models.MovementStock;
import hei.school.gas_station.services.MovementStockService;
import hei.school.gas_station.utils.exceptions.NotEnoughStockException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movement-of-stock")
public class MovementStockController {
    private final MovementStockService movementStockService;

    @GetMapping
    public List<MovementStock> getALlMovementStock() {
        return movementStockService.findAllMovementStocks();
    }
    @GetMapping("/station/{id}")
    public List<MovementStock> getAllMovementStockOfStation(@PathVariable(name = "id") long stationId) {
        return movementStockService.findMovementStocksByIdStation(stationId);
    }

    @GetMapping("/{id}")
    public MovementStock getSingleMovementStockByHisId(@PathVariable long id) {
        return movementStockService.findSingleMovementStockByHisId(id);
    }

    @PostMapping
    public MovementStock postNewMovementStock(@RequestBody MovementStock toSave) throws NotEnoughStockException {
        return movementStockService.createNewMovementStock(toSave);
    }
}
