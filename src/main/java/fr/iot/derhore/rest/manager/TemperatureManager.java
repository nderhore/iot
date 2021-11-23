package fr.iot.derhore.rest.manager;


import fr.iot.derhore.rest.entity.Temperature;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureManager {

    List<Temperature> getAllTemperature();

    List<Temperature> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin);

    Temperature getTemperature(String id);

    void deleteTemperature(String id);

    void updateTemperature(String id, Temperature temperature);
}
