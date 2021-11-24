package fr.iot.derhore.rest.service.impl;

import fr.iot.derhore.rest.entity.Temperature;
import fr.iot.derhore.rest.manager.TemperatureManager;
import fr.iot.derhore.rest.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureManager temperatureManager;

    @Override
    public List<Temperature> getAllTemperature() {
        return temperatureManager.getAllTemperature();
    }

    @Override
    public List<Temperature> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin) {
        return temperatureManager.getFilteredTemperature(dateDebut,dateFin);
    }

    @Override
    public Temperature getTemperature(String id) {
        return temperatureManager.getTemperature(id);
    }

    @Override
    public void deleteTemperature(String id) {
        temperatureManager.deleteTemperature(id);
    }

    @Override
    public void updateTemperature(String id, Temperature temperature) {
        temperatureManager.updateTemperature(id,temperature);
    }

    @Override
    public void createTemperature(Temperature temperature) {
        temperatureManager.createTemperature(temperature);
    }
}
