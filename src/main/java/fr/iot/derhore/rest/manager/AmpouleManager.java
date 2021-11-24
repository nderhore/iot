package fr.iot.derhore.rest.manager;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.enumIot.Action;

import java.util.List;

public interface AmpouleManager {

    List<IotObject> getAllAmpoule();

    List<IotObject> getFilteredAmpoule(Action action);

    IotObject getAmpoule(String id);

    void deleteAmpoule(String id);

    void updateAmpoule(String id,IotObject iotObject);

    void createAmpoule(IotObject iotObject);

    void updateAmpouleByTopic(String topic, IotObject ampoule);

}
