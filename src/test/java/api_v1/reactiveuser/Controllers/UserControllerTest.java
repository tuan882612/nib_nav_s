package api_v1.reactiveuser.Controllers;

import api_v1.reactiveuser.Controller.UserController;
import api_v1.reactiveuser.Model.User;
import api_v1.reactiveuser.Repository.UserRepository;
import api_v1.reactiveuser.Service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Unit Test 1 - User Service injection")
    public void testGet() {
        assertEquals("key",userService.testGet());
    }

    @Test
    @DisplayName("Unit Test 2 - Find the User by email")
    public void testFindUserByEmail() {
        String email = "test@gmail.com";
        User preUser = new User();
        preUser.setName("Test");
        preUser.setEmail(email);
        userRepository.save(preUser).block();

        Mono<User> testUser = userRepository.findByEmail(email);

        StepVerifier.create(testUser)
            .assertNext(user -> {
                assertEquals("Tuan",user.getName());
            })
            .expectComplete()
            .verify();
    }
}
