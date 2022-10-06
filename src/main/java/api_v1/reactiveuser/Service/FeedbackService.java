package api_v1.reactiveuser.Service;

import api_v1.reactiveuser.Model.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackService {
    Flux<Feedback> findAll();
    Mono<Feedback> findById(String Id);

    Mono<Feedback> createFeedback(Feedback feedback);

    Mono<Feedback> updateFeedback(Feedback feedback);


    Mono<Void> deleteById(String id);

    String testGet();
}
