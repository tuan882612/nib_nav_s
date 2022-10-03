package api_v1.reactiveuser.Service;

import api_v1.reactiveuser.Model.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackService {
    Flux<Feedback> findAll();
    Mono<Feedback> findByName(String name);
    void createFeedback(Feedback feedback);
    String testGet();
}
