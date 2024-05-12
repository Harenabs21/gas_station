package hei.school.gas_station.services;

import hei.school.gas_station.models.Stations;
import hei.school.gas_station.repository.StationsRepository;
import hei.school.gas_station.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StationsService {
    private final StationsRepository stationsRepository;


    public List<Stations> findAllStations() {
        return stationsRepository.findAll();
    }

    public Stations findSingleStation(long id) {
        Stations stations = stationsRepository.findById(id);
        if (stations == null) {
            throw new NotFoundException("station not found");
        }
        return stations;
    }

    public Stations createNewStation(Stations toSave) {
        return stationsRepository.save(toSave);
    }

    public Stations updateStation(Stations toUpdate) {
        Stations stations = stationsRepository.findById(toUpdate.getId());
        if (stations == null) {
            throw new NotFoundException("station not found");
        }
        return stationsRepository.update(toUpdate);
    }

    public Stations deleteStation(long id) {
        Stations stations = stationsRepository.findById(id);
        if (stations == null) {
            throw new NotFoundException("station not found");
        }
        return stationsRepository.delete(stations);
    }
}
