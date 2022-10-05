package api_v1.reactiveuser.Controller;

import api_v1.reactiveuser.Model.Feedback;
import api_v1.reactiveuser.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

//    @GetMapping(value = "/get/feedback/{id}")
//    public Mono<ResponseEntity<List<Feedback>>> getFeedback(@PathVariable("id") String id) {
//        return feedBackService.findByEmail(id).map()
//    }

@PostMapping("/create")
@ResponseStatus(HttpStatus.CREATED)
public void createFeedback(@RequestBody Feedback feedback) {
    feedBackService.createFeedback(feedback);
}

//        return feedBackService.findByEmail(id).map()
    // might need updating & delete for feedback
}
