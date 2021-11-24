package fr.iot.derhore.rest.dao;

public interface MQTTDao {

    public void startMqtt();

    public void publish(String topicSuffix, String content);
}
