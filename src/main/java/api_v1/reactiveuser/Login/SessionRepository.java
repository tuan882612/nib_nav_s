package api_v1.reactiveuser.Login;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends ReactiveMongoRepository<Session, String> {
}
