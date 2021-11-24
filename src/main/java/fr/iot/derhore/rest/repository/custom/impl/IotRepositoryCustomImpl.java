package fr.iot.derhore.rest.repository.custom.impl;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.repository.custom.IotRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import fr.iot.derhore.rest.enumIot.Type;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class IotRepositoryCustomImpl implements IotRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<IotObject> findTemperatureBetweenTwoDate(LocalDateTime dateDebut, LocalDateTime dateFin) {
        Query query = new Query();

        query.addCriteria(Criteria.where("time").gte(dateDebut.toString()).and("time").lt(dateFin.toString()));
        query.addCriteria(Criteria.where("type").is(Type.TEMPERATURE.toString()));

        return mongoTemplate.find(query, IotObject.class);
    }

    @Override
    public void updateAmpouleByTopic(String topic, IotObject iotObject) {
        Query query = new Query();

        query.addCriteria(Criteria.where("topic").is(topic));
        query.addCriteria(Criteria.where("type").is(iotObject.getType()));

        Update update = new Update();
        update.set("state",iotObject.getState());
        mongoTemplate.updateFirst(query,update,IotObject.class);
    }
}
