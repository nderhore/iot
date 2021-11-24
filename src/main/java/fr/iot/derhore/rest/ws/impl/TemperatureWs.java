package fr.iot.derhore.rest.ws.impl;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.service.TemperatureService;
import fr.iot.derhore.rest.ws.ApiRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(ApiRegistration.REST_PREFIX + ApiRegistration.REST_TEMPERATURE)
public class TemperatureWs {

    @Autowired
    private TemperatureService temperatureService;

    @GetMapping
    public List<IotObject> findTemperature() {
        return temperatureService.getAllTemperature();
    }

    @GetMapping(ApiRegistration.REST_FILTERED + "/{dateDebut}" + "/{dateFin}")
    public List<IotObject> findFilteredTemperature(
            @PathVariable(value="dateDebut")
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateDebut,
            @PathVariable(value="dateFin")
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateFin){
        return temperatureService.getFilteredTemperature(dateDebut,dateFin);
    }

    @GetMapping("/{id}")
    public IotObject findTemperature(@PathVariable(name = "id") String id) {
        return temperatureService.getTemperature(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTemperature(@PathVariable String id) {
        temperatureService.deleteTemperature(id);
    }

    @PutMapping(value = "/{id}")
    public void updateTemperature(@PathVariable String id, @RequestBody IotObject iotObject) {
        temperatureService.updateTemperature(id, iotObject);
    }

    @PostMapping
    public void createTemperature(@RequestBody IotObject iotObject){
        temperatureService.createTemperature(iotObject);
    }
}
