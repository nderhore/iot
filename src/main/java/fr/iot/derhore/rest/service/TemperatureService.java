package fr.iot.derhore.rest.service;

import fr.iot.derhore.rest.entity.IotObject;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureService {

    List<IotObject> getAllTemperature();

    List<IotObject> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin);

    IotObject getTemperature(String id);

    void deleteTemperature(String id);

    void updateTemperature(String id, IotObject iotObject);

    void createTemperature(IotObject iotObject);
}
