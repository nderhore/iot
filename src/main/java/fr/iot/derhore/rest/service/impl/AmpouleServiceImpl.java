package fr.iot.derhore.rest.service.impl;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.enumIot.Action;
import fr.iot.derhore.rest.manager.AmpouleManager;
import fr.iot.derhore.rest.service.AmpouleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmpouleServiceImpl implements AmpouleService {

    @Autowired
    private AmpouleManager ampouleManager;


    @Override
    public List<IotObject> getAllAmpoule() {
        return ampouleManager.getAllAmpoule();
    }

    @Override
    public List<IotObject> getFilteredAmpoule(Action action) {
        return ampouleManager.getFilteredAmpoule(action);
    }

    @Override
    public IotObject getAmpoule(String id) {
        return ampouleManager.getAmpoule(id);
    }

    @Override
    public void deleteAmpoule(String id) {
        ampouleManager.deleteAmpoule(id);
    }

    @Override
    public void updateAmpoule(String id, IotObject iotObject) {
        ampouleManager.updateAmpoule(id,iotObject);
    }

    @Override
    public void createAmpoule(IotObject iotObject) {
        ampouleManager.createAmpoule(iotObject);
    }
}
