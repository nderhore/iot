package fr.iot.derhore.rest.ws;

import fr.iot.derhore.rest.entity.Temperature;
import fr.iot.derhore.rest.service.TemperatureService;
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
    public List<Temperature> findTemperature() {
        return temperatureService.getAllTemperature();
    }

    @GetMapping(ApiRegistration.REST_FILTERED + "/{dateDebut}" + "/{dateFin}")
    public List<Temperature> findFilteredTemperature(
            @PathVariable(value="dateDebut")
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateDebut,
            @PathVariable(value="dateFin")
            @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dateFin){
        return temperatureService.getFilteredTemperature(dateDebut,dateFin);
    }

    @GetMapping("/{id}")
    public Temperature findTemperature(@PathVariable(name = "id") String id) {
        return temperatureService.getTemperature(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTemperature(@PathVariable String id) {
        temperatureService.deleteTemperature(id);
    }

    @PutMapping(value = "/{id}")
    public void updateTempreature(@PathVariable String id, @RequestBody Temperature temperature) {
        temperatureService.updateTemperature(id,temperature);
    }
}
