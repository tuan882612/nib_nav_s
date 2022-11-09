package api_v1.reactiveuser.Authentication;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends ReactiveMongoRepository<Auth, String> {
    
}
