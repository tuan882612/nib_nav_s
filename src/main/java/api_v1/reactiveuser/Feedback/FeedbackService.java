package api_v1.reactiveuser.Feedback;

import reactor.core.publisher.Mono;

public interface FeedbackService {
    Mono<Feedback> findById(String Id);
    Mono<Feedback> createFeedback(Feedback feedback);
    Mono<Feedback> updateFeedback(Feedback feedback);
    Mono<Void> deleteById(String id);
    String testGet();
}
