package api_v1.reactiveuser.Service;

import api_v1.reactiveuser.Model.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackService {
    Flux<Feedback> findAll();
    Mono<Feedback> findById(String Id);
    void createFeedback(Feedback feedback);
    String testGet();
}
