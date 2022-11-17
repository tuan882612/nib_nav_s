package api_v1.reactiveuser.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FeedbackServiceImp implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackServiceImp(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Mono<Feedback> findById(String id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public Mono<Feedback> createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Mono<Feedback> updateFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return feedbackRepository.deleteById(id);
    }

    @Override
    public String testGet() {return "key";}


}
