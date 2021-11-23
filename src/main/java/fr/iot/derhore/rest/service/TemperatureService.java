package fr.iot.derhore.rest.service;

import fr.iot.derhore.rest.entity.Temperature;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureService {

    List<Temperature> getAllTemperature();

    List<Temperature> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin);

    Temperature getTemperature(String id);

    void deleteTemperature(String id);

    void updateTemperature(String id, Temperature temperature);

    void createTemperature(Temperature temperature);
}
