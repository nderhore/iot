package fr.iot.derhore.rest.repository.custom;

import fr.iot.derhore.rest.entity.IotObject;

import java.time.LocalDateTime;
import java.util.List;


public interface TemperatureRepositoryCustom {

    List<IotObject> findTemperatureBetweenTwoDate(LocalDateTime dateDebut, LocalDateTime dateFin);
}
