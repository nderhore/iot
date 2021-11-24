package fr.iot.derhore.rest.manager.impl;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.enumIot.Type;
import fr.iot.derhore.rest.manager.TemperatureManager;
import fr.iot.derhore.rest.repository.IotRepository;
import fr.iot.derhore.rest.repository.custom.IotRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TemperatureManagerImpl implements TemperatureManager {

    @Autowired
    private IotRepository iotRepository;

    @Autowired
    private IotRepositoryCustom iotRepositoryCustom;

    @Override
    public List<IotObject> getAllTemperature() {
        return iotRepository.findAllByType(Type.TEMPERATURE);
    }

    @Override
    public List<IotObject> getFilteredTemperature(LocalDateTime dateDebut, LocalDateTime dateFin) {
        return iotRepositoryCustom.findTemperatureBetweenTwoDate(dateDebut,dateFin);
    }

    @Override
    public IotObject getTemperature(String id) {

        Optional<IotObject> temperatures = iotRepository.findById(new ObjectId(id));
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
            iotRepository.delete(iotObject);
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
            iotRepository.save(oldIotObject);
        }
    }

    @Override
    public void createTemperature(IotObject iotObject) {
        iotRepository.save(iotObject);
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
