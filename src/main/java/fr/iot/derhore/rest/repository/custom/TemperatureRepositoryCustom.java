package fr.iot.derhore.rest.repository.custom;

import fr.iot.derhore.rest.entity.Temperature;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;


public interface TemperatureRepositoryCustom {

    List<Temperature> findTemperatureBetweenTwoDate(LocalDateTime dateDebut, LocalDateTime dateFin);
}
