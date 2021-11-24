package fr.iot.derhore.rest.repository.custom.impl;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.repository.custom.TemperatureRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TemperatureRepositoryCustomImpl implements TemperatureRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<IotObject> findTemperatureBetweenTwoDate(LocalDateTime dateDebut, LocalDateTime dateFin) {
        Query query = new Query();

        query.addCriteria(Criteria.where("dateValue").gte(dateDebut.toString()).and("dateValue").lt(dateFin.toString()));

        return mongoTemplate.find(query, IotObject.class);
    }
}
