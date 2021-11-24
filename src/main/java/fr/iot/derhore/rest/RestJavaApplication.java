package fr.iot.derhore.rest;

import fr.iot.derhore.rest.service.MQTTService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestJavaApplication implements CommandLineRunner {

    @Autowired
    private MQTTService mqttService;

    public static void main(String[] args) {
        SpringApplication.run(RestJavaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mqttService.startMqtt();
    }
}
