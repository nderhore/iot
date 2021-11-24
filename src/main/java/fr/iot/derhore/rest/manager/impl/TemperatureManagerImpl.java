package fr.iot.derhore.rest.manager.impl;

import fr.iot.derhore.rest.entity.IotObject;
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

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Autowired
    private TemperatureRepositoryCustom temperatureRepositoryCustom;

    @Override
    public List<IotObject> getAllTemperature() {
        return temperatureRepository.findAll();
    }

    @Override
    public List<IotObject> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin) {
        return temperatureRepositoryCustom.findTemperatureBetweenTwoDate(dateDebut,dateFin);
    }

    @Override
    public IotObject getTemperature(String id) {

        Optional<IotObject> temperatures = temperatureRepository.findById(new ObjectId(id));
        IotObject iotObject = null;

        if(temperatures.isPresent()){
            iotObject = temperatures.get();
        }

        return iotObject;
    }

    @Override
    public void deleteTemperature(String id) {
        IotObject iotObject = this.getTemperature(id);

        if(iotObject != null) {
            temperatureRepository.delete(iotObject);
        }
    }

    @Override
    public void updateTemperature(String id, IotObject iotObject) {
        IotObject oldIotObject = this.getTemperature(id);

        if(oldIotObject != null){
            oldIotObject.setTime(iotObject.getTime());
            oldIotObject.setValue(iotObject.getValue());
            oldIotObject.setDuree(iotObject.getDuree());
            oldIotObject.setTopic(iotObject.getTopic());
            temperatureRepository.save(oldIotObject);
        }
    }

    @Override
    public void createTemperature(IotObject iotObject) {
        temperatureRepository.save(iotObject);
    }

    @Override
    public void createTemperatureWithAvg(List<IotObject> iotObjects) {
        IotObject iotObject = new IotObject();

        if (!iotObjects.isEmpty()) {
            for (IotObject messageIotObject : iotObjects) {
                //Premiere valeur
                if (iotObject.getTime() == null) {
                    iotObject.setTime(messageIotObject.getTime());
                    iotObject.setValue(messageIotObject.getValue());
                    iotObject.setTopic(messageIotObject.getTopic());
                    iotObject.setDuree(messageIotObject.getDuree());
                } else {
                    //Autre valeur
                    iotObject.setValue((iotObject.getValue() + messageIotObject.getValue())/2);
                    iotObject.setDuree((iotObject.getDuree() + messageIotObject.getDuree())/2);
                }
            }
            this.createTemperature(iotObject);
        }
    }
}
