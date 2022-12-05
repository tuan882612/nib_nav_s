package api_v1.reactiveuser.Feedback;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/feedback/")
public class FeedbackController {
    private final FeedbackService feedBackService;

    @Autowired
    public FeedbackController(FeedbackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<Feedback>> getByID(@PathVariable("id") String name) {
        return feedBackService.findById(name)
            .map(feedback -> new ResponseEntity<>(feedback, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<Mono<Feedback>>> createFeedback(@RequestBody Feedback feedback){
        return feedBackService.findById(feedback.getEmail())
            .map(fb -> new ResponseEntity<>(
                feedBackService.createFeedback(feedback), HttpStatus.CREATED))
            .defaultIfEmpty(new ResponseEntity<>(
                feedBackService.createFeedback(feedback), HttpStatus.CREATED));
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Mono<Feedback>>> updateFeedback(@RequestBody Feedback feedback) {
        return feedBackService.findById(feedback.getEmail())
            .map(f -> new ResponseEntity<>(
                feedBackService.updateFeedback(feedback), HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(
                feedBackService.createFeedback(feedback), HttpStatus.CREATED));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Object>> deleteFeedback(@PathVariable("id") String id){
        return feedBackService.findById(id)
            .flatMap(feedback -> feedBackService.deleteById(id)
                .then(Mono.just(ResponseEntity.status(202).build())))
            .defaultIfEmpty(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
