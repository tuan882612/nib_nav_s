package api_v1.reactiveuser.Service.Implmentation;

import api_v1.reactiveuser.Model.User;
import api_v1.reactiveuser.Repository.UserRepository;
import api_v1.reactiveuser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user).subscribe();
    }
}
