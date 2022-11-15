package api_v1.reactiveuser.Sessions;

import javax.servlet.http.Cookie;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookieRepository extends ReactiveMongoRepository<Cookie, String> {
}
