package api_v1.reactiveuser.Repository;

import api_v1.reactiveuser.Model.Feedback;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FeedbackRepository extends ReactiveMongoRepository<Feedback, String> {
    @Query("{name: ?0}")
    Mono<Feedback> findByName(String name);
}
