package api_v1.reactiveuser.User;

import api_v1.reactiveuser.User.Favorite.Favorite;
import api_v1.reactiveuser.User.Order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User implements Serializable {
    @Id
    private String email;
    private String name;
    private String password;
    private List<Order> order = new ArrayList<>();
//    private List<Favorite> favorite = new ArrayList<>();

    public User(
        String email,
        String name,
        String password) {
        setEmail(email);
        setName(name);
        setPassword(password);
    }
}
