package fr.iot.derhore.rest.ws.impl;

import fr.iot.derhore.rest.pojo.Message;
import fr.iot.derhore.rest.service.MQTTService;
import fr.iot.derhore.rest.ws.ApiRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRegistration.REST_MQTT)
public class MqttController {

    @Autowired
    private MQTTService mqttService;

    @PutMapping("/start")
    public String startMqtt() {
        return null;
    }

    @PostMapping("/publish")
    public String publish(@RequestBody Message messageDTO) {
        mqttService.publish(messageDTO.getTopic(), messageDTO.getMessage());
        return "Success";
    }
}
