package fr.iot.derhore.rest.manager.impl;

import fr.iot.derhore.rest.entity.Temperature;
import fr.iot.derhore.rest.manager.TemperatureManager;
import fr.iot.derhore.rest.repository.TemperatureRepository;
import fr.iot.derhore.rest.repository.custom.TemperatureRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TemperatureManagerImpl implements TemperatureManager {


    private TemperatureRepository temperatureRepository;


    private TemperatureRepositoryCustom temperatureRepositoryCustom;

    @Override
    public List<Temperature> getAllTemperature() {
        return temperatureRepository.findAll();
    }

    @Override
    public List<Temperature> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin) {
        return temperatureRepositoryCustom.findTemperatureBetweenTwoDate(dateDebut,dateFin);
    }

    @Override
    public Temperature getTemperature(String id) {

       Optional<Temperature> temperatures = temperatureRepository.findById(new ObjectId(id));
       Temperature temperature = null;

       if(temperatures.isPresent()){
           temperature = temperatures.get();
       }

       return temperature;
    }

    @Override
    public void deleteTemperature(String id) {
        Temperature temperature = this.getTemperature(id);

        if(temperature != null) {
            temperatureRepository.delete(temperature);
        }
    }

    @Override
    public void updateTemperature(String id, Temperature temperature) {
        Temperature oldTemperature = this.getTemperature(id);

        if(oldTemperature != null){
            oldTemperature.setDate(temperature.getDate());
            oldTemperature.setValue(temperature.getValue());
            temperatureRepository.save(oldTemperature);
        }
    }
}
