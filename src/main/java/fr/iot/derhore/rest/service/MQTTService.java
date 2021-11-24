package fr.iot.derhore.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.enumIot.Type;
import fr.iot.derhore.rest.manager.AmpouleManager;
import fr.iot.derhore.rest.manager.TemperatureManager;
import fr.iot.derhore.rest.singleton.MessageQueueSingleton;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQTTService implements MqttCallback {

    private MqttClient client = null;
    private String mqttUserName = "iot-derhore", mqttPassword = "2y10rMY77U3FWqY19UbfMo43";
    private String mqttIpAddress = "iot-derhore.cloud.shiftr.io";
    private boolean mqttHaveCredential = true;
    private String mqttPort = "1883";
    private String mqttTopic = "appart/rdc/cuisine/#";
    Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TemperatureManager temperatureManager;

    @Autowired
    private MessageQueueSingleton messageQueueSingleton;

    @Autowired
    private AmpouleManager ampouleManager;

    @Override
    public void connectionLost(Throwable arg0) {
        LOG.info("connectionLost :" + arg0.getMessage()+" :"+arg0.toString());
        LOG.info("retry ....");
        this.startMqtt();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken arg0) {
        try {
            LOG.info("deliveryCompleted " );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageArrived(String s, MqttMessage msg) throws Exception {
        LOG.info("message Arrived !");
        try {
            messageQueueSingleton = MessageQueueSingleton.getInstance();
            IotObject iotObject = objectMapper.readValue(msg.toString(), IotObject.class);
            iotObject.setTopic(s);
            if(iotObject.getType().equals(Type.TEMPERATURE)) {
                messageQueueSingleton.addElementInList(iotObject);
                if (messageQueueSingleton.getTemperatures().size() >= 10) {
                    temperatureManager.createTemperatureWithAvg(messageQueueSingleton.getTemperatures());
                    messageQueueSingleton.resetList();
                }
            } else{
                ampouleManager.updateAmpouleByTopic(s,iotObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(String topicSuffix, String content) {
        MqttMessage message = new MqttMessage();
        message.setPayload(content.getBytes());
        message.setQos(2);
        try {
            String topic = mqttTopic + topicSuffix;
            if (client.isConnected()) {
                LOG.info("Connection Status :" + client.isConnected());
            }
            client.publish(topic, message);
        } catch (MqttPersistenceException e) {
            e.printStackTrace();

        } catch (MqttException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void startMqtt() {

        MemoryPersistence persistence = new MemoryPersistence();
        try {
            client = new MqttClient("tcp://" + mqttIpAddress + ":" + mqttPort, "SpringBootApp",
                    persistence);
        } catch (MqttException e1) {

            System.out.println("error");
            e1.printStackTrace();
        }

        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(true);
        connectOptions.setMaxInflight(3000);
        connectOptions.setAutomaticReconnect(true);
        if (mqttHaveCredential) {
            connectOptions.setUserName(mqttUserName);
            connectOptions.setPassword(mqttPassword.toCharArray());
        }

        client.setCallback(this);

        try {
            IMqttToken mqttConnectionToken = client.connectWithResult(connectOptions);
            client.subscribe("#");
            LOG.info(" Connection Status :" + mqttConnectionToken.isComplete());

        } catch (MqttException e) {

            e.printStackTrace();
        }
    }

}
