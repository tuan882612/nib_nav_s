package api_v1.reactiveuser.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceImp implements AuthService {

    private final AuthRepository authRepository;

    @Autowired
    public AuthServiceImp(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public Mono<Auth> save(Auth auth) {
        return authRepository.save(auth);
    }

    @Override
    public Mono<Auth> findByKey(int key) {
        return authRepository.findAuthByKey(key);
    }
}
