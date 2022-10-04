package api_v1.reactiveuser.Repository;

import api_v1.reactiveuser.Model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {
    @Query(value = "{email: ?0}")
    Mono<User> findByEmail(String email);

    @Query(value = "{email: ?0}")
    Mono<User> deleteByEmail(String email);
}
