package fr.iot.derhore.rest.ws.impl;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.enumIot.Action;
import fr.iot.derhore.rest.enumIot.Type;
import fr.iot.derhore.rest.service.AmpouleService;
import fr.iot.derhore.rest.ws.ApiRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_AMPOULE)
public class AmpouleWs {

    @Autowired
    private AmpouleService ampouleService;

    @GetMapping
    public List<IotObject> findAmpoule() {
        return ampouleService.getAllAmpoule();
    }

    @GetMapping(ApiRegistration.REST_FILTERED + "/{action}")
    public List<IotObject> findFilteredAmpoule(
            @PathVariable(value="action") Action action){
        return ampouleService.getFilteredAmpoule(action);
    }

    @GetMapping("/{id}")
    public IotObject findAmpoule(@PathVariable(name = "id") String id) {
        return ampouleService.getAmpoule(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAmpoule(@PathVariable String id) {
        ampouleService.deleteAmpoule(id);
    }

    @PutMapping(value = "/{id}")
    public void updateAmpoule(@PathVariable String id, @RequestBody IotObject iotObject) {
        ampouleService.updateAmpoule(id, iotObject);
    }

    @PostMapping
    public void createAmpoule(@RequestBody IotObject iotObject){
        ampouleService.createAmpoule(iotObject);
    }
}
