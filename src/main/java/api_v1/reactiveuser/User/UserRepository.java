package api_v1.reactiveuser.User;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {

    @Query("{$and :[{_id: ?0},{password: ?1}] }")
    Mono<User> findUserByEmailPassword(String email, String password);
}
