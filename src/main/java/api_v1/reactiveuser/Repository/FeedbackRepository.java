package api_v1.reactiveuser.Repository;

import api_v1.reactiveuser.Model.Feedback;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FeedbackRepository extends ReactiveMongoRepository<Feedback, String> {
}
