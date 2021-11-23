package fr.iot.derhore.rest.repository;

import fr.iot.derhore.rest.entity.Temperature;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TemperatureRepository extends MongoRepository<Temperature, ObjectId> {

}
