package hei.school.gas_station.controller;

import hei.school.gas_station.models.EvaporationRate;
import hei.school.gas_station.services.EvaporationRateService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaporation-rate")
@AllArgsConstructor
public class EvaporationRateController {
    private final EvaporationRateService evaporationRateService;

    @GetMapping("/{id}")
    public EvaporationRate getById(@PathVariable long id){
        return evaporationRateService.findSingleEvaporationRate(id);
    }

    @GetMapping
    public List<EvaporationRate> getAllEvaporationRate() {
        return evaporationRateService.findAllEvaporationRate();
    }

    @GetMapping("/station")
    public List<EvaporationRate> getAllEvaporationRateOfStation(@RequestParam long stationId){
        return evaporationRateService.findAllEvaporationRateOfStation(stationId);
    }
    @GetMapping("/station-and-product")
    public EvaporationRate getEvaporationRateOfStationAndHisProducts(@RequestParam long stationId, @RequestParam long productId) {
        return evaporationRateService.findEvaporationRateOfStationAndProduct(stationId, productId);
    }
    @PostMapping
    public EvaporationRate postNewEvaporationRate(@RequestBody EvaporationRate toSave) {
        return evaporationRateService.createNewEvaporationRate(toSave);
    }
}
