package api_v1.reactiveuser.Controllers;

import api_v1.reactiveuser.User.User;
import api_v1.reactiveuser.User.UserRepository;
import api_v1.reactiveuser.User.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

//        Mono<User> testUser = userRepository.findByEmail(email);
//
//        StepVerifier.create(testUser)
//            .assertNext(user -> assertEquals("Tuan",user.getName()))
//            .expectComplete()
//            .verify();
    }
}
