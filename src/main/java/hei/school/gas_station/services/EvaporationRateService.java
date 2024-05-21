package hei.school.gas_station.services;

import hei.school.gas_station.models.EvaporationRate;
import hei.school.gas_station.repository.EvaporationRateRepository;
import hei.school.gas_station.utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EvaporationRateService {
    private final EvaporationRateRepository evaporationRateRepository;

    public EvaporationRate findSingleEvaporationRate(long id){
        EvaporationRate evaporationRate = evaporationRateRepository.findById(id);
        if(evaporationRate == null){
            throw new NotFoundException("evaporation rate may be inexistent");
        }
        return evaporationRate;
    }
    public List<EvaporationRate> findAllEvaporationRate() {
        return evaporationRateRepository.findAll();
    }
    public List<EvaporationRate> findAllEvaporationRateOfStation(long stationId){
        return evaporationRateRepository.findByIdStation(stationId);
    }
    public EvaporationRate findEvaporationRateOfStationAndProduct(long stationId, long productId){
        return evaporationRateRepository.findByIdStationAndIdProduct(stationId, productId);
    }
    public EvaporationRate createNewEvaporationRate(EvaporationRate toSave){
        return evaporationRateRepository.save(toSave);
    }
}

