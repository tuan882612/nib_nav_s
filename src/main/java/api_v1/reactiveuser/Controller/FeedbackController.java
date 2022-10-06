package api_v1.reactiveuser.Controller;

import api_v1.reactiveuser.Model.Feedback;
import api_v1.reactiveuser.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/feedback/")
public class FeedbackController {

    private final FeedbackService feedBackService;

    @Autowired
    public FeedbackController(FeedbackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @GetMapping(value = "/get/all")
    @ResponseBody
    public Flux<Feedback> findAllFeedback() {
        return feedBackService.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<Feedback>> getByID(@PathVariable("id") String name) {
        return feedBackService.findById(name)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value="/get/feedback/{id}")
    public Mono<ResponseEntity<Object>> getFeedback(@PathVariable("id") String name){
        return feedBackService.findById(name)
                .map(Feedback::getFeedback)
                .map(feedbacks->(feedbacks.isEmpty()?
                        ResponseEntity.noContent(): ResponseEntity.ok())
                        .build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Object>> createFeedback(@RequestBody Feedback feedback){
        return feedBackService.findById(feedback.getName())
                .map(feedback1 -> ResponseEntity.status(409).build())
                .defaultIfEmpty(new ResponseEntity<>(
                        feedBackService.createFeedback(feedback),
                        HttpStatus.CREATED));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Mono<Feedback>>> updateFeedback(@RequestBody Feedback feedback) {
        return feedBackService.findById(feedback.getName())
                .map(f -> new ResponseEntity<>(
                        feedBackService.updateFeedback(feedback),
                        HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(
                        feedBackService.createFeedback(feedback),
                        HttpStatus.CREATED));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Object>> deleteFeedback(@PathVariable("id") String id){
        return feedBackService.findById(id)
                .flatMap(feedback ->
                        feedBackService.deleteById(id)
                                .then(Mono.just(ResponseEntity.status(202).build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
