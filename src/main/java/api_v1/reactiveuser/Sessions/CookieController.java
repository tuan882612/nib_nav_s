package api_v1.reactiveuser.Sessions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/session")
public class CookieController {
    @GetMapping("/all")
    public String testing(HttpServletRequest request) {
        return "man";
    }
}
