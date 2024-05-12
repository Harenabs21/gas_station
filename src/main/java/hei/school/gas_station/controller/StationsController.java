package hei.school.gas_station.controller;

import hei.school.gas_station.models.Stations;
import hei.school.gas_station.services.StationsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
@AllArgsConstructor
public class StationsController {
    private final StationsService stationsService;

    @GetMapping
    public List<Stations> getAllStations() {
        return stationsService.findAllStations();
    }
    @GetMapping("/{id}")
    public Stations getSingleStationByHisId(@PathVariable long id) {
        return stationsService.findSingleStation(id);
    }
    @PostMapping
    public Stations postNewStation(@RequestBody Stations stations) {
        return stationsService.createNewStation(stations);
    }
    @PutMapping
    public Stations putStation(@RequestBody Stations stations) {
        return stationsService.updateStation(stations);
    }
    @DeleteMapping("/delete/{id}")
    public Stations deleteSingleStation(@PathVariable long id) {
        return stationsService.deleteStation(id);
    }


}
