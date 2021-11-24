package fr.iot.derhore.rest.manager;

import fr.iot.derhore.rest.entity.IotObject;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureManager {

    List<IotObject> getAllTemperature();

    List<IotObject> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin);

    IotObject getTemperature(String id);

    void deleteTemperature(String id);

    void updateTemperature(String id, IotObject iotObject);

    void createTemperature(IotObject iotObject);

    void createTemperatureWithAvg(List<IotObject> iotObjects);
}
