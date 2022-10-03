package api_v1.reactiveuser.Service.Implmentation;

import api_v1.reactiveuser.Model.Feedback;
import api_v1.reactiveuser.Repository.FeedbackRepository;
import api_v1.reactiveuser.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FeedbackImp implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Flux<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public Mono<Feedback> findByName(String name) {
        return feedbackRepository.findByName(name);
    }

    @Override
    public void createFeedback(Feedback feedback) {
        feedbackRepository.save(feedback).subscribe();
    }

    @Override
    public String testGet() {return "key";}


}
