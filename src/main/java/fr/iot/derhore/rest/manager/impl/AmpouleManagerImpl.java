package fr.iot.derhore.rest.manager.impl;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.enumIot.Action;
import fr.iot.derhore.rest.enumIot.Type;
import fr.iot.derhore.rest.manager.AmpouleManager;
import fr.iot.derhore.rest.repository.IotRepository;
import fr.iot.derhore.rest.repository.custom.IotRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AmpouleManagerImpl implements AmpouleManager {

    @Autowired
    private IotRepository iotRepository;

    @Autowired
    private IotRepositoryCustom iotRepositoryCustom;

    @Override
    public List<IotObject> getAllAmpoule() {
        return iotRepository.findAllByType(Type.AMPOULE);
    }

    @Override
    public List<IotObject> getFilteredAmpoule(Action action) {
        return iotRepository.findAllByTypeAndState(Type.AMPOULE,action);
    }

    @Override
    public IotObject getAmpoule(String id) {
        Optional<IotObject> iotObject = iotRepository.findById(new ObjectId(id));
        IotObject iot = null;
        if(iotObject.isPresent()){
            iot = iotObject.get();
        }
        return iot;
    }

    @Override
    public void deleteAmpoule(String id) {
        iotRepository.delete(this.getAmpoule(id));
    }

    @Override
    public void updateAmpoule(String id, IotObject iotObject) {
        IotObject iotOld = this.getAmpoule(id);
        if(iotOld != null){
            iotOld.setState(iotObject.getState());
            iotRepository.save(iotOld);
        }
    }

    @Override
    public void createAmpoule(IotObject iotObject) {
        iotRepository.save(iotObject);
    }

    @Override
    public void updateAmpouleByTopic(String topic, IotObject ampoule) {
        iotRepositoryCustom.updateAmpouleByTopic(topic, ampoule);
    }
}
