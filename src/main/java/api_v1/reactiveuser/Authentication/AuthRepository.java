package api_v1.reactiveuser.Authentication;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AuthRepository extends ReactiveMongoRepository<Auth, String> {
    @Query(value = "{_id: ?0}")
    Mono<Auth> findAuthByEmail(String email);
}
