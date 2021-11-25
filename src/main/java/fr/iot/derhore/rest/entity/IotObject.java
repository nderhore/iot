package fr.iot.derhore.rest.entity;

import fr.iot.derhore.rest.enumIot.Action;
import fr.iot.derhore.rest.enumIot.Type;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "iot")
public class IotObject {

    @Id
    private ObjectId id;

    private LocalDateTime time;

    private String topic;

    private Double duree;

    private Double value;

    private Type type;

    private Double maxValue;

    private Double minValue;

    private Action state;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Action getState() {
        return state;
    }

    public void setState(Action state) {
        this.state = state;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }
}
