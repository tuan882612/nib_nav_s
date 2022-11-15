package api_v1.reactiveuser.Sessions;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CookieServiceImp implements CookieService {
    private final CookieRepository repository;

    @Autowired
    public CookieServiceImp(CookieRepository cookieRepository) {
        this.repository = cookieRepository;
    }

}
