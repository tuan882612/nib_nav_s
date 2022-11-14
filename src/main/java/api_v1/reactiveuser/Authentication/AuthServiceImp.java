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
    public Mono<Auth> find(int key) {
        return authRepository.findById(String.valueOf(key));
    }

    @Override
    public Mono<Auth> findByEmail(String email) {
        return authRepository.findAuthByEmail(email);
    }

    @Override
    public Mono<Void> delete(Auth auth) {
        return authRepository.delete(auth);
    }
}
