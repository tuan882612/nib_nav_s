package api_v1.reactiveuser;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class defaultEndpoint {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
