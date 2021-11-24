package fr.iot.derhore.rest.singleton;

import fr.iot.derhore.rest.entity.IotObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class MessageQueueSingleton {

    private static MessageQueueSingleton INSTANCE;
    private List<IotObject> iotObjects = new ArrayList<>();


    public static MessageQueueSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MessageQueueSingleton();
        }
        return INSTANCE;
    }

    public void addElementInList(IotObject iotObject){
        this.iotObjects.add(iotObject);
    }

    public List<IotObject> getTemperatures() {
        return iotObjects;
    }

    public void setTemperatures(List<IotObject> iotObjects) {
        this.iotObjects = iotObjects;
    }

    public void resetList(){
        this.iotObjects.clear();
    }
}
