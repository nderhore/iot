package fr.iot.derhore.rest.singleton;

import fr.iot.derhore.rest.entity.Temperature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class MessageQueueSingleton {

    private static MessageQueueSingleton INSTANCE;
    private List<Temperature> temperatures = new ArrayList<>();


    public static MessageQueueSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MessageQueueSingleton();
        }
        return INSTANCE;
    }

    public void addElementInList(Temperature temperature){
        this.temperatures.add(temperature);
    }

    public List<Temperature> getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(List<Temperature> temperatures) {
        this.temperatures = temperatures;
    }

    public void resetList(){
        this.temperatures.clear();
    }
}
