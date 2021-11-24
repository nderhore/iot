package fr.iot.derhore.rest.repository.custom;

import fr.iot.derhore.rest.entity.IotObject;

import java.time.LocalDateTime;
import java.util.List;


public interface IotRepositoryCustom {

    List<IotObject> findTemperatureBetweenTwoDate(LocalDateTime dateDebut, LocalDateTime dateFin);

    void updateAmpouleByTopic(String topic, IotObject iotObject);
}
