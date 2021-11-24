package fr.iot.derhore.rest.service.impl;

import fr.iot.derhore.rest.entity.IotObject;
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
    public List<IotObject> getAllTemperature() {
        return temperatureManager.getAllTemperature();
    }

    @Override
    public List<IotObject> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin) {
        return temperatureManager.getFilteredTemperature(dateDebut,dateFin);
    }

    @Override
    public IotObject getTemperature(String id) {
        return temperatureManager.getTemperature(id);
    }

    @Override
    public void deleteTemperature(String id) {
        temperatureManager.deleteTemperature(id);
    }

    @Override
    public void updateTemperature(String id, IotObject iotObject) {
        temperatureManager.updateTemperature(id, iotObject);
    }

    @Override
    public void createTemperature(IotObject iotObject) {
        temperatureManager.createTemperature(iotObject);
    }
}
