package fr.iot.derhore.rest.repository;

import fr.iot.derhore.rest.entity.IotObject;
import fr.iot.derhore.rest.enumIot.Action;
import fr.iot.derhore.rest.enumIot.Type;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IotRepository extends MongoRepository<IotObject, ObjectId> {

    List<IotObject> findAllByType(Type type);

    List<IotObject> findAllByTypeAndState(Type type, Action action);

}
